package com.ripon.adminserver.controller;

import com.ripon.adminserver.model.SubCategory;
import com.ripon.adminserver.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubCategoryController {
    @Autowired
    SubCategoryService subCategoryService;

    @GetMapping("/subCategories")
    public List<SubCategory> getAllSubCategory() {
        return subCategoryService.getAllSubCategory();
    }

}
