package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccTask;
import group.cc.pcc.service.PccTaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yuanli
 * @date 2019/05/31
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/task")
public class PccTaskController {
    @Resource
    private PccTaskService pccTaskService;

    @ApiOperation("添加 PccTask")
    @PostMapping
    public Result add(@RequestBody PccTask pccTask) {
        pccTaskService.save(pccTask);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccTask")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccTaskService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccTask")
    @PutMapping
    public Result update(@RequestBody PccTask pccTask) {
        pccTaskService.update(pccTask);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccTask 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccTask pccTask = pccTaskService.findById(id);
        return ResultGenerator.genSuccessResult(pccTask);
    }

    @ApiOperation("分页查询 PccTask 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccTask> list = pccTaskService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("指定天的日程列表")
    @GetMapping("/day")
    public Result listDay(@RequestParam("day") String day) {
        List<PccTask> list = pccTaskService.listDay(day);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("指定天的日程列表")
    @GetMapping("/day/user")
    public Result listDayUser(@RequestParam("day") String day
            , @RequestParam("pccUserId")Integer pccUserId) {
        List<PccTask> list = pccTaskService.listDayUser(day, pccUserId);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("分页查询 PccTask 列表")
    @GetMapping("/will/dead")
    public Result willDeadList() {
        List<Map<String, Object>> list = pccTaskService.willDeadList();
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("分页查询 PccTask 列表")
    @GetMapping("/delete/imitate")
    public Result deleteImitate(@Param("id") Integer id) {
        pccTaskService.deleteImitate(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("分页查询 PccTask 列表")
    @GetMapping("/counts")
    public Result counts(@Param("pccUserId") Integer pccUserId,
                         @Param("startDay") String startDay,
                         @Param("endDay") String endDay) {
        Map<String , Long> res = pccTaskService.counts(pccUserId, startDay, endDay);
        return ResultGenerator.genSuccessResult(res);
    }
}
