package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LabelController
 *
 * @author waver
 * @date 2019.5.27 10:17
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 获取所有标签
     *
     * @return Result
     */
    @GetMapping()
    public Result findAll() {
        List<Label> labelList = labelService.findAll();
        return Result.ok("查询成功", labelList);
    }

    /**
     * 根据Id查询label
     *
     * @param id 标签ID
     * @return Result
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Label label = labelService.findById(id);
        return Result.ok("查询成功", label);
    }

    @PostMapping()
    public Result add(@RequestBody Label label) {
        boolean add = labelService.add(label);
        if (add) {
            return Result.ok("添加成功");
        }
        return Result.build("添加标签失败!");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        boolean update = labelService.update(label);
        if (update) {
            return Result.ok("更新成功");
        }
        return Result.build("更新失败");
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> labels = labelService.findSearch(label);
        return Result.ok("查询成功", labels);
    }

    /**
     * 标签分页
     *
     * @param label 标签
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> pageData = labelService.pageQuery(label, page, size);
        return Result.ok("查询成功", PageResult.getInstance(pageData.getTotalElements(), pageData.getContent()));
    }
}
