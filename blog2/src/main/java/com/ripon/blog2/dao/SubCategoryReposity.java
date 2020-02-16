package com.ripon.blog2.dao;

import com.ripon.blog2.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryReposity extends JpaRepository<SubCategory, Integer> {
    SubCategory findByUrl(String url);
}
