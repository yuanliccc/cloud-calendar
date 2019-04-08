package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfUserAndRole;
import group.cc.df.service.DfUserAndRoleService;
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
@RequestMapping("/df/user/and/role")
public class DfUserAndRoleController {
    @Resource
    private DfUserAndRoleService dfUserAndRoleService;

    @ApiOperation("添加 DfUserAndRole")
    @PostMapping("/add")
    public Result add(DfUserAndRole dfUserAndRole) {
        dfUserAndRoleService.save(dfUserAndRole);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfUserAndRole")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        dfUserAndRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfUserAndRole")
    @PostMapping("/update")
    public Result update(DfUserAndRole dfUserAndRole) {
        dfUserAndRoleService.update(dfUserAndRole);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfUserAndRole 详情")
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        DfUserAndRole dfUserAndRole = dfUserAndRoleService.findById(id);
        return ResultGenerator.genSuccessResult(dfUserAndRole);
    }

    @ApiOperation("分页查询 DfUserAndRole 列表")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfUserAndRole> list = dfUserAndRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
