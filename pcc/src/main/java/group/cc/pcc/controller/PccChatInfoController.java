package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccChatInfo;
import group.cc.pcc.service.PccChatInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanli
 * @date 2019/05/30
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/chat/info")
public class PccChatInfoController {
    @Resource
    private PccChatInfoService pccChatInfoService;

    @ApiOperation("添加 PccChatInfo")
    @PostMapping
    public Result add(@RequestBody PccChatInfo pccChatInfo) {
        pccChatInfoService.save(pccChatInfo);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccChatInfo")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccChatInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccChatInfo")
    @PutMapping
    public Result update(@RequestBody PccChatInfo pccChatInfo) {
        pccChatInfoService.update(pccChatInfo);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccChatInfo 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccChatInfo pccChatInfo = pccChatInfoService.findById(id);
        return ResultGenerator.genSuccessResult(pccChatInfo);
    }

    @ApiOperation("分页查询 PccChatInfo 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccChatInfo> list = pccChatInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("获取指定发送人与接收人的消息列表")
    @GetMapping("/info")
    public Result friendChatInfo(@RequestParam("sendUserId") Integer sendUserId
            , @RequestParam("receiveUserId") Integer receiveUserId) {
        List<PccChatInfo> friendChatInfo = pccChatInfoService.friendChatInfo(sendUserId, receiveUserId);
        return ResultGenerator.genSuccessResult(friendChatInfo);
    }

    @ApiOperation("未查看消息总数")
    @GetMapping("/count")
    public Integer count(@RequestParam("receiveUserId") Integer receiveUserId) {
        return pccChatInfoService.count(receiveUserId);
    }

    @ApiOperation("指定发送人未查看消息总数")
    @GetMapping("/count/send/user")
    public Integer countSendUserChatInfo(@RequestParam("sendUserId") Integer sendUserId
            , @RequestParam("receiveUserId") Integer receiveUserId) {
        return pccChatInfoService.countSendUserChatInfo(sendUserId, receiveUserId);
    }

    @ApiOperation("接收消息")
    @GetMapping("/receive")
    public void receiveChatInfo(@RequestParam("sendUserId") Integer sendUserId
            , @RequestParam("receiveUserId") Integer receiveUserId) {
        pccChatInfoService.receiveChatInfo(sendUserId, receiveUserId);
    }
}
