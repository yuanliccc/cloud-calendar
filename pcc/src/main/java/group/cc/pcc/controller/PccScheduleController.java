package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccSchedule;
import group.cc.pcc.model.dto.PccScheduleComplete;
import group.cc.pcc.model.dto.PccScheduleDto;
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

    /* 新增 API*/

    @ApiOperation(value="从起始日期到截止日期内（包含起始日期及截止日期），每日发布的任务数量")
    @GetMapping("day/count")
    public Result dayCount(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam Integer pccUserId) {
        List<Map<String, Object>> list = pccScheduleService.dayCount(startDate, endDate, pccUserId);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation(value="新增 pccSchedule")
    @PostMapping("add")
    public Result add(@RequestBody PccScheduleDto pccScheduleDto) {

        pccScheduleService.add(pccScheduleDto);

        return ResultGenerator.genSuccessResult();
    }


    @ApiOperation(value="分页查询 PccSchedule 列表")
    @GetMapping("relation")
    public Result relationList(@RequestParam(defaultValue = "0") Integer page,
                               @RequestParam(defaultValue = "0") Integer size,
                               @RequestParam Integer pccUserId) {

        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = pccScheduleService.relationList(pccUserId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation(value="分页查询 PccSchedule 列表")
    @GetMapping("create")
    public Result createList(@RequestParam(defaultValue = "0") Integer page,
                               @RequestParam(defaultValue = "0") Integer size,
                               @RequestParam Integer pccUserId) {

        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = pccScheduleService.createList(pccUserId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 PccSchedule 列表")
    @GetMapping("untreated")
    public Result untreatedList(@RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "0") Integer size,
                             @RequestParam Integer pccUserId) {

        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = pccScheduleService.untreated(pccUserId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 PccSchedule 列表")
    @GetMapping("treated")
    public Result treatedList(@RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "0") Integer size,
                                @RequestParam Integer pccUserId) {

        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = pccScheduleService.treated(pccUserId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("完成任务")
    @PostMapping("complete")
    public Result complete(@RequestBody PccScheduleComplete pccScheduleComplete) {

        if(pccScheduleComplete == null) {
            return ResultGenerator.genFailResult("所需数据为空");
        }

        pccScheduleService.complete(pccScheduleComplete);

        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("分页查询 PccSchedule 列表")
    @GetMapping("additional")
    public Result additionalInfoList(@RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "0") Integer size,
                               @RequestParam Integer pccScheduleId) {

        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = pccScheduleService.additionalInfoList(pccScheduleId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("历史任务统计，包含相关数量，已完成数量等")
    @GetMapping("history/count")
    public Result historyCount(@RequestParam Integer pccUserId) {
        Map<String, Object> counts = pccScheduleService.historyCount(pccUserId);
        return ResultGenerator.genSuccessResult(counts);
    }

    @ApiOperation("指派数量，以人分组")
    @GetMapping("history/assign/count")
    public Result historyAssignCount(@RequestParam Integer pccUserId) {
        List<Map<String, Object>> counts = pccScheduleService.historyAssignCount(pccUserId);
        return ResultGenerator.genSuccessResult(counts);
    }

    @ApiOperation("被指派数量，以人分组")
    @GetMapping("history/assigned/count")
    public Result historyAssignedCount(@RequestParam Integer pccUserId) {
        List<Map<String, Object>> counts = pccScheduleService.historyAssignedCount(pccUserId);
        return ResultGenerator.genSuccessResult(counts);
    }

    @ApiOperation(value="从起始日期到截止日期内（包含起始日期及截止日期），每日发布的任务数量，完成数量，接受数量")
    @GetMapping("day/counts")
    public Result counts(@RequestParam Integer pccUserId, @RequestParam Date startDate, @RequestParam Date endDate) {
        return ResultGenerator.genSuccessResult(pccScheduleService.counts(pccUserId, startDate, endDate));
    }

}
