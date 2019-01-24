package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.file.FileOperatorUtil;
import group.cc.pcc.model.PccFile;
import group.cc.pcc.model.converter.PccFileConverter;
import group.cc.pcc.service.PccFileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yuanli
 * @date 2019/01/15
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/file")
@PropertySource({"classpath:application.properties"})
public class PccFileController {
    @Resource
    private PccFileService pccFileService;

    @Value("${pcc.windows.file.storage.directory}")
    private String windowsStoragePath;

    @ApiOperation("添加 PccFile")
    @PostMapping
    public Result add(@RequestBody PccFile pccFile) {
        pccFileService.save(pccFile);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccFile")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccFileService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccFile")
    @PutMapping
    public Result update(@RequestBody PccFile pccFile) {
        pccFileService.update(pccFile);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccFile 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccFile pccFile = pccFileService.findById(id);
        return ResultGenerator.genSuccessResult(pccFile);
    }

    @ApiOperation("分页查询 PccFile 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccFile> list = pccFileService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") List<MultipartFile> files) {
        List<PccFile> pccFiles = new ArrayList<>(files.size());
        PccFileConverter converter = new PccFileConverter();

        for (MultipartFile file : files) {
            File dest = null;
            try {
                // 创建目标文件，防止直接声明路径作为file时，文件上级目录不存在的情况，该过程会创建目录及文件
                dest = FileOperatorUtil.createEmptyFile(windowsStoragePath, file.getOriginalFilename());
                // 将上传的缓存文件转储到目标文件中
                file.transferTo(dest);
                // 创建 PccFile 对象
                PccFile pccFile = converter.convert(file);
                pccFile.setUrl(dest.getPath());
                pccFile.setUrlType("local");

                pccFiles.add(pccFile);
            }
            catch (Exception e) {
                return ResultGenerator.genFailResult("服务器错误，请等待袁李大大维护！");
            }
        }
        // 将 PccFile 列表插入到数据中
        pccFileService.save(pccFiles);
        return ResultGenerator.genSuccessResult(pccFiles);
    }

    @ApiOperation("上传文件")
    @PostMapping("/delete")
    public Result delete(@RequestBody PccFile pccFile) {
        boolean isDelete = FileOperatorUtil.deleteFile(pccFile.getUrl());
        if(!isDelete) {
            return ResultGenerator.genFailResult("无法找到要删除的文件");
        }
        else {
            pccFileService.deleteById(pccFile.getId());
        }

        return ResultGenerator.genSuccessResult("删除文件成功");
    }

}
