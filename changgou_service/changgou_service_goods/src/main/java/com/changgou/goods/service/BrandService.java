package com.changgou.goods.service;

import com.changgou.entity.PageResult;
import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;
import java.util.Map;

public interface BrandService {

    /*查询所有*/
    public List<Brand> findAll();

    /**
     * 根据id查询品牌
     * @param id 传入的brandid
     * @return 从数据库查询的brand
     */
    public Brand findById(Integer id);

    /**
     * 新增
     * @param brand 传入的品牌对象
     */
    public void add(Brand brand);

    /**
     * 修改
     * @param brand
     */
    public void update(Brand brand);

    /**
     * 通过id进行删除
     * @param id
     */
    public void delete(Integer id);

    /**
     * 多条件查询，
     * @param seachMap
     * @return
     */
    public List<Brand> findList(Map<String, Object> seachMap);

    /**
     * 分页查询
     * @param page 页数
     * @param size 每页显示的数目
     * @return
     */
    public Page<Brand> findPage(int page, int size);

    /**
     * 品牌列表+分页查询
     */

    public PageResult findList(Map<String, Object> seachMap, int page, int size);
}
