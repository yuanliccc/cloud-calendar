package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Lastloginorg;
import group.cc.occ.service.LastloginorgService;
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
@RequestMapping("/lastloginorg")
public class LastloginorgController {
    @Resource
    private LastloginorgService lastloginorgService;

    @ApiOperation(value="添加 Lastloginorg")
    @PostMapping
    public Result add(@RequestBody Lastloginorg lastloginorg) {
        lastloginorgService.save(lastloginorg);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 Lastloginorg")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        lastloginorgService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 Lastloginorg")
    @PutMapping
    public Result update(@RequestBody Lastloginorg lastloginorg) {
        lastloginorgService.update(lastloginorg);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 Lastloginorg 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Lastloginorg lastloginorg = lastloginorgService.findById(id);
        return ResultGenerator.genSuccessResult(lastloginorg);
    }

    @ApiOperation(value="分页查询 Lastloginorg 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Lastloginorg> list = lastloginorgService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
