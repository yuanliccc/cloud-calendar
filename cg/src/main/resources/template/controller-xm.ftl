package ${controllerPackage};

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ${author}
* @date ${date}
*/
@CrossOrigin("*")
@RestController
@RequestMapping("/occ/${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @ApiOperation("添加 ${modelNameUpperCamel}")
    @PostMapping("/add")
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 ${modelNameUpperCamel}")
    @DeleteMapping("/delete")
    public Result delete(@PathVariable Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 ${modelNameUpperCamel}")
    @PutMapping("/update")
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 ${modelNameUpperCamel} 详情")
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @ApiOperation("分页查询 ${modelNameUpperCamel} 列表")
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 ${modelNameUpperCamel} 列表带模糊查询")
    @GetMapping("/listByKey")
    public Result listByKey(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String value) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = null;
        if("".equals(key))
            list = ${modelNameLowerCamel}Service.findAll();
        else
            list = ${modelNameLowerCamel}Service.listByKey(key, value);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("批量删除 ${modelNameUpperCamel}")
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<${modelNameUpperCamel}> ${modelNameLowerCamel}s) {
        ${modelNameLowerCamel}Service.deleteBatch(${modelNameLowerCamel}s);
        return ResultGenerator.genSuccessResult();
    }
}
