package group.cc.bms.controller;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import group.cc.bms.model.Org;
import group.cc.bms.service.OrgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by yuanli on 2018/10/29.
*/
@RestController
@RequestMapping("/org")
public class OrgController {
    @Resource
    private OrgService orgService;

    @PostMapping("/add")
    public Result add(Org org) {
        orgService.save(org);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        orgService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Org org) {
        orgService.update(org);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Org org = orgService.findById(id);
        return ResultGenerator.genSuccessResult(org);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Org> list = orgService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
