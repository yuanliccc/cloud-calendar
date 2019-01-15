package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.AdditionalInfoType;
import group.cc.pcc.service.AdditionalInfoTypeService;
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
@RequestMapping("/additional/info/type")
public class AdditionalInfoTypeController {
    @Resource
    private AdditionalInfoTypeService additionalInfoTypeService;

    @ApiOperation("添加 AdditionalInfoType")
    @PostMapping
    public Result add(@RequestBody AdditionalInfoType additionalInfoType) {
        additionalInfoTypeService.save(additionalInfoType);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 AdditionalInfoType")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        additionalInfoTypeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 AdditionalInfoType")
    @PutMapping
    public Result update(@RequestBody AdditionalInfoType additionalInfoType) {
        additionalInfoTypeService.update(additionalInfoType);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 AdditionalInfoType 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        AdditionalInfoType additionalInfoType = additionalInfoTypeService.findById(id);
        return ResultGenerator.genSuccessResult(additionalInfoType);
    }

    @ApiOperation("分页查询 AdditionalInfoType 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<AdditionalInfoType> list = additionalInfoTypeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
