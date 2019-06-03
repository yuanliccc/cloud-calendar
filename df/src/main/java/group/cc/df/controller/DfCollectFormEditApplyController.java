package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.dto.DfCollectFormEditApplyDTO;
import group.cc.df.model.DfCollectFormEditApply;
import group.cc.df.service.DfCollectFormEditApplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author gxd
 * @date 2019/05/14
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/df/collect/form/edit/apply")
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

    @ApiOperation("提交编辑收集表单申请")
    @PostMapping("/submitCollectFormEditApply")
    public Result submitCollectFormEditApply(@RequestBody DfCollectFormEditApply applyInfo) {
        this.dfCollectFormEditApplyService.submitCollectFormEditApply(applyInfo);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("分页查询申请信息")
    @PostMapping("/findCollectFormEditApply")
    public Result findCollectFormEditApply(@RequestBody Map<String, Object> conditionMap) {
        PageInfo<DfCollectFormEditApplyDTO> pageInfo = this.dfCollectFormEditApplyService.findCollectFormEditApply(conditionMap);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("通过申请")
    @GetMapping("/adoptApply/{applyId}")
    public Result adoptApply(@PathVariable("applyId") Integer applyId) {
        this.dfCollectFormEditApplyService.adoptApply(applyId);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("拒绝申请")
    @GetMapping("/refuseApply/{applyId}")
    public Result refuseApply(@PathVariable("applyId") Integer applyId) {
        this.dfCollectFormEditApplyService.refuseApply(applyId);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("根据表单名称模糊查询当前用户需要审批的表单信息")
    @GetMapping("/findFormLikeFormName/{formName}")
    public Result findFormLikeFormName(@PathVariable("formName") String formName) {
        List<DfCollectFormEditApplyDTO> dto = this.dfCollectFormEditApplyService.findFormLikeFormName(formName);
        return ResultGenerator.genSuccessResult(dto);
    }
}
