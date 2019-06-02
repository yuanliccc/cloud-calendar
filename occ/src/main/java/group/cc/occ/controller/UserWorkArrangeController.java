package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.UserWorkArrange;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.UserWorkArrangeService;
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
* @ddate 2019/04/27
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/userWorkArrange")
public class UserWorkArrangeController {
    @Resource
    private UserWorkArrangeService userWorkArrangeService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @ApiOperation("添加 UserWorkArrange")
    @PostMapping("/add")
    public Result add(@RequestBody UserWorkArrange userWorkArrange) {
        userWorkArrangeService.save(userWorkArrange);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 UserWorkArrange")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userWorkArrangeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 UserWorkArrange")
    @PutMapping("/update")
    public Result update(@RequestBody UserWorkArrange userWorkArrange) {
        userWorkArrangeService.update(userWorkArrange);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 UserWorkArrange 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        UserWorkArrange userWorkArrange = userWorkArrangeService.findById(id);
        return ResultGenerator.genSuccessResult(userWorkArrange);
    }

    @ApiOperation("分页查询 UserWorkArrange 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<UserWorkArrange> list = userWorkArrangeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 UserWorkArrange 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<UserWorkArrange> list = null;
        if("".equals(key))
            list = userWorkArrangeService.findAll();
        else
            list = userWorkArrangeService.listByKey(key, value);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 UserWorkArrange")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<UserWorkArrange> userWorkArranges) {
        userWorkArrangeService.deleteBatch(userWorkArranges);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("")
    @GetMapping("/finish")
    public Result finish(@RequestParam() Integer workId){
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        userWorkArrangeService.finish(workId, login);
        return ResultGenerator.genSuccessResult();
    }
}
