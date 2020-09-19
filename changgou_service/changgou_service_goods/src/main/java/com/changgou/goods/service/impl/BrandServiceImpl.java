package com.changgou.goods.service.impl;

import com.changgou.entity.PageResult;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        List<Brand> brands = brandMapper.selectAll();
        return brands;
    }

    @Override
    public Brand findById(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /*多条件查询，*/
    @Override
    public List<Brand> findList(Map<String, Object> seachMap) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (seachMap != null){
            if (seachMap.get("name") != null && !"".equals(seachMap.get("name"))){
                criteria.andLike("name", "%" + seachMap.get("name") + "%");
            }
            // 品牌的首字母
            if(seachMap.get("letter")!=null && !"".equals(seachMap.get("letter"))){
                criteria.andEqualTo("letter",seachMap.get("letter"));
            }
        }

        return brandMapper.selectByExample(example);
    }

    @Override
    public Page<Brand> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Brand> brandPage = (Page<Brand>) brandMapper.selectAll();
        return brandPage;
    }

    @Override
    public PageResult findList(Map<String, Object> seachMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (seachMap != null){
            if (seachMap.get("name") != null && !"".equals(seachMap.get("name"))){
                criteria.andLike("name", "%" + seachMap.get("name") + "%");
            }
            // 品牌的首字母
            if(seachMap.get("letter")!=null && !"".equals(seachMap.get("letter"))){
                criteria.andEqualTo("letter",seachMap.get("letter"));
            }
        }
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);
        PageResult pageResult = new PageResult(brands.getTotal(), brands.getResult());
        return pageResult;
    }


}
