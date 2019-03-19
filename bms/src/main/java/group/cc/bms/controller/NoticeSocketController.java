package group.cc.bms.controller;

import group.cc.bms.service.NoticeScoketService;
import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by wangyuming on 2019/3/19.
 */
@CrossOrigin("*")
@Controller
@RequestMapping("/notice")
public class NoticeSocketController {

    @Resource
    private NoticeScoketService noticeScoketService;

    @ApiOperation("通知")
    @GetMapping("/occ")
    public Result occ(@RequestParam() Integer userId, @RequestParam() String message) {
        noticeScoketService.OccNotice(userId, message);
        return ResultGenerator.genSuccessResult();
    }
}
