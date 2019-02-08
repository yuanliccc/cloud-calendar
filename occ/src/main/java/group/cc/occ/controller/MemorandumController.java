package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Memorandum;
import group.cc.occ.service.MemorandumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangyuming
 * @date 2019/01/02
 */
@RestController
@RequestMapping("/memorandum")
public class MemorandumController {
    @Resource
    private MemorandumService memorandumService;

    @ApiOperation(value="添加 Memorandum")
    @PostMapping
    public Result add(@RequestBody Memorandum memorandum) {
        memorandumService.save(memorandum);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 Memorandum")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        memorandumService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 Memorandum")
    @PutMapping
    public Result update(@RequestBody Memorandum memorandum) {
        memorandumService.update(memorandum);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 Memorandum 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Memorandum memorandum = memorandumService.findById(id);
        return ResultGenerator.genSuccessResult(memorandum);
    }

    @ApiOperation(value="分页查询 Memorandum 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Memorandum> list = memorandumService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
