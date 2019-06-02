package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.NoticeList;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.NoticeListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import group.cc.occ.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author wangyuming
* @date 2019/05/29
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/noticeList")
public class NoticeListController {
    @Resource
    private NoticeListService noticeListService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 NoticeList")
    @PostMapping("/add")
    public Result add(@RequestBody NoticeList noticeList) {
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        noticeListService.saveNoticeList(noticeList, login);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 NoticeList")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        noticeListService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 NoticeList")
    @PutMapping("/update")
    public Result update(@RequestBody NoticeList noticeList) {
        noticeListService.update(noticeList);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 NoticeList 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        NoticeList noticeList = noticeListService.findById(id);
        return ResultGenerator.genSuccessResult(noticeList);
    }

    @ApiOperation("分页查询 NoticeList 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<NoticeList> list = noticeListService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 NoticeList 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<NoticeList> list = null;
        if("".equals(key))
            list = noticeListService.findAll();
        else
            list = noticeListService.listByKey(key, value);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 NoticeList")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<NoticeList> noticeLists) {
        noticeListService.deleteBatch(noticeLists);
        return ResultGenerator.genSuccessResult();
    }
}
