package group.cc.df.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.df.dto.LoginUserInfoDTO;
import group.cc.df.model.DfUser;
import group.cc.df.service.DfUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author gxd
 * @date 2019/02/16
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/df/user")
public class DfUserController {
    @Resource
    private DfUserService dfUserService;

    @ApiOperation("添加 DfUser")
    @PostMapping
    public Result add(@RequestBody DfUser dfUser) {
        dfUserService.save(dfUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 DfUser")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dfUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 DfUser")
    @PutMapping
    public Result update(@RequestBody DfUser dfUser) {
        dfUserService.update(dfUser);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 DfUser 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        DfUser dfUser = dfUserService.findById(id);
        return ResultGenerator.genSuccessResult(dfUser);
    }

    @ApiOperation("分页查询 DfUser 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DfUser> list = dfUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginUserInfoDTO loginUserInfoDTO) {
        String identityCode = loginUserInfoDTO.getIdentityCode();
        String verifyPassword = loginUserInfoDTO.getVerifyPassword();

        DfUser user = dfUserService.verifyUser(identityCode, verifyPassword);

        return ResultGenerator.genSuccessResult(user);
    }
}
