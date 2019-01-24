package group.cc.pcc.model.converter;

import com.yl.common.converter.Converter;
import group.cc.pcc.file.FileOperatorUtil;
import group.cc.pcc.model.PccFile;
import org.springframework.web.multipart.MultipartFile;

public class PccFileConverter implements Converter<MultipartFile, PccFile> {

    @Override
    public PccFile convert(MultipartFile multipartFile) {
        PccFile file = new PccFile();
        String fileFullName = multipartFile.getOriginalFilename();

        file.setName(FileOperatorUtil.getFileSimpleName(fileFullName));
        file.setSize(multipartFile.getSize() * 1d);
        file.setType(FileOperatorUtil.getFileTypeByName(fileFullName));

        return file;
    }
}
