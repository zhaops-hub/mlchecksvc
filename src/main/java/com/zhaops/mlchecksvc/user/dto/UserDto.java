package com.zhaops.mlchecksvc.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhaops.mlchecksvc.user.entity.User;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SoYuan
 */
public class UserDto implements Serializable {

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.lastLoginTime = user.getLastLoginTime();
        this.companyName = user.getCompanyName();
        this.expired = user.getExpired();
        this.isDelete = user.getIsDelete();
    }

    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 最后登录日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 公司名称
     */
    private String companyName;


    /**
     * 是否删除
     */
    private int isDelete;

    /**
     * 过期时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date expired;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }
}
