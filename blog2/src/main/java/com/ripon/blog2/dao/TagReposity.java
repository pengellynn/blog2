package com.ripon.blog2.dao;

import com.ripon.blog2.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagReposity extends JpaRepository<Tag,Integer> {
    List<Tag> findByCategoryId(Integer id);
}
