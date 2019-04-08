package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultCode;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Module;
import group.cc.occ.model.Role;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.model.dto.PermissionDto;
import group.cc.occ.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import group.cc.occ.util.CusAccessObjectUtil;
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
 * @date 2019/03/01
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 Role")
    @PostMapping("/add")
    public Result add(@RequestBody Role role) {
        if(role.getRolelevel() == 6)
            return ResultGenerator.genFailResult("您无法创建系统管理员级别的角色，请重试!").setCode(ResultCode.FAIL);

        roleService.save(role);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 Role")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        roleService.deleteRole(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 Role")
    @PutMapping("/update")
    public Result update(@RequestBody Role role) {
        roleService.update(role);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 Role 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Role role = roleService.findById(id);
        return ResultGenerator.genSuccessResult(role);
    }

    @ApiOperation("分页查询 Role 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Role> list = roleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 Role 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<Role> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        //需要重新判断该用户是否拥有此角色（未完成）
        if(login == null)
            return new Result().setCode(ResultCode.UNAUTHORIZED).setMessage("用户未登录！");

        if("".equals(key))
            list = roleService.findAllByLoginOrg(login);
        else
            list = roleService.listByKey(key, value, login);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 Role")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Role> roles) {
        roleService.deleteBatch(roles);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("分配权限")
    @PostMapping("/assignPer")
    public Result assignPer(@RequestBody PermissionDto permissionDto) {
        this.roleService.assignPer(permissionDto);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("查询 Role 列表")
    @GetMapping("/getRoles")
    public Result getRoles() {
        List<Role> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        //需要重新判断该用户是否拥有此角色（未完成）
        if(login == null)
            return new Result().setCode(ResultCode.UNAUTHORIZED).setMessage("用户未登录！");

        list = roleService.findAllByLoginOrg(login);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("分配角色")
    @GetMapping("/distributeRole")
    public Result distributeRole(@RequestParam()Integer userId, @RequestParam()Integer roleId){
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        //需要重新判断该用户是否拥有此角色（未完成）
        if(login == null)
            return new Result().setCode(ResultCode.UNAUTHORIZED).setMessage("用户未登录！");
        roleService.distributeRole(userId, roleId, login);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("根据用户ID获取角色信息")
    @GetMapping("/getRoleByUserId")
    public Result getRoleByUserId(@RequestParam()Integer userId){
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        //需要重新判断该用户是否拥有此角色（未完成）
        if(login == null)
            return new Result().setCode(ResultCode.UNAUTHORIZED).setMessage("用户未登录！");
        Role role = roleService.getRoleByUserId(userId, login.getOrganization().getId());
        return ResultGenerator.genSuccessResult(role);
    }
}
