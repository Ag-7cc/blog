package com.sqb.blog.api.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(value = Include.NON_NULL)
public class ViewBody {

    private Object bean;

    public ViewBody() {
    }

    public ViewBody(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

}
