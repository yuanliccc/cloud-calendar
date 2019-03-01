package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.User;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import group.cc.occ.util.CusAccessObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wangyuming
 * @date 2019/03/01
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/user")
public class UserController {
    @Resource
    private UserService userService;

    @Autowired
    private HttpServletRequest request;


    @ApiOperation("添加 User")
    @PostMapping
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 User")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 User")
    @PutMapping
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 User 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @ApiOperation("分页查询 User 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation(value="登录")
    @GetMapping(value="/login")
    public Result login(@RequestParam()String account, @RequestParam()String password) {
        LoginUserDto loginUserDto = null;
        try {
            loginUserDto = userService.login(account, password);
            //redisTemplate.opsForValue().set("userInfo" + CusAccessObjectUtil.getIpAddress(request), loginUserDto);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }

        return ResultGenerator.genSuccessResult(loginUserDto);
    }

    @ApiOperation(value="注册")
    @PostMapping(value="/register")
    public Result register(@RequestBody User user) {
        LoginUserDto loginUserDto = null;
        HttpSession session = request.getSession(true);
        try {
            loginUserDto = userService.register(user);
            session.setAttribute("userInfo", loginUserDto);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult(loginUserDto);
    }

    @ApiOperation(value="注销登录")
    @PostMapping(value="/loginUp")
    public Result loginUp() {
        HttpSession session = request.getSession(true);
        session.removeAttribute("userInfo");
        return ResultGenerator.genSuccessResult();
    }
}
