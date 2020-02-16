package com.ripon.adminserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;
import java.util.List;
@JsonIgnoreProperties(value = {"handler"})
public class ArticleDetail {
    private Integer id;

    private String title;

    private String author;

    private Date date;

    private String summary;

    private String content;

    private Integer view;

    private Integer love;

    private Integer categoryId;

    private Integer subCategoryId;

    private List<String> tagNames;

    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ArticleDetail() {
    }

    public ArticleDetail(Integer id, String title, String author, Date date, String summary, String content, Integer view, Integer love, Integer categoryId, Integer subCategoryId, List<String> tagNames, String icon) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.summary = summary;
        this.content = content;
        this.view = view;
        this.love = love;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.tagNames = tagNames;
        this.icon = icon;
    }
}
