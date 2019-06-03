package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccNotice;
import group.cc.pcc.service.PccNoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanli
 * @date 2019/06/01
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/notice")
public class PccNoticeController {
    @Resource
    private PccNoticeService pccNoticeService;

    @ApiOperation("添加 PccNotice")
    @PostMapping
    public Result add(@RequestBody PccNotice pccNotice) {
        pccNoticeService.save(pccNotice);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 PccNotice")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccNoticeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 PccNotice")
    @PutMapping
    public Result update(@RequestBody PccNotice pccNotice) {
        pccNoticeService.update(pccNotice);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 PccNotice 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccNotice pccNotice = pccNoticeService.findById(id);
        return ResultGenerator.genSuccessResult(pccNotice);
    }

    @ApiOperation("分页查询 PccNotice 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccNotice> list = pccNoticeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("列表")
    @GetMapping("/list/user")
    public Result listUser(@RequestParam("pccUserId") Integer pccUserId) {

        List<PccNotice> pccNotices = pccNoticeService.lisUser(pccUserId);

        return ResultGenerator.genSuccessResult(pccNotices);
    }

    @ApiOperation("列表")
    @GetMapping("/list/apply")
    public Result listApply(@RequestParam("pccUserId") Integer pccUserId) {
        List<PccNotice> pccNotices = pccNoticeService.listApply(pccUserId);
        return ResultGenerator.genSuccessResult(pccNotices);
    }
}
