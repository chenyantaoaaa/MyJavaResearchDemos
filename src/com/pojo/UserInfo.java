package com.pojo;

/**
 * @author yantao.chen
 * @date 2017/11/8.
 */
public class UserInfo {
    /**
     * 用户id，主键
     */
    private String userid;

    private String username;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
