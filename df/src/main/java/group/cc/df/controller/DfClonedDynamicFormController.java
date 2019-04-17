package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfClonedDynamicForm;
import group.cc.df.service.DfClonedDynamicFormService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gxd
 * @date 2019/04/12
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/df/cloned/dynamic/form")
public class DfClonedDynamicFormController {
    @Resource
    private DfClonedDynamicFormService dfClonedDynamicFormService;

    @ApiOperation("添加 DfClonedDynamicForm")
    @PostMapping
    public Result add(@RequestBody DfClonedDynamicForm dfClonedDynamicForm) {
        dfClonedDynamicFormService.save(dfClonedDynamicForm);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfClonedDynamicForm")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfClonedDynamicFormService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfClonedDynamicForm")
    @PutMapping
    public Result update(@RequestBody DfClonedDynamicForm dfClonedDynamicForm) {
        dfClonedDynamicFormService.update(dfClonedDynamicForm);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfClonedDynamicForm 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfClonedDynamicForm dfClonedDynamicForm = dfClonedDynamicFormService.findById(id);
        return ResultGenerator.genSuccessResult(dfClonedDynamicForm);
    }

    @ApiOperation("分页查询 DfClonedDynamicForm 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfClonedDynamicForm> list = dfClonedDynamicFormService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
