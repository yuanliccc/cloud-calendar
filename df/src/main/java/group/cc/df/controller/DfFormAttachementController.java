package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfFormAttachement;
import group.cc.df.service.DfFormAttachementService;
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
@RequestMapping("/df/form/attachement")
public class DfFormAttachementController {
    @Resource
    private DfFormAttachementService dfFormAttachementService;

    @ApiOperation(value="添加 DfFormAttachement")
    @PostMapping
    public Result add(@RequestBody DfFormAttachement dfFormAttachement) {
        dfFormAttachementService.save(dfFormAttachement);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 DfFormAttachement")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfFormAttachementService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 DfFormAttachement")
    @PutMapping
    public Result update(@RequestBody DfFormAttachement dfFormAttachement) {
        dfFormAttachementService.update(dfFormAttachement);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 DfFormAttachement 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfFormAttachement dfFormAttachement = dfFormAttachementService.findById(id);
        return ResultGenerator.genSuccessResult(dfFormAttachement);
    }

    @ApiOperation(value="分页查询 DfFormAttachement 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfFormAttachement> list = dfFormAttachementService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
