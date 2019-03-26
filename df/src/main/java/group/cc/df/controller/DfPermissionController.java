package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfPermission;
import group.cc.df.service.DfPermissionService;
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
@RequestMapping("/df/permission")
public class DfPermissionController {
    @Resource
    private DfPermissionService dfPermissionService;

    @ApiOperation("添加 DfPermission")
    @PostMapping("/add")
    public Result add(DfPermission dfPermission) {
        dfPermissionService.save(dfPermission);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfPermission")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        dfPermissionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfPermission")
    @PostMapping("/update")
    public Result update(DfPermission dfPermission) {
        dfPermissionService.update(dfPermission);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfPermission 详情")
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        DfPermission dfPermission = dfPermissionService.findById(id);
        return ResultGenerator.genSuccessResult(dfPermission);
    }

    @ApiOperation("分页查询 DfPermission 列表")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfPermission> list = dfPermissionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
