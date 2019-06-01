package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.SystemConfig;
import group.cc.occ.service.SystemConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
* @author wangyuming
* @date 2019/04/27
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/system/config")
public class SystemConfigController {
    @Resource
    private SystemConfigService systemConfigService;

    @ApiOperation("添加 SystemConfig")
    @PostMapping("/add")
    public Result add(@RequestBody SystemConfig systemConfig) {
        systemConfigService.save(systemConfig);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 SystemConfig")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        systemConfigService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 SystemConfig")
    @PutMapping("/update")
    public Result update(@RequestBody SystemConfig systemConfig) {
        systemConfigService.update(systemConfig);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 SystemConfig 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SystemConfig systemConfig = systemConfigService.findById(id);
        return ResultGenerator.genSuccessResult(systemConfig);
    }

    @ApiOperation("分页查询 SystemConfig 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SystemConfig> list = systemConfigService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 SystemConfig 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<SystemConfig> list = null;
        if("".equals(key))
            list = systemConfigService.findAll();
        else
            list = systemConfigService.listByKey(key, value);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 SystemConfig")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<SystemConfig> systemConfigs) {
        systemConfigService.deleteBatch(systemConfigs);
        return ResultGenerator.genSuccessResult();
    }
}
