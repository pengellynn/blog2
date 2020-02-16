package com.ripon.blog2.service;

import com.ripon.blog2.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Set;

public interface ArticleService {
    public Page<Article> findAllArticle(Integer page, Integer size, Sort sort);
    public Page<Article> findArticleByCategory(Integer categoryId,Integer page, Integer size, Sort sort);
    public Page<Article> findArticleBySubCategory(Integer subCategoryId,Integer page, Integer size, Sort sort);
    public Article findArticleById(Integer id);
    public Page<Article> findArticleByKeyword(String keyword,Integer page, Integer size, Sort sort);
    public Long findTotalCount();
    public Long findTotalCount(Integer categoryId);
    public Long findTotalCountBySubCategory(Integer subCategoryId);
    public Long findTotalCountByKeyWord(String keyWord);
    public Set<Article> findArticleByTag(Integer id);
}
