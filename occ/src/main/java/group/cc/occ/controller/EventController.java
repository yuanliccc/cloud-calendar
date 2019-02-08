package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Event;
import group.cc.occ.service.EventService;
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
@RequestMapping("/event")
public class EventController {
    @Resource
    private EventService eventService;

    @ApiOperation(value="添加 Event")
    @PostMapping
    public Result add(@RequestBody Event event) {
        eventService.save(event);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 Event")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        eventService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 Event")
    @PutMapping
    public Result update(@RequestBody Event event) {
        eventService.update(event);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 Event 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Event event = eventService.findById(id);
        return ResultGenerator.genSuccessResult(event);
    }

    @ApiOperation(value="分页查询 Event 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Event> list = eventService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
