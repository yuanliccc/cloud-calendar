package group.cc.pcc.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @description 此类只读取ClassPath及其下面的文件</br>
 * @attention 请记住关闭IO
 * @author NoSuchBugException
 */
public class FileReaderUtil {
	
	@SuppressWarnings("unused")
	private static final FileReaderUtil fileReaderUtil = new FileReaderUtil();
	
	private static final String classpath = FileReaderUtil.class.getResource("/").getPath();
	
	private FileReaderUtil(){}
	
	public static BufferedReader getBufferedReader(String filePath) throws Exception{
		BufferedReader bufferedReader = null;
		try {
			String absolutePath = classpath + filePath;
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(absolutePath),"UTF-8"));
			return bufferedReader;
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public synchronized static StringBuffer getStringBuffer(String filePath) throws Exception{
		BufferedReader bufferedReader = null;
		try{
			bufferedReader = getBufferedReader(filePath);
			StringBuffer stringBuffer = new StringBuffer("");
			String lineString = "";
			while ((lineString=bufferedReader.readLine()) != null) {
				stringBuffer.append(lineString);
			}
			return stringBuffer;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(bufferedReader != null) {
				bufferedReader.close();
			}
		}
	}
	
	/**
	 * 单线程优先选择StringBuilder
	 */
	public synchronized static StringBuilder getStringBuilder(String filePath) throws Exception{
		BufferedReader bufferedReader = null;
		try{
			bufferedReader = getBufferedReader(filePath);
			StringBuilder stringBuilder = new StringBuilder("");
			String lineString = "";
			while ((lineString=bufferedReader.readLine()) != null) {
				stringBuilder.append(lineString);
			}
			return stringBuilder;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally{
			if(bufferedReader != null) {
				bufferedReader.close();
			}
		}
	}
	
}
