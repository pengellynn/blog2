package com.ripon.blog2.service.impl;

import com.ripon.blog2.dao.ArticleReposity;
import com.ripon.blog2.domain.Article;
import com.ripon.blog2.domain.Tag;
import com.ripon.blog2.service.ArticleService;
import com.ripon.blog2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleReposity articleReposity;
    @Autowired
    TagService tagService;

    @Override
    public Set<Article> findArticleByTag(Integer id) {
        Tag tag = tagService.findTagById(id);
        return tag.getArticles();
    }

    @Override
    public Long findTotalCount(Integer categoryId) {
        return articleReposity.countByCategoryId(categoryId);
    }

    @Override
    public Long findTotalCountBySubCategory(Integer subCategoryId) {
        return articleReposity.countBySubCategoryId(subCategoryId);
    }

    @Override
    public Long findTotalCount() {
        return articleReposity.count();
    }

    @Override
    public Page<Article> findArticleBySubCategory(Integer subCategoryId, Integer page, Integer size, Sort sort) {
        PageRequest pageRequest=PageRequest.of(page,size,sort);
        return articleReposity.findBySubCategoryId(subCategoryId,pageRequest);
    }

    @Override
    public Page<Article> findArticleByCategory(Integer id,Integer page, Integer size, Sort sort) {
        PageRequest pageRequest=PageRequest.of(page,size,sort);
        return articleReposity.findByCategoryId(id,pageRequest);
    }

    @Override
    public Page<Article> findAllArticle(Integer page, Integer size, Sort sort) {
        PageRequest pageRequest=PageRequest.of(page,size,sort);
        return articleReposity.findAll(pageRequest);
    }

    @Override
    public Article findArticleById(Integer id) {
        return articleReposity.findById(id).get();
    }

    @Override
    public Long findTotalCountByKeyWord(String keyWord) {
        return articleReposity.countByTitleContaining(keyWord);
    }

    @Override
    public Page<Article> findArticleByKeyword(String keyword,Integer page, Integer size, Sort sort) {
        PageRequest pageRequest=PageRequest.of(page,size,sort);
        return articleReposity.findByTitleContaining(keyword,pageRequest);
    }
}
