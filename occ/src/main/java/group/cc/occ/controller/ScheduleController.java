package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Schedule;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.ScheduleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import group.cc.occ.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author wangyuming
* @date 2019/03/29
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/schedule")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 Schedule")
    @PostMapping("/add")
    public Result add(@RequestBody Schedule schedule) {
        scheduleService.save(schedule);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 Schedule")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        scheduleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 Schedule")
    @PutMapping("/update")
    public Result update(@RequestBody Schedule schedule) {
        scheduleService.update(schedule);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 Schedule 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Schedule schedule = scheduleService.findById(id);
        return ResultGenerator.genSuccessResult(schedule);
    }

    @ApiOperation("分页查询 Schedule 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Schedule> list = scheduleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 Schedule 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<Schedule> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        if("".equals(key))
            list = scheduleService.findAllByLoginOrgId(login);
        else
            list = scheduleService.listByKey(key, value, login);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 Schedule")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Schedule> schedules) {
        scheduleService.deleteBatch(schedules);
        return ResultGenerator.genSuccessResult();
    }
}
