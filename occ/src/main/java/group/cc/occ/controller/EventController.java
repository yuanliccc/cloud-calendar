package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Event;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.EventService;
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
* @date 2019/04/26
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/event")
public class EventController {
    @Resource
    private EventService eventService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 Event")
    @PostMapping("/add")
    public Result add(@RequestBody Event event) {
        eventService.save(event);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 Event")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        eventService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 Event")
    @PutMapping("/update")
    public Result update(@RequestBody Event event) {
        eventService.update(event);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 Event 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Event event = eventService.findById(id);
        return ResultGenerator.genSuccessResult(event);
    }

    @ApiOperation("分页查询 Event 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Event> list = eventService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 Event 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        List<Event> list = null;
        if("".equals(key))
            list = eventService.listByKey("title", "", login);
        else
            list = eventService.listByKey(key, value, login);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 Event")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Event> events) {
        eventService.deleteBatch(events);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("根据ScheduleId获取 Event")
    @GetMapping("/getTheEventByScheduleId")
    public Result getTheEventByScheduleId(@RequestParam()Integer id){
        List<Event> list = this.eventService.getTheEventByScheduleId(id);
        return ResultGenerator.genSuccessResult(list);
    }
}
