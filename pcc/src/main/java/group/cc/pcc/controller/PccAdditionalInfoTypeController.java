package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccAdditionalInfoType;
import group.cc.pcc.service.PccAdditionalInfoTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yuanli
 * @date 2019/01/15
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/additional/info/type")
public class PccAdditionalInfoTypeController {
    @Resource
    private PccAdditionalInfoTypeService pccAdditionalInfoTypeService;

    @ApiOperation("添加 PccAdditionalInfoType")
    @PostMapping
    public Result add(@RequestBody PccAdditionalInfoType pccAdditionalInfoType) {
        pccAdditionalInfoTypeService.save(pccAdditionalInfoType);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccAdditionalInfoType")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccAdditionalInfoTypeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccAdditionalInfoType")
    @PutMapping
    public Result update(@RequestBody PccAdditionalInfoType pccAdditionalInfoType) {
        pccAdditionalInfoTypeService.update(pccAdditionalInfoType);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccAdditionalInfoType 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccAdditionalInfoType pccAdditionalInfoType = pccAdditionalInfoTypeService.findById(id);
        return ResultGenerator.genSuccessResult(pccAdditionalInfoType);
    }

    @ApiOperation("分页查询 PccAdditionalInfoType 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccAdditionalInfoType> list = pccAdditionalInfoTypeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("查询 PccAdditionalInfoType 列表")
    @GetMapping("list")
    public Result list(@RequestParam Integer pccScheduleId) {
        List<Map<String, Object>> list = pccAdditionalInfoTypeService.list(pccScheduleId);
        return ResultGenerator.genSuccessResult(list);
    }
}
