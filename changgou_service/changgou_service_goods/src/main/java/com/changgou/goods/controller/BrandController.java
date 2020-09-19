package com.changgou.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.impl.BrandServiceImpl;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController  {
    @Autowired
    private BrandServiceImpl brandService;

    @GetMapping
    public Result findAll(){
        List<Brand> all = brandService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", all);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Integer id){
        Brand byId = brandService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", byId);

    }

    @PostMapping
    @Transactional //开启事务（执行错误后会进行事务回滚）
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping("/{id}")
    @Transactional //开启事务（执行错误后会进行事务回滚）
    public Result update(@RequestBody Brand brand,@PathVariable("id") Integer id){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true,StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    @Transactional //开启事务（执行错误后会进行事务回滚）
    public Result delete(@PathVariable("id") Integer id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK, "删除成功");
    }

    @GetMapping("/search")
    public Result findList(@RequestParam Map searchMap){
        //这个map的查询条件是要写在url地址栏内，不是用json数据传输的
        List list = brandService.findList(searchMap);
        return new Result(true, StatusCode.OK, "查找成功", list);
    }


    @GetMapping("/search/{page}/{size}")
    public Result findPage(@PathVariable("page")int page, @PathVariable("size")int size){
        int a =1 / 0;
        Page<Brand> pageList = brandService.findPage(page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(true,StatusCode.OK, "分页查询成功", pageResult);
    }


    @GetMapping("/searchPage/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap,@PathVariable("page")int page, @PathVariable("size")int size){
        PageResult list = brandService.findList(searchMap, page, size);
        return new Result(true, StatusCode.OK, "按条件进行分页查询成功", list);
    }



}
