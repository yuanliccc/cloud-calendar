package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccSchedule;
import group.cc.pcc.service.PccScheduleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuanli
 * @date 2018/12/23
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/schedule")
public class PccScheduleController {
    @Resource
    private PccScheduleService pccScheduleService;

    @ApiOperation(value="添加 PccSchedule")
    @PostMapping
    public Result add(@RequestBody PccSchedule pccSchedule) {
        pccScheduleService.save(pccSchedule);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 PccSchedule")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccScheduleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 PccSchedule")
    @PutMapping
    public Result update(@RequestBody PccSchedule pccSchedule) {
        pccScheduleService.update(pccSchedule);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 PccSchedule 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccSchedule pccSchedule = pccScheduleService.findById(id);
        return ResultGenerator.genSuccessResult(pccSchedule);
    }

    @ApiOperation(value="分页查询 PccSchedule 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccSchedule> list = pccScheduleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation(value="从起始日期到截止日期内（包含起始日期及截止日期），每日发布的任务数量")
    @GetMapping("day/count")
    public Result dayCount(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam Integer pccUserId) {

        List<Map<String, Object>> list = pccScheduleService.dayCount(startDate, endDate, pccUserId);

        return ResultGenerator.genSuccessResult(list);
    }
}
