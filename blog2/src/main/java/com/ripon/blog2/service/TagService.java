package com.ripon.blog2.service;

import com.ripon.blog2.domain.Tag;

import java.util.List;

public interface TagService {
    public List<Tag> findAllTag();
    public List<Tag> findTagByCategory(Integer categoryId);
    public Tag findTagById(Integer id);
}
