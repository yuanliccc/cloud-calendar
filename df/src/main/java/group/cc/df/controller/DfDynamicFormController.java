package group.cc.df.controller;

import com.alibaba.fastjson.JSONObject;
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
import java.util.Map;

/**
 * @author gxd
 * @date 2019/01/10
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/df/dynamic/form")
public class DfDynamicFormController {
    @Resource
    private DfDynamicFormService dfDynamicFormService;

    @ApiOperation("添加 DfDynamicForm")
    @PostMapping
    public Result add(@RequestBody DfDynamicForm dfDynamicForm) {
        dfDynamicFormService.save(dfDynamicForm);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过JSON字符串添加动态表单")
    @PostMapping("/addDynamicForm")
    public Result add(@RequestBody Map<String, Object> formJson) {
        System.out.println(formJson);
        return null;
    }

    @ApiOperation("删除 DfDynamicForm")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfDynamicFormService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfDynamicForm")
    @PutMapping
    public Result update(@RequestBody DfDynamicForm dfDynamicForm) {
        dfDynamicFormService.update(dfDynamicForm);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfDynamicForm 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfDynamicForm dfDynamicForm = dfDynamicFormService.findById(id);
        return ResultGenerator.genSuccessResult(dfDynamicForm);
    }

    @ApiOperation("分页查询 DfDynamicForm 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfDynamicForm> list = dfDynamicFormService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
