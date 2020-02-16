package com.ripon.blog2.dao;

import com.ripon.blog2.domain.Article;
import com.ripon.blog2.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleReposity extends JpaRepository<Article,Integer> {
    Page<Article> findAll(Pageable pageable);
    Page<Article> findByCategoryId(Integer id,Pageable pageable);
    Page<Article> findBySubCategoryId(Integer id, Pageable pageable);
    Page<Article> findByTitleContaining(String keyword,Pageable pageable);
    Long countByCategoryId(Integer id);
    Long countBySubCategoryId(Integer id);
    Long countByTitleContaining(String keyword);
}
