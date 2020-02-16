package com.ripon.blog2.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private String url;
    @OneToMany(mappedBy = "category")
    private List<Article> articleList;
    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategoryList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Category() {
    }

    public Category(String name, String url, List<Article> articleList, List<SubCategory> subCategoryList) {
        this.name = name;
        this.url = url;
        this.articleList = articleList;
        this.subCategoryList = subCategoryList;
    }
}
