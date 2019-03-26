package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfRoleAndPermission;
import group.cc.df.service.DfRoleAndPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gxd
 * @date 2019/03/25
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/df/role/and/permission")
public class DfRoleAndPermissionController {
    @Resource
    private DfRoleAndPermissionService dfRoleAndPermissionService;

    @ApiOperation("添加 DfRoleAndPermission")
    @PostMapping("/add")
    public Result add(DfRoleAndPermission dfRoleAndPermission) {
        dfRoleAndPermissionService.save(dfRoleAndPermission);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfRoleAndPermission")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        dfRoleAndPermissionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfRoleAndPermission")
    @PostMapping("/update")
    public Result update(DfRoleAndPermission dfRoleAndPermission) {
        dfRoleAndPermissionService.update(dfRoleAndPermission);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfRoleAndPermission 详情")
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        DfRoleAndPermission dfRoleAndPermission = dfRoleAndPermissionService.findById(id);
        return ResultGenerator.genSuccessResult(dfRoleAndPermission);
    }

    @ApiOperation("分页查询 DfRoleAndPermission 列表")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfRoleAndPermission> list = dfRoleAndPermissionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
