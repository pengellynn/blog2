package com.ripon.blog2.controller;

import com.ripon.blog2.domain.Category;
import com.ripon.blog2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AboutController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/about")
    public String aboutInit(Model model){
        String url="/about";
        Category category=categoryService.findCategoryByUrl(url);
        List categoryList=categoryService.findAllCategory();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("category",category);
        return "about";
    }
}
