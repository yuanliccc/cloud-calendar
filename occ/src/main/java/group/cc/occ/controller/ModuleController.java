package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Module;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import group.cc.occ.util.CusAccessObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wangyuming
 * @date 2019/03/01
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/module")
public class ModuleController {
    @Resource
    private ModuleService moduleService;

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 Module")
    @PostMapping
    public Result add(@RequestBody Module module) {
        moduleService.save(module);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 Module")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        moduleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 Module")
    @PutMapping
    public Result update(@RequestBody Module module) {
        moduleService.update(module);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 Module 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Module module = moduleService.findById(id);
        return ResultGenerator.genSuccessResult(module);
    }

    @ApiOperation("分页查询 Module 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Module> list = moduleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("获取登录用户的模块")
    @GetMapping("/getLoginModule")
    public Result getLoginModule(){
        LoginUserDto login = (LoginUserDto)redisTemplate.opsForValue().get("userInfo" + CusAccessObjectUtil.getIpAddress(request));
        //需要重新判断该用户是否拥有此角色（未完成）
        List list = moduleService.getModules(login.getRole().getId());
        return ResultGenerator.genSuccessResult(list);
    }
}
