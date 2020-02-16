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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/search")
    public String Search(HttpServletRequest request, @RequestParam(name = "keyboard", required = false) String keyword, Model model) {
        if (keyword != null) {
            request.getSession().setAttribute("keyword", keyword);
        }
        List<Category> categoryList = categoryService.findAllCategory();
        Long totalCount=articleService.findTotalCountByKeyWord(keyword);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("totalCount",totalCount);
        return "search";
    }

    @GetMapping("/search/articleList")
    public String findResultByPage(HttpServletRequest request,
                                   @RequestParam(name = "page", required = false) Integer page,
                                   @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                   Model model) {
//        if (page == null) {
//            page = 0;
//        }
        String keyword = (String) request.getSession().getAttribute("keyword");
        Page<Article> articleList = articleService.findArticleByKeyword(keyword, page, pageSize, new Sort(Sort.Direction.DESC, "date"));
        model.addAttribute("newArticles", articleList);
        return "index::articleList";
    }
}

