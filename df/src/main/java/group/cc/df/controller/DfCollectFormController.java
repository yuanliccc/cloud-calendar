package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfCollectForm;
import group.cc.df.service.DfCollectFormService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author gxd
 * @date 2019/04/16
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/df/collect/form")
public class DfCollectFormController {
    @Resource
    private DfCollectFormService dfCollectFormService;

    @ApiOperation("添加 DfCollectForm")
    @PostMapping
    public Result add(@RequestBody DfCollectForm dfCollectForm) {
        dfCollectFormService.save(dfCollectForm);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfCollectForm")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfCollectFormService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfCollectForm")
    @PutMapping
    public Result update(@RequestBody DfCollectForm dfCollectForm) {
        dfCollectFormService.update(dfCollectForm);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfCollectForm 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfCollectForm dfCollectForm = dfCollectFormService.findById(id);
        return ResultGenerator.genSuccessResult(dfCollectForm);
    }

    @ApiOperation("分页查询 DfCollectForm 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfCollectForm> list = dfCollectFormService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("保存收集表单信息")
    @PostMapping("/saveCollectForm")
    public Result saveCollectForm(@RequestBody Map<String, Object> collectFormMap) {
        this.dfCollectFormService.saveCollectForm(collectFormMap);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("查询当前用户所填写对应Id的收集表单信息")
    @GetMapping("/findSelfCollectFormByFormId/{formId}")
    public Result findSelfCollectFormByFormId(@PathVariable("formId") Integer formId) {
        DfCollectForm collectForm = this.dfCollectFormService.findSelfCollectFormByFormId(formId);
        return ResultGenerator.genSuccessResult(collectForm);
    }
}
