package com.ripon.adminserver.service;

import com.ripon.adminserver.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    int addCategory(Category category);
    int deleteCategory(Integer id);
    int updateCategory(Category category);
    int batchDeleteCategory(List<Integer> list);
}
