package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccScheduleRemind;
import group.cc.pcc.service.PccScheduleRemindService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanli
 * @date 2019/01/10
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/schedule/remind")
public class PccScheduleRemindController {
    @Resource
    private PccScheduleRemindService pccScheduleRemindService;

    @ApiOperation("添加 PccScheduleRemind")
    @PostMapping
    public Result add(@RequestBody PccScheduleRemind pccScheduleRemind) {
        pccScheduleRemindService.save(pccScheduleRemind);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccScheduleRemind")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccScheduleRemindService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccScheduleRemind")
    @PutMapping
    public Result update(@RequestBody PccScheduleRemind pccScheduleRemind) {
        pccScheduleRemindService.update(pccScheduleRemind);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccScheduleRemind 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccScheduleRemind pccScheduleRemind = pccScheduleRemindService.findById(id);
        return ResultGenerator.genSuccessResult(pccScheduleRemind);
    }

    @ApiOperation("分页查询 PccScheduleRemind 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccScheduleRemind> list = pccScheduleRemindService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
