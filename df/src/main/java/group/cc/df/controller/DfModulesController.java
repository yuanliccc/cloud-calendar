package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfModules;
import group.cc.df.service.DfModulesService;
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
@RequestMapping("/df/modules")
public class DfModulesController {
    @Resource
    private DfModulesService dfModulesService;

    @ApiOperation("添加 DfModules")
    @PostMapping("/add")
    public Result add(DfModules dfModules) {
        dfModulesService.save(dfModules);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfModules")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        dfModulesService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfModules")
    @PostMapping("/update")
    public Result update(DfModules dfModules) {
        dfModulesService.update(dfModules);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfModules 详情")
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        DfModules dfModules = dfModulesService.findById(id);
        return ResultGenerator.genSuccessResult(dfModules);
    }

    @ApiOperation("分页查询 DfModules 列表")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfModules> list = dfModulesService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
