package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Orgcalender;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.OrgcalenderService;
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
* @date 2019/03/28
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/orgCalender")
public class OrgcalenderController {
    @Resource
    private OrgcalenderService orgcalenderService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 Orgcalender")
    @PostMapping("/add")
    public Result add(@RequestBody Orgcalender orgcalender) {
        orgcalenderService.save(orgcalender);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 Orgcalender")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        orgcalenderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 Orgcalender")
    @PutMapping("/update")
    public Result update(@RequestBody Orgcalender orgcalender) {
        orgcalenderService.update(orgcalender);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 Orgcalender 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Orgcalender orgcalender = orgcalenderService.findById(id);
        return ResultGenerator.genSuccessResult(orgcalender);
    }

    @ApiOperation("分页查询 Orgcalender 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Orgcalender> list = orgcalenderService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 Orgcalender 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<Orgcalender> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        if("".equals(key))
            list = orgcalenderService.findAllByLoginOrgId(login);
        else
            list = orgcalenderService.listByKey(key, value, login);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 Orgcalender")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Orgcalender> orgcalenders) {
        orgcalenderService.deleteBatch(orgcalenders);
        return ResultGenerator.genSuccessResult();
    }
}
