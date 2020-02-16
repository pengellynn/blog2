package com.ripon.blog2.controller;

import com.ripon.blog2.domain.Article;
import com.ripon.blog2.domain.Category;
import com.ripon.blog2.service.ArticleService;
import com.ripon.blog2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class TimeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;
    @GetMapping("/time")
    public String timeInit(Model model){
        String url="/time";
        Category category=categoryService.findCategoryByUrl(url);
        List<Category> categoryList=categoryService.findAllCategory();
        Long totalCount = articleService.findTotalCount();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("category",category);
        model.addAttribute("totalCount", totalCount);
        return "time";
    }
    @GetMapping("/time/articleList")
    public String findArticleByPage(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize, Model model){
        Page<Article> articles=articleService.findAllArticle(page,pageSize,new Sort(Sort.Direction.DESC,"date"));
        model.addAttribute("articles",articles);
        return "time::articleList";
    }
}
