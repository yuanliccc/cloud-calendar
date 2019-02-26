package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.User;
import group.cc.occ.service.UserService;
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
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value="添加 User")
    @PostMapping
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 User")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 User")
    @PutMapping
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 User 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @ApiOperation(value="分页查询 User 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation(value="登录")
    @GetMapping(value="/login")
    public Result login(String account, String password) {
        userService.login(account, password);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="注册")
    @PostMapping(value="/register")
    public Result register(@RequestBody User user) {
        try {
            user = userService.register(user);
        }catch (Exception e){
            return ResultGenerator.genFailResult("账号已存在！");
        }
        if(user == null)
            return ResultGenerator.genFailResult("账号密码不能为空！");

        return ResultGenerator.genSuccessResult(user);
    }
}
