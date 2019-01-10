package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfFormItem;
import group.cc.df.service.DfFormItemService;
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
@RequestMapping("/df/form/item")
public class DfFormItemController {
    @Resource
    private DfFormItemService dfFormItemService;

    @ApiOperation("添加 DfFormItem")
    @PostMapping
    public Result add(@RequestBody DfFormItem dfFormItem) {
        dfFormItemService.save(dfFormItem);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfFormItem")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfFormItemService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfFormItem")
    @PutMapping
    public Result update(@RequestBody DfFormItem dfFormItem) {
        dfFormItemService.update(dfFormItem);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfFormItem 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfFormItem dfFormItem = dfFormItemService.findById(id);
        return ResultGenerator.genSuccessResult(dfFormItem);
    }

    @ApiOperation("分页查询 DfFormItem 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfFormItem> list = dfFormItemService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
