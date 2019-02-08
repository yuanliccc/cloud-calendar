package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Schedule;
import group.cc.occ.service.ScheduleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangyuming
 * @date 2019/01/02
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    @ApiOperation(value="添加 Schedule")
    @PostMapping
    public Result add(@RequestBody Schedule schedule) {
        scheduleService.save(schedule);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 Schedule")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        scheduleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 Schedule")
    @PutMapping
    public Result update(@RequestBody Schedule schedule) {
        scheduleService.update(schedule);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 Schedule 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Schedule schedule = scheduleService.findById(id);
        return ResultGenerator.genSuccessResult(schedule);
    }

    @ApiOperation(value="分页查询 Schedule 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Schedule> list = scheduleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
