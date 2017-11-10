package com.mybatis.mapper;

import com.pojo.UserInfo;

import java.util.List;

/**
 * @author yantao.chen
 * @date 2017/11/9.
 */
public interface UserMapper {
    List<UserInfo> selectUserDetail();
}
