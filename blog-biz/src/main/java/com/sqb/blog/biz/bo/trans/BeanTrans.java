package com.sqb.blog.biz.bo.trans;

import com.sqb.blog.biz.bo.Article;
import com.sqb.blog.dal.model.ArticleModel;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BeanTrans {

    /**
     * 转日志
     *
     * @param articleModel
     * @return
     */
    public static Article trans(ArticleModel articleModel) {
        Article article = null;
        if (null != articleModel) {
            article = new Article();
            BeanUtils.copyProperties(articleModel, article);
        }
        return article;
    }

    /**
     * 转日志列表
     *
     * @param articleModelList
     * @return
     */
    public static List<Article> trans(List<ArticleModel> articleModelList) {
        List<Article> articleList = null;
        if (null != articleModelList && !articleModelList.isEmpty()) {
            articleList = new ArrayList<>();
            Article article = null;
            for (ArticleModel model : articleModelList) {
                article = new Article();
                BeanUtils.copyProperties(model, article);
                articleList.add(article);
            }
        }
        return articleList;
    }
}
