package com.sqb.blog.dal.mapper;

import com.sqb.blog.dal.model.ArticleModel;
import com.sqb.blog.dal.model.ArticleModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleModelMapper {
    int countByExample(ArticleModelExample example);

    int deleteByExample(ArticleModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticleModel record);

    int insertSelective(ArticleModel record);

    List<ArticleModel> selectByExampleWithBLOBs(ArticleModelExample example);

    List<ArticleModel> selectByExample(ArticleModelExample example);

    ArticleModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ArticleModel record, @Param("example") ArticleModelExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleModel record, @Param("example") ArticleModelExample example);

    int updateByExample(@Param("record") ArticleModel record, @Param("example") ArticleModelExample example);

    int updateByPrimaryKeySelective(ArticleModel record);

    int updateByPrimaryKeyWithBLOBs(ArticleModel record);

    int updateByPrimaryKey(ArticleModel record);
}