package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccScheduleAdditionalType;
import group.cc.pcc.service.PccScheduleAdditionalTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanli
 * @date 2019/01/15
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/schedule/additional/type")
public class PccScheduleAdditionalTypeController {
    @Resource
    private PccScheduleAdditionalTypeService pccScheduleAdditionalTypeService;

    @ApiOperation("添加 PccScheduleAdditionalType")
    @PostMapping
    public Result add(@RequestBody PccScheduleAdditionalType pccScheduleAdditionalType) {
        pccScheduleAdditionalTypeService.save(pccScheduleAdditionalType);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccScheduleAdditionalType")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccScheduleAdditionalTypeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccScheduleAdditionalType")
    @PutMapping
    public Result update(@RequestBody PccScheduleAdditionalType pccScheduleAdditionalType) {
        pccScheduleAdditionalTypeService.update(pccScheduleAdditionalType);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccScheduleAdditionalType 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccScheduleAdditionalType pccScheduleAdditionalType = pccScheduleAdditionalTypeService.findById(id);
        return ResultGenerator.genSuccessResult(pccScheduleAdditionalType);
    }

    @ApiOperation("分页查询 PccScheduleAdditionalType 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccScheduleAdditionalType> list = pccScheduleAdditionalTypeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
