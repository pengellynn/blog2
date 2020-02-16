package com.ripon.adminserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ripon.adminserver.dto.ArticleDetail;
import com.ripon.adminserver.dto.ArticleGeneral;
import com.ripon.adminserver.dto.Response;
import com.ripon.adminserver.service.ArticleService;
import com.ripon.adminserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;

    @GetMapping("/article/{id}")
    public ArticleDetail getArticle(@PathVariable("id") Integer id) {
        return articleService.getArticle(id);
    }

    @PutMapping("/article/{id}")
    public Response updateArticle(@RequestBody ArticleDetail articleDetail) {
        if (articleService.updateArticle(articleDetail) == 1) {
            return Response.success();
        }
        return Response.fail("更新文章失败");
    }

    @PostMapping("/article/")
    public Response addArticle(@RequestBody ArticleDetail articleDetail) {
        if (articleService.addArticle(articleDetail) == 1) {
            return Response.success();
        }
        return Response.fail("添加文章失败");
    }

    @DeleteMapping("/article/{id}")
    public Response deleteArticle(@PathVariable("id") Integer id) {
        if (articleService.deleteArticle(id) == 1) {
            return Response.success();
        }
        return Response.fail("删除文章失败");

    }

    @GetMapping("/article/list")
    public Map<String, Object> getArticles(@RequestParam("currentPage") Integer currentPage,
                                           @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<ArticleGeneral> list = articleService.getAllArticleGeneral();
        PageInfo<ArticleGeneral> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @DeleteMapping("/articles")
    public Response batchDeleteArticle(List<Integer> ids) {
        if (articleService.batchDeleteArticle(ids) == ids.size()) {
            return Response.success();
        }
        return Response.fail("批量删除文章失败");

    }
}
