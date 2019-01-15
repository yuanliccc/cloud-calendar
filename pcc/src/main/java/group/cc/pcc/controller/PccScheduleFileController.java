package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccScheduleFile;
import group.cc.pcc.service.PccScheduleFileService;
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
@RequestMapping("/pcc/schedule/file")
public class PccScheduleFileController {
    @Resource
    private PccScheduleFileService pccScheduleFileService;

    @ApiOperation("添加 PccScheduleFile")
    @PostMapping
    public Result add(@RequestBody PccScheduleFile pccScheduleFile) {
        pccScheduleFileService.save(pccScheduleFile);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccScheduleFile")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccScheduleFileService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccScheduleFile")
    @PutMapping
    public Result update(@RequestBody PccScheduleFile pccScheduleFile) {
        pccScheduleFileService.update(pccScheduleFile);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccScheduleFile 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccScheduleFile pccScheduleFile = pccScheduleFileService.findById(id);
        return ResultGenerator.genSuccessResult(pccScheduleFile);
    }

    @ApiOperation("分页查询 PccScheduleFile 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccScheduleFile> list = pccScheduleFileService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
