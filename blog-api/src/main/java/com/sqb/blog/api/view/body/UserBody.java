package com.sqb.blog.api.view.body;

import com.sqb.blog.api.view.ViewBody;
import com.sqb.blog.biz.bo.User;

/**
 * Created by vic.
 * Copyright 2016 by vic.shan
 * Date: 2016/5/30 20:48
 */
public class UserBody extends ViewBody{
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
