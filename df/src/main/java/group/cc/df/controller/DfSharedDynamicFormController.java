package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfSharedDynamicForm;
import group.cc.df.service.DfSharedDynamicFormService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gxd
 * @date 2019/04/09
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/df/shared/dynamic/form")
public class DfSharedDynamicFormController {
    @Resource
    private DfSharedDynamicFormService dfSharedDynamicFormService;

    @ApiOperation("添加 DfSharedDynamicForm")
    @PostMapping("/add")
    public Result add(DfSharedDynamicForm dfSharedDynamicForm) {
        dfSharedDynamicFormService.save(dfSharedDynamicForm);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfSharedDynamicForm")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        dfSharedDynamicFormService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfSharedDynamicForm")
    @PostMapping("/update")
    public Result update(DfSharedDynamicForm dfSharedDynamicForm) {
        dfSharedDynamicFormService.update(dfSharedDynamicForm);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfSharedDynamicForm 详情")
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        DfSharedDynamicForm dfSharedDynamicForm = dfSharedDynamicFormService.findById(id);
        return ResultGenerator.genSuccessResult(dfSharedDynamicForm);
    }

    @ApiOperation("分页查询 DfSharedDynamicForm 列表")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfSharedDynamicForm> list = dfSharedDynamicFormService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
