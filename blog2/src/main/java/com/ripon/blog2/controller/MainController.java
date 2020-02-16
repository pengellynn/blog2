package com.ripon.blog2.controller;

import com.ripon.blog2.domain.Article;
import com.ripon.blog2.domain.Tag;
import com.ripon.blog2.service.ArticleService;
import com.ripon.blog2.service.CategoryService;
import com.ripon.blog2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;
    @Autowired
    TagService tagService;
    @GetMapping(value ={"/","/index"})
    public String indexInit(Model model){
        final Integer PAGE=0;
        final Integer PAGE_SIZE=5;
        List categoryList=categoryService.findAllCategory();
        Page<Article> recommendedArticles=articleService.findAllArticle(PAGE,PAGE_SIZE,new Sort(Sort.Direction.DESC,"like"));
        Page<Article> hottestArticles=articleService.findAllArticle(PAGE,PAGE_SIZE,new Sort(Sort.Direction.DESC,"view"));
        List<Tag> tagList=tagService.findAllTag();
        Long totalCount = articleService.findTotalCount();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("recommendedArticles",recommendedArticles);
        model.addAttribute("hottestArticles",hottestArticles);
        model.addAttribute("tagList",tagList);
        model.addAttribute("totalCount", totalCount);
        return "index";
    }
    @GetMapping("/articles")
    public String findArticleByPage(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize, Model model){
        Page<Article> newArticles=articleService.findAllArticle(page,pageSize,new Sort(Sort.Direction.DESC,"date"));
        model.addAttribute("newArticles",newArticles);
        return "index::articleList";
    }
    @GetMapping("/tag/{id}")
    public String findArticleByTag(@PathVariable("id") Integer id, Model model){
        Set<Article> newArticles=articleService.findArticleByTag(id);
        model.addAttribute("newArticles",newArticles);
        return "index::articleList";
    }
}
