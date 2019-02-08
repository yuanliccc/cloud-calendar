package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccScheduleText;
import group.cc.pcc.service.PccScheduleTextService;
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
@RequestMapping("/pcc/schedule/text")
public class PccScheduleTextController {
    @Resource
    private PccScheduleTextService pccScheduleTextService;

    @ApiOperation("添加 PccScheduleText")
    @PostMapping
    public Result add(@RequestBody PccScheduleText pccScheduleText) {
        pccScheduleTextService.save(pccScheduleText);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccScheduleText")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccScheduleTextService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccScheduleText")
    @PutMapping
    public Result update(@RequestBody PccScheduleText pccScheduleText) {
        pccScheduleTextService.update(pccScheduleText);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccScheduleText 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccScheduleText pccScheduleText = pccScheduleTextService.findById(id);
        return ResultGenerator.genSuccessResult(pccScheduleText);
    }

    @ApiOperation("分页查询 PccScheduleText 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccScheduleText> list = pccScheduleTextService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
