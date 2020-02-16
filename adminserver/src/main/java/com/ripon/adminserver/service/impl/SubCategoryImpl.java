package com.ripon.adminserver.service.impl;

import com.ripon.adminserver.dao.SubCategoryMapper;
import com.ripon.adminserver.model.SubCategory;
import com.ripon.adminserver.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubCategoryImpl implements SubCategoryService {
    @Autowired
    SubCategoryMapper subCategoryMapper;
    @Override
    public List<SubCategory> getAllSubCategory() {
        return subCategoryMapper.selectByExample(null);
    }
}
