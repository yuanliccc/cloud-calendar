package group.cc.pcc.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.pcc.model.PccRemindService;
import group.cc.pcc.service.PccRemindServiceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanli
 * @date 2018/12/23
 */
@RestController
@RequestMapping("/pcc/remind/service")
public class PccRemindServiceController {
    @Resource
    private PccRemindServiceService pccRemindServiceService;

    @ApiOperation("添加 PccRemindService")
    @PostMapping
    public Result add(@RequestBody PccRemindService pccRemindService) {
        pccRemindServiceService.save(pccRemindService);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="删除 PccRemindService")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        pccRemindServiceService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="更新 PccRemindService")
    @PutMapping
    public Result update(@RequestBody PccRemindService pccRemindService) {
        pccRemindServiceService.update(pccRemindService);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="通过 Id 查询 PccRemindService 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PccRemindService pccRemindService = pccRemindServiceService.findById(id);
        return ResultGenerator.genSuccessResult(pccRemindService);
    }

    @ApiOperation(value="分页查询 PccRemindService 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PccRemindService> list = pccRemindServiceService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
