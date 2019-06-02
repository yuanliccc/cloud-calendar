package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.OrgInvite;
import group.cc.occ.model.Organization;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.OrgInviteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import group.cc.occ.service.OrganizationService;
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
* @date 2019/05/30
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/orgInvite")
public class OrgInviteController {
    @Resource
    private OrgInviteService orgInviteService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 OrgInvite")
    @PostMapping("/add")
    public Result add(@RequestBody OrgInvite orgInvite) {
        try {
            orgInviteService.addInvite(orgInvite);
        } catch (Exception e) {
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 OrgInvite")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        orgInviteService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 OrgInvite")
    @PutMapping("/update")
    public Result update(@RequestBody OrgInvite orgInvite) {
        orgInviteService.update(orgInvite);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 OrgInvite 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        OrgInvite orgInvite = orgInviteService.findById(id);
        return ResultGenerator.genSuccessResult(orgInvite);
    }

    @ApiOperation("分页查询 OrgInvite 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<OrgInvite> list = orgInviteService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 OrgInvite 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<OrgInvite> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        if("".equals(key))
            list = orgInviteService.listByKey("STATE", "", login.getUser().getId());
        else
            list = orgInviteService.listByKey(key, value, login.getUser().getId());
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 OrgInvite")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<OrgInvite> orgInvites) {
        orgInviteService.deleteBatch(orgInvites);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("用户审批邀请")
    @GetMapping("/approveInvite")
    public Result approveInvite(Integer inviteId, String state) {
        OrgInvite orgInvite = this.orgInviteService.findById(inviteId);
        orgInvite.setState(state);

        orgInviteService.approveInvite(orgInvite);
        return ResultGenerator.genSuccessResult();
    }
}
