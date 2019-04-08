package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.model.DfFormAttachment;
import group.cc.df.service.DfFormAttachmentService;
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
@RequestMapping("/api/df/form/attachment")
public class DfFormAttachmentController {
    @Resource
    private DfFormAttachmentService dfFormAttachmentService;

    @ApiOperation("添加 DfFormAttachment")
    @PostMapping
    public Result add(@RequestBody DfFormAttachment dfFormAttachment) {
        dfFormAttachmentService.save(dfFormAttachment);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfFormAttachment")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfFormAttachmentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfFormAttachment")
    @PutMapping
    public Result update(@RequestBody DfFormAttachment dfFormAttachment) {
        dfFormAttachmentService.update(dfFormAttachment);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfFormAttachment 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfFormAttachment dfFormAttachment = dfFormAttachmentService.findById(id);
        return ResultGenerator.genSuccessResult(dfFormAttachment);
    }

    @ApiOperation("分页查询 DfFormAttachment 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfFormAttachment> list = dfFormAttachmentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
