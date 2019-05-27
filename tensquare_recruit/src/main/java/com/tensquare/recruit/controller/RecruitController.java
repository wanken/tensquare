package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping()
    public Result findAll() {
        return Result.ok("查询成功", recruitService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return Result.ok("查询成功", recruitService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Recruit> pageList = recruitService.findSearch(searchMap, page, size);
        return Result.ok("查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap 查询条件封装
     * @return Result
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap) {
        return Result.ok("查询成功", recruitService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param recruit 招聘信息
     */
    @PostMapping()
    public Result add(@RequestBody Recruit recruit) {
        recruitService.add(recruit);
        return Result.ok("增加成功");
    }

    /**
     * 修改
     *
     * @param recruit 招聘信息
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Recruit recruit, @PathVariable String id) {
        recruit.setId(id);
        recruitService.update(recruit);
        return Result.ok("修改成功");
    }

    /**
     * 删除
     *
     * @param id 招聘信息ID
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        recruitService.deleteById(id);
        return Result.ok("删除成功");
    }

    @GetMapping("/search/recommend")
    public Result recommend() {
        return Result.ok("查询成功", recruitService.recommend());
    }

    @GetMapping("/search/newList")
    public Result newList() {
        return Result.ok("查询成功", recruitService.newList());
    }
}
