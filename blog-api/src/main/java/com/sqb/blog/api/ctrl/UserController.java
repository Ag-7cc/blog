package com.sqb.blog.api.ctrl;

import com.sqb.blog.api.view.View;
import com.sqb.blog.api.view.ViewBody;
import com.sqb.blog.api.view.ViewUtil;
import com.sqb.blog.api.view.body.UserBody;
import com.sqb.blog.biz.bo.User;
import com.sqb.blog.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息
 * Created by vic.
 * Copyright 2016 by vic.shan
 * Date: 2016/5/30 20:42
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public View user(@PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id);
        UserBody body = new UserBody();
        body.setUser(user);
        View view = ViewUtil.defaultView();
        view.setBody(body);
        return view;
    }
}
