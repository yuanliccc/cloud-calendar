package group.cc.pcc.file;

import com.yl.common.util.PropertiesReaderUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 文件操作类(暂未加入文件路径格式的验证)
 * @author yuanli
 * @version 1.1</br>
 * 1.保存规则：</br>
 *              &nbsp;&nbsp;&nbsp;&nbsp;1）此类传入的保存路径不会作为文件存储直接路径（您的保存路径将作为存储主目录
 *              ，根据文件类型建立以文件类型名为名的文件夹， 接下来的层级为：年-月-日,您的文件将存储在日所在的文件夹下）</br>
 *              &nbsp;&nbsp;&nbsp;&nbsp;2）保存文件后将返回Map,包含filePath:文件路径,fileType:文件类型
 *              ,fileSize:文件大小（byte）</br>
 *              &nbsp;&nbsp;&nbsp;&nbsp;3）二进制文件统一存为.yl
 */
public class FileOperatorUtil {

	public static final String fileTypeMapKey = "fileType";
	
	public static final String filePathMapKey = "filePath";
	
	public static final String defaultFileType = "yl";
	
	public static final String fileSizeMapKey = "fileSize";
	
	private static Map<String, String> fileTypeCode;
	
	static {initFileTypeCode();}

	private FileOperatorUtil() {}

