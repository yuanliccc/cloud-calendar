package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Notice;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.NoticeService;
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
* @date 2019/03/17
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 Notice")
    @PostMapping("/add")
    public Result add(@RequestBody Notice notice) {
        LoginUserDto loginUserDto = RedisUtil.getLoginInfo(redisTemplate, request);
        noticeService.addNotice(notice, loginUserDto);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 Notice")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        noticeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 Notice")
    @PutMapping("/update")
    public Result update(@RequestBody Notice notice) {
        noticeService.update(notice);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 Notice 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Notice notice = noticeService.findById(id);
        return ResultGenerator.genSuccessResult(notice);
    }

    @ApiOperation("分页查询 Notice 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Notice> list = noticeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 Notice 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<Notice> list = null;
        LoginUserDto loginUserDto = RedisUtil.getLoginInfo(redisTemplate, request);
        if("".equals(key))
            list = noticeService.findAll();
        else
            list = noticeService.listByKey(key, value, loginUserDto);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 Notice")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Notice> notices) {
        noticeService.deleteBatch(notices);
        return ResultGenerator.genSuccessResult();
    }
}
