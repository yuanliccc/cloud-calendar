package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultCode;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Module;
import group.cc.occ.model.Role;
import group.cc.occ.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
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
    public Result detail(@PathVariable Integer id) {
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
        if("".equals(key))
            list = roleService.findAll();
        else
            list = roleService.listByKey(key, value);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 Role")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Role> roles) {
        roleService.deleteBatch(roles);
        return ResultGenerator.genSuccessResult();
    }
}
