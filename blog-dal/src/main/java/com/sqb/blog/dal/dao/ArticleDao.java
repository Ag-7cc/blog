package com.sqb.blog.dal.dao;

import com.sqb.blog.dal.mapper.ArticleModelMapper;
import com.sqb.blog.dal.model.ArticleModel;
import com.sqb.blog.dal.model.ArticleModelExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 日志
 * Created by vic.
 * Copyright 2016 by vic.shan
 * Date: 2016/5/31 22:31
 */
@Repository
public class ArticleDao {

    @Autowired
    private ArticleModelMapper articleModelMapper;

    /**
     * 根据用户id查询日志列表
     * @param userId
     * @return
     */
    public List<ArticleModel> getArticleListByUid(Long userId){
        ArticleModelExample example =  new ArticleModelExample();
        example.or().andUserIdEqualTo(userId);
        example.setOrderByClause("create_time desc");
        List<ArticleModel> articleModelList = articleModelMapper.selectByExampleWithBLOBs(example);
        return articleModelList;
    }

}
