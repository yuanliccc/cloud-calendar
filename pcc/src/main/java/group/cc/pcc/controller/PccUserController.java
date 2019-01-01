package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccUser;
import group.cc.pcc.service.PccUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanli
 * @date 2018/11/19
 */
@RestController
@RequestMapping("/pcc/user")
public class PccUserController {
    @Resource
    private PccUserService pccUserService;

    @ApiOperation(value="添加 PccUser")
    @PostMapping
    public Result add(@RequestBody PccUser pccUser) {
        pccUserService.save(pccUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 PccUser")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 PccUser")
    @PutMapping
    public Result update(@RequestBody PccUser pccUser) {
        pccUserService.update(pccUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 PccUser 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccUser pccUser = pccUserService.findById(id);
        return ResultGenerator.genSuccessResult(pccUser);
    }

    @ApiOperation(value="分页查询 PccUser 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccUser> list = pccUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /* 新增 API */

    public Result detail(@RequestParam String username, @RequestParam String password) {
        PccUser pccUser = pccUserService.find(username, password);

        return ResultGenerator.genSuccessResult(pccUser);
    }
}
