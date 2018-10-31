package group.cc.bms.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.bms.model.Org;
import group.cc.bms.service.OrgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanli
 * @date 2018/11/01
 */
@RestController
@RequestMapping("/org")
public class OrgController {
    @Resource
    private OrgService orgService;

    @ApiOperation(value="添加 Org")
    @PostMapping
    public Result add(@RequestBody Org org) {
        orgService.save(org);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 Org")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        orgService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 Org")
    @PutMapping
    public Result update(@RequestBody Org org) {
        orgService.update(org);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 Org 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Org org = orgService.findById(id);
        return ResultGenerator.genSuccessResult(org);
    }

    @ApiOperation(value="分页查询 Org 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Org> list = orgService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
