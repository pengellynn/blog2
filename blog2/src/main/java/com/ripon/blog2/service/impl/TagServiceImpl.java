package com.ripon.blog2.service.impl;

import com.ripon.blog2.dao.TagReposity;
import com.ripon.blog2.domain.Tag;
import com.ripon.blog2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagReposity tagReposity;
    @Override
    public List<Tag> findAllTag() {
        return tagReposity.findAll();
    }

    @Override
    public List<Tag> findTagByCategory(Integer categoryId) {
        return tagReposity.findByCategoryId(categoryId);
    }

    @Override
    public Tag findTagById(Integer id) {
        return tagReposity.findById(id).get();
    }
}
