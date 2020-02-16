package com.ripon.blog2.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Tag {
    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    @Column(name="category_id")
    private Integer categoryId;
    @ManyToMany(mappedBy = "tags",fetch = FetchType.LAZY)
    private Set<Article> articles;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Tag() {
    }

    public Tag(String name, Integer categoryId, Set<Article> articles) {
        this.name = name;
        this.categoryId = categoryId;
        this.articles = articles;
    }
}
