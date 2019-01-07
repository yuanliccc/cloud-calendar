package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfFormFieldType;
import group.cc.df.service.DfFormFieldTypeService;
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
@RequestMapping("/df/form/field/type")
public class DfFormFieldTypeController {
    @Resource
    private DfFormFieldTypeService dfFormFieldTypeService;

    @ApiOperation(value="添加 DfFormFieldType")
    @PostMapping
    public Result add(@RequestBody DfFormFieldType dfFormFieldType) {
        dfFormFieldTypeService.save(dfFormFieldType);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 DfFormFieldType")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfFormFieldTypeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 DfFormFieldType")
    @PutMapping
    public Result update(@RequestBody DfFormFieldType dfFormFieldType) {
        dfFormFieldTypeService.update(dfFormFieldType);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 DfFormFieldType 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfFormFieldType dfFormFieldType = dfFormFieldTypeService.findById(id);
        return ResultGenerator.genSuccessResult(dfFormFieldType);
    }

    @ApiOperation(value="分页查询 DfFormFieldType 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfFormFieldType> list = dfFormFieldTypeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
