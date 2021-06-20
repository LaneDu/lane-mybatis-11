package com.lagou.pojo;

import javax.persistence.Table;

@Table(name = "user")
public class UserM {

    private Integer id;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserM{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
