package com.ripon.adminserver.service.impl;

import com.ripon.adminserver.dao.CategoryMapper;
import com.ripon.adminserver.model.Category;
import com.ripon.adminserver.model.CategoryExample;
import com.ripon.adminserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.selectByExample(null);
    }

    @Override
    public int updateCategory(Category category) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(category.getId());
        return categoryMapper.updateByExample(category, example);
        //相当于update category set username = #{username}, url=#{url} where id= #{id}
    }

    @Override
    public int addCategory(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int deleteCategory(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int batchDeleteCategory(List<Integer> list) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(list);
        return categoryMapper.deleteByExample(example);
    }
}
