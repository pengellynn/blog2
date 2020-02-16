package com.ripon.blog2.service.impl;

import com.ripon.blog2.dao.SubCategoryReposity;
import com.ripon.blog2.domain.SubCategory;
import com.ripon.blog2.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    SubCategoryReposity subCategoryReposity;
    @Override
    public SubCategory findSubCategoryByUrl(String url) {
        return subCategoryReposity.findByUrl(url);
    }
}
