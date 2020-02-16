package com.ripon.blog2.domain;


import javax.persistence.*;
import java.sql.Date;
import java.util.Set;


@Entity
public class Article {
    @GeneratedValue
    @Id
    private Integer id;
    private String title;
    private String author;
    private Date date;
    private String summary;
    private String content;
    private String icon;
    private Integer view;
    private Integer like;
    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;
    @JoinColumn(name = "sub_category_id")
    @ManyToOne
    private SubCategory subCategory;
    @JoinTable(name="article_tag",
            joinColumns = {@JoinColumn(name="article_id",referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="tag_id",referencedColumnName = "id")}
    )
    @ManyToMany
    private Set<Tag> tags;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Article() {
    }

    public Article(String title, String author, Date date, String summary, String content, String icon, Integer view, Integer like, Category category, SubCategory subCategory, Set<Tag> tags) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.summary = summary;
        this.content = content;
        this.icon = icon;
        this.view = view;
        this.like = like;
        this.category = category;
        this.subCategory = subCategory;
        this.tags = tags;
    }
}
