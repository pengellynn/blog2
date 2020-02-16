package com.ripon.adminserver.dto;

import java.sql.Date;

public class ArticleGeneral {
    private Integer id;

    private String title;

    private String author;

    private Date date;

    private String categoryName;

    private String subCategoryName;

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


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

    public ArticleGeneral() {
    }

    public ArticleGeneral(Integer id, String title, String author, Date date, String categoryName, String subCategoryName) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.categoryName = categoryName;
        this.subCategoryName = subCategoryName;
    }

    @Override
    public String toString() {
        return "ArticleGeneral{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", categoryName='" + categoryName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                '}';
    }
}
