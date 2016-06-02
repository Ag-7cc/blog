package com.sqb.blog.biz.service;

import com.sqb.blog.biz.bo.Article;
import com.sqb.blog.biz.bo.trans.BeanTrans;
import com.sqb.blog.dal.dao.ArticleDao;
import com.sqb.blog.dal.model.ArticleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ��־
 * Created by vic.
 * Copyright 2016 by vic.shan
 * Date: 2016/5/31 22:29
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    /**
     * �����û�id��ѯ��־�б�
     * @param userId
     * @return
     */
    public List<Article> getArticleListByUid(Long userId){
        List<ArticleModel> articleModelList = articleDao.getArticleListByUid(userId);
        return BeanTrans.trans(articleModelList);
    }
}
