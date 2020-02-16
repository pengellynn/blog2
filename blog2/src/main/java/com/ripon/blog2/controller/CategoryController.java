package com.ripon.blog2.controller;

import com.ripon.blog2.domain.Article;
import com.ripon.blog2.domain.Category;
import com.ripon.blog2.domain.SubCategory;
import com.ripon.blog2.domain.Tag;
import com.ripon.blog2.service.ArticleService;
import com.ripon.blog2.service.CategoryService;
import com.ripon.blog2.service.SubCategoryService;
import com.ripon.blog2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;
    @Autowired
    TagService tagService;
    @Autowired
    SubCategoryService subCategoryService;

    @GetMapping("/category/{categoryName}")
    public String categoryInit(@PathVariable("categoryName") String categoryName, Model model, HttpServletRequest httpRequest){
        String url = httpRequest.getRequestURI();
        final Integer PAGE=0;
        final Integer PAGE_SIZE=5;
        Category category = getCategory(url);
        Integer categoryId = category.getId();
        List categoryList=categoryService.findAllCategory();
        Page<Article> recommendedArticles=articleService.findArticleByCategory(categoryId,PAGE,PAGE_SIZE,new Sort(Sort.Direction.DESC,"like"));
        Page<Article> hottestArticles=articleService.findArticleByCategory(categoryId,PAGE,PAGE_SIZE,new Sort(Sort.Direction.DESC,"view"));
        List<Tag> tagList=tagService.findTagByCategory(categoryId);
        Long totalCount=articleService.findTotalCount(categoryId);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("recommendedArticles",recommendedArticles);
        model.addAttribute("hottestArticles",hottestArticles);
        model.addAttribute("tagList",tagList);
        model.addAttribute("category",category);
        model.addAttribute("totalCount",totalCount);
        return categoryName;
    }
    @GetMapping("/category/{category}/articles")
    public String findArticleByPage(@PathVariable("category") String category,
                                    @RequestParam("page") Integer page,
                                    @RequestParam("pageSize") Integer pageSize,
                                    Model model){
        String url ="/category/"+category;
        Integer categoryId=getCategory(url).getId();
        Page<Article> newArticles=articleService.findArticleByCategory(categoryId,page,pageSize,new Sort(Sort.Direction.DESC,"date"));
        model.addAttribute("newArticles",newArticles);
        return "index::articleList";
    }

    @GetMapping("/technology/{subCategory}")
    public String technologySubCategoryInit(Model model, HttpServletRequest httpRequest){
        String url= httpRequest.getRequestURI();
        setSubCategoryModel(url, model);
        return "technology";
    }

    @GetMapping("life/{subCategory}")
    public String lifeSubCategoryInit(Model model, HttpServletRequest httpRequest){
        String url= httpRequest.getRequestURI();
        setSubCategoryModel(url, model);
        return "life";
    }

    @GetMapping("/{category}/{subCategory}/articles")
    public String getArticleByPage( @PathVariable("category") String category,
                                    @PathVariable("subCategory") String subCategory,
                                    @RequestParam("page") Integer page,
                                    @RequestParam("pageSize") Integer pageSize,
                                    Model model){
        String url= "/"+category+"/"+subCategory;
        Integer subCategoryId=subCategoryService.findSubCategoryByUrl(url).getId();
        Page<Article> newArticles=articleService.findArticleBySubCategory(subCategoryId,page,pageSize,new Sort(Sort.Direction.DESC,"date"));
        model.addAttribute("newArticles",newArticles);
        return "index::articleList";
    }
    private Category getCategory (String url) {
        return categoryService.findCategoryByUrl(url);
    }

    private void setSubCategoryModel(String url, Model model) {
        final Integer PAGE=0;
        final Integer PAGE_SIZE=5;
        SubCategory subCategory=subCategoryService.findSubCategoryByUrl(url);
        Category category=subCategory.getCategory();
        Integer categoryId=category.getId();
        Integer subCategoryId = subCategory.getId();
        List categoryList=categoryService.findAllCategory();
        Page<Article> recommendedArticles=articleService.findArticleBySubCategory(subCategoryId,PAGE,PAGE_SIZE,new Sort(Sort.Direction.DESC,"like"));
        Page<Article> hottestArticles=articleService.findArticleBySubCategory(subCategoryId,PAGE,PAGE_SIZE,new Sort(Sort.Direction.DESC,"view"));
        List<Tag> tagList=tagService.findTagByCategory(categoryId);
        Long totalCount=articleService.findTotalCountBySubCategory(subCategoryId);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("recommendedArticles",recommendedArticles);
        model.addAttribute("hottestArticles",hottestArticles);
        model.addAttribute("tagList",tagList);
        model.addAttribute("category",category);
        model.addAttribute("totalCount",totalCount);
    }
}

