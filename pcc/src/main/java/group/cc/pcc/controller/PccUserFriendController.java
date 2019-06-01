package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccUserFriend;
import group.cc.pcc.service.PccNoticeService;
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

    @Resource
    private PccNoticeService pccNoticeService;

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

    @ApiOperation("修改remark")
    @GetMapping("/remark")
    public Result remark(@RequestParam("pccUserId") Integer pccUserId,
                         @RequestParam("friendPccUserId") Integer friendPccUserId,
                         @RequestParam("remark") String remark) {
        pccUserFriendService.remark(pccUserId, friendPccUserId, remark);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("delete")
    @GetMapping("/delete")
    public Result delete(@RequestParam("pccUserId") Integer pccUserId,
                         @RequestParam("friendPccUserId") Integer friendPccUserId) {
        pccUserFriendService.deleteByIdes(pccUserId, friendPccUserId);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("加好友实际操作")
    @GetMapping("/save/friend")
    public Result saveFriend(@RequestParam("pccUserId") Integer pccUserId,
                         @RequestParam("friendPccUserId") Integer friendPccUserId) {
        if(pccUserFriendService.isFriend(pccUserId, friendPccUserId)) {
            return ResultGenerator.genSuccessResult("你们已经是好友了");
        }

        pccUserFriendService.saveFriend(pccUserId, friendPccUserId);
        return ResultGenerator.genSuccessResult();
    }

    public Result friendApply(@RequestParam("pccUserId") Integer pccUserId,
                              @RequestParam("friendPccUserId") Integer friendPccUserId) {

        pccUserFriendService.friendApply(pccUserId, friendPccUserId);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("加好友实际操作")
    @GetMapping("/apply")
    public Result friendApplyEmail(@RequestParam("pccUserId") Integer pccUserId,
                              @RequestParam("email") String email) {

        if(pccUserFriendService.isFriend(pccUserId, email)) {
            return ResultGenerator.genSuccessResult("你们已经是好友了");
        }

        if(pccNoticeService.isApply(pccUserId, email)) {
            return ResultGenerator.genSuccessResult("已经提交过申请了，请勿重复申请");
        }

        pccUserFriendService.friendApplyEmail(pccUserId, email);
        return ResultGenerator.genSuccessResult("以提交好友申请，请等待对方同意");
    }
}
