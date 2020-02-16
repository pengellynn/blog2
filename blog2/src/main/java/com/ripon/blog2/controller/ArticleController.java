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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/article/{id}")
    public String infoInit(@PathVariable("id") Integer id, Model model){
        Article article=articleService.findArticleById(id);
        Category category =article.getCategory();
        Integer categoryId=category.getId();
        Article preArticle;
        Article nextArticle;
        if(id-1<=0) {
            preArticle=articleService.findArticleById(id+2);
        }else{
            preArticle=articleService.findArticleById(id-1);
        }
        if(id+1>=5) {
            nextArticle=articleService.findArticleById(id-2);
        }else{
            nextArticle=articleService.findArticleById(id+1);
        }
        Page<Article> relatedArticles=articleService.findArticleByCategory(categoryId,0,5,new Sort(Sort.Direction.DESC,"date"));
        Page<Article> recommendedArticles=articleService.findArticleByCategory(categoryId,0,5,new Sort(Sort.Direction.DESC,"like"));
        Page<Article> hottestArticles=articleService.findArticleByCategory(categoryId,0,5,new Sort(Sort.Direction.DESC,"view"));
        List categoryList=categoryService.findAllCategory();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("recommendedArticles",recommendedArticles);
        model.addAttribute("hottestArticles",hottestArticles);
        model.addAttribute("article",article);
        model.addAttribute("category",category);
        model.addAttribute("preArticle",preArticle);
        model.addAttribute("nextArticle",nextArticle);
        model.addAttribute("relatedArticles",relatedArticles);
        return "info";
    }
}
