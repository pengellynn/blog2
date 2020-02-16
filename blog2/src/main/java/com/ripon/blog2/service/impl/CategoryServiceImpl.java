package com.ripon.blog2.service.impl;

import com.ripon.blog2.dao.CategoryReposity;
import com.ripon.blog2.domain.Category;
import com.ripon.blog2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryReposity categoryReposity;
    @Override
    public List<Category> findAllCategory() {
        return categoryReposity.findAllCategory();
    }
    @Override
    public Category findCategoryByUrl(String url) {
        return categoryReposity.findByUrl(url);
    }
}
