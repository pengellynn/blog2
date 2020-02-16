package com.ripon.adminserver.service.impl;

import com.ripon.adminserver.dao.TagMapper;
import com.ripon.adminserver.model.Tag;
import com.ripon.adminserver.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Override
    public List<Tag> getAllTag() {
        return tagMapper.selectByExample(null);
    }
}
