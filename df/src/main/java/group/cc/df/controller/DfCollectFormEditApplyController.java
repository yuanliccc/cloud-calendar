package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfCollectFormEditApply;
import group.cc.df.service.DfCollectFormEditApplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gxd
 * @date 2019/05/14
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/df/collect/form/edit/apply")
public class DfCollectFormEditApplyController {
    @Resource
    private DfCollectFormEditApplyService dfCollectFormEditApplyService;

    @ApiOperation("添加 DfCollectFormEditApply")
    @PostMapping
    public Result add(@RequestBody DfCollectFormEditApply dfCollectFormEditApply) {
        dfCollectFormEditApplyService.save(dfCollectFormEditApply);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfCollectFormEditApply")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfCollectFormEditApplyService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfCollectFormEditApply")
    @PutMapping
    public Result update(@RequestBody DfCollectFormEditApply dfCollectFormEditApply) {
        dfCollectFormEditApplyService.update(dfCollectFormEditApply);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfCollectFormEditApply 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfCollectFormEditApply dfCollectFormEditApply = dfCollectFormEditApplyService.findById(id);
        return ResultGenerator.genSuccessResult(dfCollectFormEditApply);
    }

    @ApiOperation("分页查询 DfCollectFormEditApply 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfCollectFormEditApply> list = dfCollectFormEditApplyService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
