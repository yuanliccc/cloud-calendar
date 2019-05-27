package group.cc.df.controller;

import com.alibaba.fastjson.JSONObject;
import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.dto.DfDynamicFormDTO;
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
@RequestMapping("/api/df/dynamic/form")
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
    public Result addDynamicForm(@RequestBody Map<String, Object> dfMap) {
        dfDynamicFormService.saveDynamicForm(dfMap);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过JSON字符串修改动态表单")
    @PutMapping("/updateDynamicForm")
    public Result updateDynamicForm(@RequestBody Map<String, Object> dfMap) {
        dfDynamicFormService.updateDynamicForm(dfMap);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfDynamicForm")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfDynamicFormService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除动态表单及其条目信息")
    @DeleteMapping("/deleteDynamicForm/{id}")
    public Result deleteDynamicForm(@PathVariable("id") Integer id) {
        this.dfDynamicFormService.deleteDynamicForm(id);
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

    @ApiOperation("自定义的根据页码和数据量分页查询表单数据")
    @GetMapping("/findDynamicFormByLimit")
    public Result findDynamicFormByLimit(Integer pageNum, Integer pageSize) {
        List<DfDynamicFormDTO> dfDynamicFormDTOList = this.dfDynamicFormService.findDynamicFormByLimit(pageSize, pageNum);
        return ResultGenerator.genSuccessResult(dfDynamicFormDTOList);
    }

    @ApiOperation("根据条件查询动态表单信息")
    @PostMapping("/findDynamicFormByCondition")
    @ResponseBody
    public Result findDynamicFormByCondition(@RequestBody Map<String, Object> conditionMap) {
        Map<String, Object> resultMap = this.dfDynamicFormService.findDynamicFormByCondition(conditionMap);
        return ResultGenerator.genSuccessResult(resultMap);
    }

    @ApiOperation("获取存储数据的总量")
    @GetMapping("/findDynamicFormCount")
    public Result findDynamicFormCount() {
        int dynamicFormCount = this.dfDynamicFormService.findDynamicFormCount();
        return ResultGenerator.genSuccessResult(dynamicFormCount);
    }

    @ApiOperation("分享表单")
    @GetMapping("/shareDynamicForm/{formId}")
    public Result shareDynamicForm(@PathVariable("formId") Integer formId) {
        this.dfDynamicFormService.shareDynamicForm(formId);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("发布表单")
    @GetMapping("/publishDynamicForm/{formId}")
    public Result publishDynamicForm(@PathVariable("formId") Integer formId) {
        this.dfDynamicFormService.publishDynamicForm(formId);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("关闭已经发布的表单")
    @GetMapping("/closePublishForm/{formId}")
    public Result closePublishForm(@PathVariable("formId") Integer formId) {
        this.dfDynamicFormService.closePublishForm(formId);
        return ResultGenerator.genSuccessResult();
    }
}
