package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.dto.DfFieldComponentDTO;
import group.cc.df.model.DfFormField;
import group.cc.df.service.DfFormFieldService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gxd
 * @date 2019/01/10
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/df/form/field")
public class DfFormFieldController {
    @Resource
    private DfFormFieldService dfFormFieldService;

    @ApiOperation("添加 DfFormField")
    @PostMapping
    public Result add(@RequestBody DfFormField dfFormField) {
        dfFormFieldService.save(dfFormField);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfFormField")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfFormFieldService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfFormField")
    @PutMapping
    public Result update(@RequestBody DfFormField dfFormField) {
        dfFormFieldService.update(dfFormField);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfFormField 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfFormField dfFormField = dfFormFieldService.findById(id);
        return ResultGenerator.genSuccessResult(dfFormField);
    }

    @ApiOperation("分页查询 DfFormField 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfFormField> list = dfFormFieldService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("按照表单Id查询对应的表单域")
    @GetMapping("/findDynamicFormFieldsByFormId/{formId}")
    public Result findDynamicFormFieldsByFormId(@PathVariable Integer formId) {
        List<DfFieldComponentDTO> list = dfFormFieldService.findDynamicFormFieldsByFormId(formId);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("根据收集表的Id查询收集表的表单域信息")
    @GetMapping("/findCollectFormFieldByCollectFormId/{formId}")
    public Result findCollectFormFieldByCollectFormId(@PathVariable("formId") Integer formId) {
        List<DfFormField> formFieldList = this.dfFormFieldService.findCollectFormFieldByCollectFormId(formId);
        return ResultGenerator.genSuccessResult(formFieldList);
    }
}
