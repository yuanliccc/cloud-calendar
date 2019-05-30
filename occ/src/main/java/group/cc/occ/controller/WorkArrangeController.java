package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.WorkArrange;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.model.dto.WorkArrangeDto;
import group.cc.occ.service.WorkArrangeService;
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
* @date 2019/04/27
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/workArrange")
public class WorkArrangeController {
    @Resource
    private WorkArrangeService workArrangeService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 WorkArrange")
    @PostMapping("/add")
    public Result add(@RequestBody WorkArrange workArrange) {
        workArrangeService.save(workArrange);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 WorkArrange")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        workArrangeService.deleteUserWork(id);
        workArrangeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 WorkArrange")
    @PutMapping("/update")
    public Result update(@RequestBody WorkArrange workArrange) {
        WorkArrange old = this.workArrangeService.findById(workArrange.getId());
        if("已完成".equals(workArrange.getState()) && "是".equals(workArrange.getIsrepeat())){
            workArrange.setState("执行");
        }
        workArrangeService.update(workArrange);
        if(!old.equals(workArrange)){
            workArrangeService.updateUserWork(workArrange.getId(), workArrange.getIsrepeat());
        }
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 WorkArrange 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WorkArrange workArrange = workArrangeService.findById(id);
        return ResultGenerator.genSuccessResult(workArrange);
    }

    @ApiOperation("分页查询 WorkArrange 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<WorkArrange> list = workArrangeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 WorkArrange 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<WorkArrange> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        list = workArrangeService.listByKey(key, value, login);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 WorkArrange")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<WorkArrange> workArranges) {
        workArrangeService.deleteBatch(workArranges);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("为某工作安排分配用户")
    @PostMapping("/saveUserForWork")
    public Result saveUserForWork(@RequestBody WorkArrangeDto workArrangeDto) {
        this.workArrangeService.saveUserForWork(workArrangeDto);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("获取该工作安排负责的用户")
    @GetMapping("/getWorkUser")
    public Result getWorkUser(@RequestParam Integer workId) {
        List<Integer> users = this.workArrangeService.getWorkUser(workId);
        return ResultGenerator.genSuccessResult(users);
    }
}
