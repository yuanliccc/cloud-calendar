package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.OrgApply;
import group.cc.occ.model.Organization;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.OrgApplyService;
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
import java.util.Date;
import java.util.List;

/**
* @author wangyuming
* @date 2019/05/30
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/orgApply")
public class OrgApplyController {
    @Resource
    private OrgApplyService orgApplyService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private OrganizationService organizationService;


    @ApiOperation("添加 OrgApply")
    @PostMapping("/add")
    public Result add(@RequestBody OrgApply orgApply) {
        orgApplyService.save(orgApply);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 OrgApply")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        orgApplyService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 OrgApply")
    @PutMapping("/update")
    public Result update(@RequestBody OrgApply orgApply) {
        orgApplyService.update(orgApply);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 OrgApply 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        OrgApply orgApply = orgApplyService.findById(id);
        return ResultGenerator.genSuccessResult(orgApply);
    }

    @ApiOperation("分页查询 OrgApply 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<OrgApply> list = orgApplyService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 OrgApply 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<OrgApply> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        if("".equals(key))
            list = orgApplyService.listByKey("NAME", "", login.getOrganization().getId());
        else
            list = orgApplyService.listByKey(key, value, login.getOrganization().getId());
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 OrgApply")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<OrgApply> orgApplys) {
        orgApplyService.deleteBatch(orgApplys);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("审批入驻申请")
    @GetMapping("/approveApply")
    public Result approveApply(@RequestParam()Integer applyId, @RequestParam()String state) {
        OrgApply apply = orgApplyService.findById(applyId);
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);

        apply.setState(state);
        apply.setApprovaltime(new Date());
        apply.setApprovaluserid(login.getUser().getId());

        this.orgApplyService.update(apply);
        this.organizationService.orgApply(applyId, apply.getSubmituserid());
        return ResultGenerator.genSuccessResult();
    }
}
