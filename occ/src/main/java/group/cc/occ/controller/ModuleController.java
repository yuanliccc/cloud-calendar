package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultCode;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Module;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.service.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import group.cc.occ.util.RedisUtil;
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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("添加 Module")
    @PostMapping("/add")
    public Result add(@RequestBody Module module) {
        try {
            moduleService.saveModule(module);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }

        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 Module")
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        moduleService.deleteByModuleId(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("批量删除 Module")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Module> modules) {
       moduleService.deleteBatch(modules);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 Module")
    @PutMapping("/update")
    public Result update(@RequestBody Module module) {
        moduleService.update(module);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 Module 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Module module = moduleService.findById(id);
        return ResultGenerator.genSuccessResult(module);
    }

    @ApiOperation("分页查询 Module 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Module> list = moduleService.findAll();
        PageInfo<Module> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 Module 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<Module> list = null;
        if("".equals(key))
            list = moduleService.findAll();
        else
            list = moduleService.listByKey(key, value);
        PageInfo<Module> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("获取登录用户的模块")
    @GetMapping("/getLoginModule")
    public Result getLoginModule(){
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);
        //需要重新判断该用户是否拥有此角色（未完成）
        if(login == null)
            return new Result().setCode(ResultCode.UNAUTHORIZED).setMessage("用户未登录！");

        List list = moduleService.getModules(login.getRole().getId());
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("获取所有父模块")
    @GetMapping("/getAllParent")
    public Result getAllParent(){
        List<Module> list = this.moduleService.getAllParent();
        return ResultGenerator.genSuccessResult(list);
    }

    @ApiOperation("获取所有模块")
    @GetMapping("/getAllModule")
    public Result getAllModule() {
        List<Module> list = moduleService.findAllExceptSystemModule();
        return ResultGenerator.genSuccessResult(list);
    }
}
