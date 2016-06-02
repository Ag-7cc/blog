package com.sqb.blog.dal.dao;

import com.sqb.blog.dal.mapper.UserModelMapper;
import com.sqb.blog.dal.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/5/29.
 */
@Repository
public class UserDao {

    @Autowired
    private UserModelMapper userModelMapper;

    public UserModel selectById(Long uid) {
        return userModelMapper.selectByPrimaryKey(uid);
    }
}
