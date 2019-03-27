package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.dto.DfLoginUserInfoDTO;
import group.cc.df.dto.DfSimpleUserInfo;
import group.cc.df.model.DfUser;
import group.cc.df.service.DfUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author gxd
 * @date 2019/03/25
 */
@CrossOrigin
/*@RestController*/
@RequestMapping("/api/df/user")
public class DfUserController {
    @Resource
    private DfUserService dfUserService;

    @ApiOperation("添加 DfUser")
    @PostMapping("/add")
    public Result add(@RequestBody DfUser dfUser) {
        dfUserService.save(dfUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfUser")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        dfUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfUser")
    @PostMapping("/update")
    public Result update(DfUser dfUser) {
        dfUserService.update(dfUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfUser 详情")
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        DfUser dfUser = dfUserService.findById(id);
        return ResultGenerator.genSuccessResult(dfUser);
    }

    @ApiOperation("分页查询 DfUser 列表")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfUser> list = dfUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    /**
     * 将登录的用户,密码传入UsernamePasswordToken,当调用subject.login时,
     * 将调用Relam的doGetAuthenticationInfo方法,开始密码验证
     * 此时执行我们自己编写的密码匹配器
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody DfLoginUserInfoDTO loginUserInfo) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginUserInfo.getUserName(),
                loginUserInfo.getPassword());
        Session session = SecurityUtils.getSubject().getSession();
        Subject subject = SecurityUtils.getSubject();

        DfUser user = null;
        DfSimpleUserInfo userInfo = new DfSimpleUserInfo();
        try {
            subject.login(token);
            user = (DfUser) subject.getPrincipal();
            session.setAttribute("user", user);
            userInfo.setName(user.getName());
            userInfo.setId(user.getId());

        } catch (AuthenticationException e) {
            return ResultGenerator.genErrorResult("用户名或密码错误");
        } catch (InvalidSessionException e) {
            return ResultGenerator.genErrorResult("用户名或密码错误");
        }

        return user == null ? ResultGenerator.genFailResult("用户名或密码错误") : ResultGenerator.genSuccessResult(userInfo);
    }

    @ApiOperation("判断用户是否登录")
    @GetMapping("/isLogin")
    @ResponseBody
    public Result isLogin() {
        Session session = SecurityUtils.getSubject().getSession();
        DfUser user = (DfUser) session.getAttribute("user");

        if (user == null) {
            return ResultGenerator.genFailResult("未登录");
        }

        DfSimpleUserInfo userInfo = new DfSimpleUserInfo();
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());

        return ResultGenerator.genSuccessResult(userInfo);
    }
}
