package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccChat;
import group.cc.pcc.service.PccChatService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanli
 * @date 2019/04/08
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/chat")
public class PccChatController {
    @Resource
    private PccChatService pccChatService;

    @ApiOperation("添加 PccChat")
    @PostMapping("/add")
    public Result add(PccChat pccChat) {
        pccChatService.save(pccChat);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccChat")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        pccChatService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccChat")
    @PostMapping("/update")
    public Result update(PccChat pccChat) {
        pccChatService.update(pccChat);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccChat 详情")
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        PccChat pccChat = pccChatService.findById(id);
        return ResultGenerator.genSuccessResult(pccChat);
    }

    @ApiOperation("分页查询 PccChat 列表")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccChat> list = pccChatService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
