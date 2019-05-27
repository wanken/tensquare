package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping()
    public Result findAll() {
        return Result.ok("查询成功", enterpriseService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return Result
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return Result.ok("查询成功", enterpriseService.findById(id));
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
        Page<Enterprise> pageList = enterpriseService.findSearch(searchMap, page, size);
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
        return Result.ok("查询成功", enterpriseService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param enterprise 企业对象
     */
    @PostMapping
    public Result add(@RequestBody Enterprise enterprise) {
        enterpriseService.add(enterprise);
        return Result.ok("增加成功");
    }

    /**
     * 修改
     *
     * @param enterprise 企业对象
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Enterprise enterprise, @PathVariable String id) {
        enterprise.setId(id);
        enterpriseService.update(enterprise);
        return Result.ok("修改成功");
    }

    /**
     * 删除
     *
     * @param id 企业ID
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        enterpriseService.deleteById(id);
        return Result.ok("删除成功");
    }


    @GetMapping("/search/hotList")
    public Result hostList(){
        List<Enterprise> enterprises = enterpriseService.hotList("1");
        return Result.ok("查询成功", enterprises);
    }

}
