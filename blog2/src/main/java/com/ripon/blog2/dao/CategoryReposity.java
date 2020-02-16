package com.ripon.blog2.dao;

import com.ripon.blog2.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryReposity extends JpaRepository<Category,Integer> {
    @Query("select distinct c from Category c left join fetch c.subCategoryList order by c.id")
    List<Category> findAllCategory();
    Category findByUrl(String url);
}
