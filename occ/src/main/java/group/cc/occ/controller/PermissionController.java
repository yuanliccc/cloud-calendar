package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultCode;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Permission;
import group.cc.occ.model.dto.PermissionDto;
import group.cc.occ.service.PermissionService;
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
@RequestMapping("/occ/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @ApiOperation("添加 Permission")
    @PostMapping("/add")
    public Result add(@RequestBody Permission permission) {
        permissionService.save(permission);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 Permission")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        permissionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 Permission")
    @PutMapping("/update")
    public Result update(@RequestBody Permission permission) {
        permissionService.update(permission);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 Permission 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Permission permission = permissionService.findById(id);
        return ResultGenerator.genSuccessResult(permission);
    }

    @ApiOperation("分页查询 Permission 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Permission> list = permissionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("获取该角色已分配的权限")
    @GetMapping("/getAllPermissionByRoleId")
    public Result getAllPermissionByRoleId(@RequestParam Integer roleId) {
        List<Permission> list = this.permissionService.getAllPermissionByRoleId(roleId);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("获取所有权限")
    @GetMapping("/getPermission")
    public Result getPermission() {
        List<Permission> list = permissionService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("分页查询 Permission 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<Permission> list = null;

        if("".equals(key))
            list = permissionService.findAll();
        else
            list = permissionService.listByKey(key, value);
        PageInfo<Permission> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 Module")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Permission> pers) {
        permissionService.deleteBatch(pers);
        return ResultGenerator.genSuccessResult();
    }
}
