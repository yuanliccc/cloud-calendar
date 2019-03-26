package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfRole;
import group.cc.df.service.DfRoleService;
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
@RequestMapping("/df/role")
public class DfRoleController {
    @Resource
    private DfRoleService dfRoleService;

    @ApiOperation("添加 DfRole")
    @PostMapping("/add")
    public Result add(DfRole dfRole) {
        dfRoleService.save(dfRole);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfRole")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        dfRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfRole")
    @PostMapping("/update")
    public Result update(DfRole dfRole) {
        dfRoleService.update(dfRole);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfRole 详情")
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        DfRole dfRole = dfRoleService.findById(id);
        return ResultGenerator.genSuccessResult(dfRole);
    }

    @ApiOperation("分页查询 DfRole 列表")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfRole> list = dfRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
