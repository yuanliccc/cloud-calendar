package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.NoticeList;
import group.cc.occ.model.Schedule;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.model.dto.ScheduleDto;
import group.cc.occ.service.NoticeListService;
import group.cc.occ.service.ScheduleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import group.cc.occ.util.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

    @Resource
    private NoticeListService noticeListService;

    @ApiOperation("添加 Schedule")
    @PostMapping("/add")
    public Result add(@RequestBody Schedule schedule) {
        scheduleService.save(schedule);

        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        this.noticeListService.notice(login.getUser().getId(),"机构管理员发布了工作日程：" + schedule.getTitle(),
                schedule.getContent(), "机构通知", schedule.getSubordinatecanseen(), login);

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
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        this.noticeListService.notice(login.getUser().getId(),"机构管理员更新了工作日程：" + schedule.getTitle(),
                schedule.getContent(), "机构通知", schedule.getSubordinatecanseen(), login);
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
            list = scheduleService.listByKey("name", "", login);
        else
            list = scheduleService.listByKey(key, value, login);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("获取当前组织机构所有日程")
    @GetMapping("/findAllSchedule")
    public Result findAllSchedule() {
        List<Schedule> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        list = scheduleService.findAllByLoginOrgId(login);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("批量删除 Schedule")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Schedule> schedules) {
        scheduleService.deleteBatch(schedules);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("撤销该条日程")
    @GetMapping("/revoke")
    public Result revoke(@RequestParam()Integer id){
        scheduleService.revoke(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("获取当前组织机构当月所有日程")
    @GetMapping("/findAllScheduleThisMonth")
    public Result findAllScheduleThisMonth(@RequestParam()String dayTime) {
        List<ScheduleDto> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        list = scheduleService.findAllScheduleThisMonth(login, new Date(dayTime));
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("获取当前组织机构今天所有日程")
    @GetMapping("/findAllScheduleToday")
    public Result findAllScheduleToday() {
        List<ScheduleDto> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        list = scheduleService.findAllScheduleToday(login);
        return ResultGenerator.genSuccessResult(list);
    }
}
