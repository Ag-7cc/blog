package com.sqb.blog.biz.service;

import com.sqb.blog.biz.bo.User;
import com.sqb.blog.dal.dao.UserDao;
import com.sqb.blog.dal.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/5/29.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserById(Long uid) {
        UserModel userModel = userDao.selectById(uid);
        if (null == userModel) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        return user;
    }
}
