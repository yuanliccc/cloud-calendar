package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccUserFriend;
import group.cc.pcc.service.PccUserFriendService;
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
@RequestMapping("/pcc/user/friend")
public class PccUserFriendController {
    @Resource
    private PccUserFriendService pccUserFriendService;

    @ApiOperation("添加 PccUserFriend")
    @PostMapping
    public Result add(@RequestBody PccUserFriend pccUserFriend) {
        pccUserFriendService.save(pccUserFriend);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccUserFriend")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccUserFriendService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccUserFriend")
    @PutMapping
    public Result update(@RequestBody PccUserFriend pccUserFriend) {
        pccUserFriendService.update(pccUserFriend);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccUserFriend 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccUserFriend pccUserFriend = pccUserFriendService.findById(id);
        return ResultGenerator.genSuccessResult(pccUserFriend);
    }

    @ApiOperation("分页查询 PccUserFriend 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccUserFriend> list = pccUserFriendService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
