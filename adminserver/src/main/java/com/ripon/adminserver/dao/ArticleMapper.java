package com.ripon.adminserver.dao;

import com.ripon.adminserver.dto.ArticleDetail;
import com.ripon.adminserver.dto.ArticleGeneral;
import com.ripon.adminserver.model.Article;
import com.ripon.adminserver.model.ArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExampleWithBLOBs(ArticleExample example);

    List<Article> selectByExample(ArticleExample example);

    Article selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<ArticleGeneral> selectAllArticleGeneral();

    ArticleDetail selectDetailById(Integer id);

    List<Integer> selectTagIdById(Integer id);

    int deleteTagIdById(Integer id);

    int insertTagIdById(@Param("id") Integer id, @Param("tagIds") List<Integer> tagIds);

    int deleteTagIdByIds(@Param("ids") List<Integer> ids);

    List<Integer> selectIdByTagId(Integer tagId);
}