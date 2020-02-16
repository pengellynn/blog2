package com.ripon.adminserver.service;

import com.ripon.adminserver.dto.ArticleDetail;
import com.ripon.adminserver.dto.ArticleGeneral;

import java.util.List;

public interface ArticleService {
    ArticleDetail getArticle(Integer id);
    int deleteArticle(Integer id);
    int updateArticle(ArticleDetail articleDetail);
    int addArticle(ArticleDetail articleDetail);
    List<ArticleGeneral> getAllArticleGeneral();
    int batchDeleteArticle(List<Integer> ids);
}

