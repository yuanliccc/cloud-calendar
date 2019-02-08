package group.cc.occ.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.Organization;
import group.cc.occ.service.OrganizationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangyuming
 * @date 2019/01/02
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Resource
    private OrganizationService organizationService;

    @ApiOperation(value="添加 Organization")
    @PostMapping
    public Result add(@RequestBody Organization organization) {
        organizationService.save(organization);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 Organization")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        organizationService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 Organization")
    @PutMapping
    public Result update(@RequestBody Organization organization) {
        organizationService.update(organization);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 Organization 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Organization organization = organizationService.findById(id);
        return ResultGenerator.genSuccessResult(organization);
    }

    @ApiOperation(value="分页查询 Organization 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Organization> list = organizationService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
