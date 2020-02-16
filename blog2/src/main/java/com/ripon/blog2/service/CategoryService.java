package com.ripon.blog2.service;

import com.ripon.blog2.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategory();
    Category findCategoryByUrl(String url);
}
