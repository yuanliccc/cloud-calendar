package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccScheduleUser;
import group.cc.pcc.service.PccScheduleUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanli
 * @date 2019/01/10
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/schedule/user")
public class PccScheduleUserController {
    @Resource
    private PccScheduleUserService pccScheduleUserService;

    @ApiOperation("添加 PccScheduleUser")
    @PostMapping
    public Result add(@RequestBody PccScheduleUser pccScheduleUser) {
        pccScheduleUserService.save(pccScheduleUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccScheduleUser")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccScheduleUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccScheduleUser")
    @PutMapping
    public Result update(@RequestBody PccScheduleUser pccScheduleUser) {
        pccScheduleUserService.update(pccScheduleUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccScheduleUser 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccScheduleUser pccScheduleUser = pccScheduleUserService.findById(id);
        return ResultGenerator.genSuccessResult(pccScheduleUser);
    }

    @ApiOperation("分页查询 PccScheduleUser 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccScheduleUser> list = pccScheduleUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
