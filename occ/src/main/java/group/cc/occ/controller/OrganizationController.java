package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultCode;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Organization;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.OrganizationService;
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
 * @date 2019/03/01
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/organization")
public class OrganizationController {
    @Resource
    private OrganizationService organizationService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 Organization")
    @PostMapping("/add")
    public Result add(@RequestBody Organization organization) {
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        organizationService.addOrg(organization, login);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 Organization")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        if(this.organizationService.hasChildOrg(id))
            return ResultGenerator.genFailResult("该机构存在子机构，清先删除子机构！");
        organizationService.deleteOrg(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 Organization")
    @PutMapping("/update")
    public Result update(@RequestBody Organization organization) {
        organizationService.updateOrg(organization);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 Organization 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Organization organization = organizationService.findById(id);
        return ResultGenerator.genSuccessResult(organization);
    }

    @ApiOperation("分页查询 Organization 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Organization> list = organizationService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("获取所有organization")
    @GetMapping("/getAllOrganization")
    public Result getAllOrganization() {
        List<Organization> list = organizationService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("获取所有organization")
    @GetMapping("/getAllOrgByThisOrLow")
    public Result getAllOrgByThisOrLow() {
        LoginUserDto loginUserDto = RedisUtil.getLoginInfo(redisTemplate, request);
        List<Organization> list = organizationService.listByKey("NAME","%%", loginUserDto);
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("分页查询 User 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<Organization> list = null;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        if(login == null)
            return new Result().setCode(ResultCode.UNAUTHORIZED).setMessage("用户未登录！");

        if("".equals(key))
            list = organizationService.listByKey("NAME","%%", login);
        else
            list = organizationService.listByKey(key, value, login);
        PageInfo<Organization> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 Organization 列表")
    @GetMapping("/getAllLoginUserOrg")
    public Result getAllLoginUserOrg() {
        LoginUserDto loginUserDto = RedisUtil.getLoginInfo(redisTemplate, request);
        List<Organization> list = organizationService.getAllLoginUserOrg(loginUserDto);
        return ResultGenerator.genSuccessResult(list);
    }
}
