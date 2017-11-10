package com.mybatis.imitateMybatis;

/**
 * @author chenyantao
 * @date 2017/11/10.
 */
public class MyEnviroment {
    private String id;

    private String transactionManager;

    private String driver;

    private String url;

    private String username;

    private String password;

    public MyEnviroment(String driver, String url, String username, String password,String id,String transactionManager) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.id = id;
        this.transactionManager = transactionManager;
    }

    public MyEnviroment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(String transactionManager) {
        this.transactionManager = transactionManager;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MyEnviroment{" +
                "id='" + id + '\'' +
                ", transactionManager='" + transactionManager + '\'' +
                ", driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
