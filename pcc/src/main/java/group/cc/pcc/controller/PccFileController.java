package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccFile;
import group.cc.pcc.service.PccFileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanli
 * @date 2019/01/15
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/file")
public class PccFileController {
    @Resource
    private PccFileService pccFileService;

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
}
