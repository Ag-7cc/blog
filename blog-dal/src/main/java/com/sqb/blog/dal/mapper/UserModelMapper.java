package com.sqb.blog.dal.mapper;

import com.sqb.blog.dal.model.UserModel;
import com.sqb.blog.dal.model.UserModelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserModelMapper {
    int countByExample(UserModelExample example);

    int deleteByExample(UserModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    List<UserModel> selectByExample(UserModelExample example);

    UserModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserModel record, @Param("example") UserModelExample example);

    int updateByExample(@Param("record") UserModel record, @Param("example") UserModelExample example);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);
}