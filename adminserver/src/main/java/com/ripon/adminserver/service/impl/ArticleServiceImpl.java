package com.ripon.adminserver.service.impl;

import com.ripon.adminserver.dao.ArticleMapper;
import com.ripon.adminserver.dao.TagMapper;
import com.ripon.adminserver.dto.ArticleDetail;
import com.ripon.adminserver.dto.ArticleGeneral;
import com.ripon.adminserver.model.Article;
import com.ripon.adminserver.model.ArticleExample;
import com.ripon.adminserver.model.Tag;
import com.ripon.adminserver.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagMapper tagMapper;

    @Override
    public ArticleDetail getArticle(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        List<Integer> tagIds = articleMapper.selectTagIdById(id);
        List<String> tagNames = tagMapper.selectNameById(tagIds);
        ArticleDetail articleDetail = new ArticleDetail();
        BeanUtils.copyProperties(article, articleDetail);
        articleDetail.setTagNames(tagNames);
        return articleDetail;
    }

    @Override
    public int deleteArticle(Integer id) {
        int count1 = articleMapper.deleteByPrimaryKey(id);
        List<Integer> tagIds = articleMapper.selectTagIdById(id);
        int count2 = articleMapper.deleteTagIdById(id);
        if (count2 != tagIds.size()) {
            return -1;
        }
        int count3 = deleteUnusedTag(tagIds);
        if (count3 == -1) {
            return -1;
        }
        return count1;
    }

    @Override
    public int updateArticle(ArticleDetail articleDetail) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDetail, article, "tags");
        //更新文章表
        int count1 = articleMapper.updateByPrimaryKey(article);
        Integer id = articleDetail.getId();
        //文章修改前的标签
        List<Integer> oldTagIds = articleMapper.selectTagIdById(id);
        //从中间表删除原有的文章——标签映射
        int count2 = articleMapper.deleteTagIdById(id);
        //删除中间表记录失败
        if (count2 != oldTagIds.size()) {
            return -1;
        }
        //删除已无文章绑定的标签
        int count3 = deleteUnusedTag(oldTagIds);
        if (count3 == -1) {
            return -1;
        }
        //文章修改后的标签
        List<Integer> tagIds = getTagIds(articleDetail);
        if (tagIds == null) {
            return -1;
        }
        //往中间表中插入新的文章——标签映射
        int count4 = articleMapper.insertTagIdById(id, tagIds);
        //更新成功
        if (count4 != tagIds.size()) {
            return -1;
        }
        return count4;
    }

    @Override
    public int addArticle(ArticleDetail articleDetail) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDetail, article, "tagNames");
        Date date = new Date(System.currentTimeMillis());
        article.setDate(date);
        int count1 = articleMapper.insert(article);
        List<Integer> tagIds = getTagIds(articleDetail);
        if (tagIds == null) {
            return -1;
        }
        Integer id = article.getId();
        int count2 = articleMapper.insertTagIdById(article.getId(), tagIds);
        if (count2 != tagIds.size()) {
            return -1;
        }
        return count1;
    }

    @Override
    public List<ArticleGeneral> getAllArticleGeneral() {
        return articleMapper.selectAllArticleGeneral();
    }

    @Override
    public int batchDeleteArticle(List<Integer> ids) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        // 文章表中删除文章
        int count1 = articleMapper.deleteByExample(example);
        for (Integer id : ids) {
            //一篇文章对应的所有标签
            List<Integer> tagIds = articleMapper.selectTagIdById(id);
            //删除中间表相关的文章——标签映射
            int count2 = articleMapper.deleteTagIdById(id);
            if (count2 != tagIds.size()) {
                return -1;
            }
            //删除已无文章绑定的标签
            int count3 = deleteUnusedTag(tagIds);
            if (count3 == -1) {
                return -1;
            }
        }
        return count1;
    }

    // 获取文章修改后的标签ID
    private List<Integer> getTagIds(ArticleDetail articleDetail) {
        //获取所有标签名字
        List<String> tagNames = articleDetail.getTagNames();
        List<Integer> tagIds = new ArrayList<>();
        //根据名字查出每一个标签，并获取每一个标签的ID，若不存在则新建插入或获取
        for (String tagName : tagNames) {
            Tag tag = tagMapper.selectByName(tagName);
            //标签不存在，新建标签
            if (tag == null) {
                Tag newTag = new Tag();
                newTag.setName(tagName);
                newTag.setCategoryId(articleDetail.getCategoryId());
                int count = tagMapper.insert(newTag);
                //新建标签插入失败
                if (count != 1) {
                    return null;
                }
                tagIds.add(newTag.getId());
            } else {
                tagIds.add(tag.getId());
            }
        }
        return tagIds;
    }

    //删除无文章绑定的标签
    private int deleteUnusedTag(List<Integer> tagIds) {
        for (Integer tagId : tagIds) {
            //从中间表中查找是否还存在与文章的映射关系
            List<Integer> ids = articleMapper.selectIdByTagId(tagId);
            //映射不存在，删除标签
            if (ids.size() == 0) {
                int count = tagMapper.deleteByPrimaryKey(tagId);
                //删除失败
                if (count != 1) {
                    return -1;
                }
            }
        }
        return 1;
    }
}
//    private List<Tag> getTagByName(List<String> tagNames) {
//        TagExample example = new TagExample();
//        TagExample.Criteria criteria = example.createCriteria();
//        criteria.andNameIn(tagNames);
//        return tagMapper.selectByExample(example);
//    }
//        List<Tag> tags = getTagByName(tagNames);
//        List<Integer> tagIds = new ArrayList<>();
//        if (tagNames.size() != tags.size()) {
//            for (String tagName : tagNames) {
//                boolean isExist = false;
//                for (Tag tag : tags) {
//                    if (tagName.equals(tag.getName())) {
//                        tagIds.add(tag.getId());
//                        isExist = true;
//                        break;
//                    }
//                }
//                if (!isExist) {
//                    Tag newTag = new Tag();
//                    newTag.setName(tagName);
//                    newTag.setCategoryId(articleDetail.getCategoryId());
//                    int count = tagMapper.insert(newTag);
//                    if (count != 1) {
//                        return null;
//                    }
//                    tagIds.add(newTag.getId());
//                }
//            }
//        } else {
//            for (Tag tag : tags) {
//                tagIds.add(tag.getId());
//            }
//        }