	/**
	 * 存储文件，但文件类型通过编码识别，较慢
	 * @param file 待保存File
	 * @param storagePath 存储路径（请查看类说明存储规则）
	 * @return 返回Map,包含filePath:文件路径,fileType:文件类型,fileSize:文件大小（byte）
	 * @warn 若存在无法识别的类型，我们会以.yl类型返回
	 */
	public static Map<String, Object> storageFileWithoutFileType(File file, String storagePath, String fileName) throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		try{
			String fileType = getFileTypeByCode(file);
			if(fileType == null){
				resultMap.put(fileTypeMapKey, defaultFileType);
			}
			else if(fileType.equals("xls") || fileType.equals("xlsx")){
				resultMap.put(fileTypeMapKey, getFileTypeByName(fileName));
			}
			else{
				resultMap.put(fileTypeMapKey, fileType);
			}
			String filePath = createFilePathByRuleOfTime((String)resultMap.get(fileTypeMapKey),storagePath);
			resultMap.put(filePathMapKey, filePath);
			createFileByFilePath(filePath);
			resultMap.put(fileSizeMapKey, storageFile(file, filePath));
			return resultMap;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static File createEmptyFile(String storagePath, String fileName) throws Exception {
	    String fileType = getFileTypeByName(fileName);
	    return createFileByFilePath(createFilePathByRuleOfTime(fileType, storagePath));
    }

    public static boolean deleteFile(String absoluteFilePath) {
        File file = new File(absoluteFilePath);
        if(file.exists() && file.isFile()) {
            file.delete();
            return true;
        }
        else {
            return false;
        }
    }

    public static List<Map<String, Object>> storageFiles(File[] files, String storagePath,
                                                                        List<String> fileNames) throws Exception {
        List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        try{
            for(int i = 0; i < files.length; i++) {
                result.add(storageFile(files[i],storagePath,fileNames.get(i)));
            }
            return result;
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Map<String, Object> storageFile(File file, String storagePath, String fileName) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        try{
            String fileType = getFileTypeByName(fileName);
            if(fileType == null){
                resultMap.put(fileTypeMapKey, defaultFileType);
            }
            else{
                resultMap.put(fileTypeMapKey, fileType);
            }
            String filePath = createFilePathByRuleOfTime((String)resultMap.get(fileTypeMapKey), storagePath);
            resultMap.put(filePathMapKey, filePath);
            createFileByFilePath(filePath);
            resultMap.put(fileSizeMapKey, storageFile(file, filePath));
            return resultMap;
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
	
	/**
	 * 获取文件除去类型的名称
	 */
	public static String getFileSimpleName(String fileName) {
		if(fileName == null || fileName.equals("")) return null;
		int index = fileName.lastIndexOf('.');
		if(index > 0) {
			return fileName.substring(0, index);
		}
		return null;
	}
	
	/**
	 * 通过filename获取文件类型
	 */
	public static String getFileTypeByName(String fileName){
		if(fileName == null || fileName.equals("")) return defaultFileType;
		int index = fileName.lastIndexOf('.');
		if(index >= 0 && index != fileName.length() - 1) {
			return fileName.substring(index + 1,fileName.length());
		}
		return defaultFileType;
	}
	
	/**
	 * 保存并返回文件大小
	 */
	private static Long storageFile(File file, String filePath) throws Exception{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		Long size = null;
		try{
			fis = new FileInputStream(file);
			fos = new FileOutputStream(filePath);
			/**获取通道*/
			FileChannel fcI = fis.getChannel();
			FileChannel fcO = fos.getChannel();
			size = fcI.size();
			ByteBuffer bf = ByteBuffer.allocate(1024);
			while(true) {
				bf.clear();
				int rs = fcI.read(bf);
				if(rs == -1) break;
				bf.flip();
				fcO.write(bf);
			}
			fis.close();
			fos.close();
			return size;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private synchronized static String createFilePathByRuleOfTime(String fileType,String storagePath){
		Calendar calendar = Calendar.getInstance();
		return "/" + storagePath + "/" + fileType + "/" + calendar.get(Calendar.YEAR) 
				+ "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH)
                + "/" + System.nanoTime() + "." + fileType;
	}

	/**
	 * 存储文件，但文件类型通过编码识别，较慢
	 * @param files 待保存File
	 * @param storagePath 存储路径（请查看类说明存储规则）
	 * @return 返回Map集合,包含filePath:文件路径,fileType:文件类型,fileSize:文件大小（byte）
	 * @warn 若存在无法识别的类型，我们会以.yl类型返回
	 */
	public static List<Map<String, Object>> storageFilesWithoutFileType(File[] files, String storagePath,
                                                                        List<String> fileNames) throws Exception{
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try{
			for(int i = 0; i < files.length; i++) {
				result.add(storageFileWithoutFileType(files[i],storagePath,fileNames.get(i)));
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 通过编码获取文件类型
	 * @param file 待识别File
	 */
	public static String getFileTypeByCode(File file) throws Exception {
		String fileType = null;
		try {
			byte[] typeByte = new byte[50];
			InputStream iStream = new FileInputStream(file);
			iStream.read(typeByte);
			fileType = parseByteToFileType(typeByte);
			iStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return fileType;
	}

	/**
	 * 解析文件头部，返回文件类型
	 */
	private static String parseByteToFileType(byte[] typeByte) {
		String fileTypeHex = byteChangeToHex(typeByte);
        Iterator<Entry<String, String>> entryIterator = fileTypeCode.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Entry<String,String> entry =  entryIterator.next();
            String fileTypeHexValue = entry.getValue();
            if (fileTypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return null;
	}

	/**
	 * byte转16进制
	 */
	private static String byteChangeToHex(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 判断文件是否存在
	 * @param filePath 文件路径
	 */
	public static boolean judgeFilePathIsFile(String filePath) throws Exception {
		try {
			File file = new File(filePath);
			if (file.exists() && file.isFile()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 判断目录是否存在
	 * @param directoryPath 目录路径
	 */
	public static boolean judgeDirectoryPathIsDirectory(String directoryPath)
			throws Exception {
		try {
			File file = new File(directoryPath);
			if (file.exists() && file.isDirectory()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 创建文件
	 * @param filePath 文件路径
	 */
	public static File createFileByFilePath(String filePath) throws Exception {
		try {
			File file = new File(filePath);
			if (file.exists() && file.isFile()) {
			} else {
				String directoryPath = getDirectory(filePath);
				if(!createDirectoryByDirectoryPath(directoryPath)) {
					throw new IOException("Generate directory: '" + directoryPath + "' failed!");
				}
				file.createNewFile();
				return file;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	public static String getDirectory(String filePath) {
		int index = -1;
		for (int i = filePath.length() - 1; i >= 0; i--) {
			if (filePath.charAt(i) == '/') {
				index = i;
				break;
			}
		}
		if (index == -1) {
			return "";
		} else {
			return filePath.substring(0, index);
		}
	}


	/**
	 * 判断目录是否存在并创建目录
	 * @param directoryPath 文件路径
	 */
	public static boolean createDirectoryByDirectoryPath(String directoryPath) {
		boolean isCreate = false;
		try {
			File file = new File(directoryPath);
			if (file.exists() && file.isDirectory()) {
				return true;
			} else {
				return file.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private static void initFileTypeCode() {
		fileTypeCode = new HashMap<>();
		Properties properties = PropertiesReaderUtil
				.getProperties("fileTypeCode.properties");
		Set<Object> keys = properties.keySet();
		for (Object key : keys) {
			fileTypeCode.put(key.toString(), properties.get(key).toString());
		}
	}
	
	
	//有关正则表达式的操作，正在研究，不可用
	/**@deprecated*/
	public static final String filePathPatternExp_windows = "[a-zA-Z]:((\\|/)\\w+)(\\w+)*(\\.\\w+)?";

	/**@deprecated*/
	public static final String directoryPathPatternExp_windows = "^[a-zA-Z]:(/\\w+)*";

	/**@deprecated*/
	public static final String filePathPatternExp_linux = "/(\\w+/)*\\w+(\\.\\w+)";

	/**@deprecated*/
	public static final String directoryPathPatternExp_linux = "/(\\w+/)*\\w+";
	
	/**@deprecated 验证目录是否正确*/
	public static boolean judgeDirectoryPath(String directoryPath) {
		Properties properties = System.getProperties();
		String osName = properties.getProperty("os.name");
		if (osName.indexOf("Windows") > 0) {
			Pattern pattern = Pattern.compile(directoryPathPatternExp_windows);
			Matcher matcher = pattern.matcher(directoryPath);
			return matcher.matches();
		} else {
			Pattern pattern = Pattern.compile(directoryPathPatternExp_linux);
			Matcher matcher = pattern.matcher(directoryPath);
			return matcher.matches();
		}
	}
	
	/**@deprecated 验证文件路径是否正确*/
	public static boolean judgeFilePath(String filePath) {
		Properties properties = System.getProperties();
		String osName = properties.getProperty("os.name");
		if (osName.indexOf("Windows") > 0) {
			Pattern pattern = Pattern.compile(filePathPatternExp_windows);
			Matcher matcher = pattern.matcher(filePath);
			return matcher.matches();
		} else {
			Pattern pattern = Pattern.compile(filePathPatternExp_linux);
			Matcher matcher = pattern.matcher(filePath);
			return matcher.matches();
		}
	}
	
	/**@deprecated*/
	public static String getDirectoryPathFormFilePath(String filePath) {
		Properties properties = System.getProperties();
		String osName = properties.getProperty("os.name");
		if (osName.indexOf("Windows") >= 0) {
			Pattern pattern = Pattern.compile(directoryPathPatternExp_windows);
			Matcher matcher = pattern.matcher(filePath);
			return matcher.group(1);
		} else {
			Pattern pattern = Pattern.compile(directoryPathPatternExp_linux);
			Matcher matcher = pattern.matcher(filePath);
			return matcher.group(0);
		}
	}
}
