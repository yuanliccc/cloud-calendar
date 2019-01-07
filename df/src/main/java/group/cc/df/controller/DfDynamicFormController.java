package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfDynamicForm;
import group.cc.df.service.DfDynamicFormService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gxd
 * @date 2018/12/07
 */
@RestController
@RequestMapping("/df/dynamic/form")
public class DfDynamicFormController {
    @Resource
    private DfDynamicFormService dfDynamicFormService;

    @ApiOperation(value="添加 DfDynamicForm")
    @PostMapping
    public Result add(@RequestBody DfDynamicForm dfDynamicForm) {
        dfDynamicFormService.save(dfDynamicForm);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 DfDynamicForm")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfDynamicFormService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 DfDynamicForm")
    @PutMapping
    public Result update(@RequestBody DfDynamicForm dfDynamicForm) {
        dfDynamicFormService.update(dfDynamicForm);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 DfDynamicForm 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfDynamicForm dfDynamicForm = dfDynamicFormService.findById(id);
        return ResultGenerator.genSuccessResult(dfDynamicForm);
    }

    @ApiOperation(value="分页查询 DfDynamicForm 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfDynamicForm> list = dfDynamicFormService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
