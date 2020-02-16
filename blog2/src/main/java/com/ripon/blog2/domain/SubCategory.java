package com.ripon.blog2.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sub_category")
public class SubCategory {
    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private String url;
    @OneToMany(mappedBy = "subCategory")
    private List<Article> articleList;
    @JoinColumn(name="category_id")
    @ManyToOne
    private Category category;

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

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory() {
    }

    public SubCategory(String name, String url, List<Article> articleList, Category category) {
        this.name = name;
        this.url = url;
        this.articleList = articleList;
        this.category = category;
    }
}
