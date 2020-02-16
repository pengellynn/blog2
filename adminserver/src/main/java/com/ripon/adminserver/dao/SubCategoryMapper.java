package com.ripon.adminserver.dao;

import com.ripon.adminserver.model.SubCategory;
import com.ripon.adminserver.model.SubCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubCategoryMapper {
    long countByExample(SubCategoryExample example);

    int deleteByExample(SubCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SubCategory record);

    int insertSelective(SubCategory record);

    List<SubCategory> selectByExample(SubCategoryExample example);

    SubCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SubCategory record, @Param("example") SubCategoryExample example);

    int updateByExample(@Param("record") SubCategory record, @Param("example") SubCategoryExample example);

    int updateByPrimaryKeySelective(SubCategory record);

    int updateByPrimaryKey(SubCategory record);
}