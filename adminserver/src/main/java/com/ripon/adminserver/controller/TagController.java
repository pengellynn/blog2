package com.ripon.adminserver.controller;

import com.ripon.adminserver.model.Tag;
import com.ripon.adminserver.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    TagService tagService;
    @GetMapping("/tags")
    public List<Tag> getAllTag() {
        return tagService.getAllTag();
    }
}
