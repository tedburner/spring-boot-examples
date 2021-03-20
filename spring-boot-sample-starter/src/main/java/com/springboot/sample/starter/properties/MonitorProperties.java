package com.springboot.sample.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: lingjun.jlj
 * @date: 2021/3/20 21:42
 * @description:
 */
@ConfigurationProperties(prefix = "demo.monitor")
public class MonitorProperties {

    private String loginUrl;
    private String username;
    private String password;
    private String serverUrl;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
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

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public String toString() {
        return "MonitorProperties{" +
                "loginUrl='" + loginUrl + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", serverUrl='" + serverUrl + '\'' +
                '}';
    }
}
