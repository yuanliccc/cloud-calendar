package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccText;
import group.cc.pcc.service.PccTextService;
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
@RequestMapping("/pcc/text")
public class PccTextController {
    @Resource
    private PccTextService pccTextService;

    @ApiOperation("添加 PccText")
    @PostMapping
    public Result add(@RequestBody PccText pccText) {
        pccTextService.save(pccText);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccText")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccTextService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccText")
    @PutMapping
    public Result update(@RequestBody PccText pccText) {
        pccTextService.update(pccText);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccText 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccText pccText = pccTextService.findById(id);
        return ResultGenerator.genSuccessResult(pccText);
    }

    @ApiOperation("分页查询 PccText 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccText> list = pccTextService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
