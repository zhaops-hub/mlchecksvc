package com.zhaops.mlchecksvc.user.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author SoYuan
 */
@Entity
@Table(name = "userinfo")
public class User {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户名
     */
    @Column(name = "user_name", length = 200)
    private String userName;
    /**
     * 密码
     */
    @Column(name = "password", length = 200)
    private String password;
    /**
     * 最后登录日期
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 是否删除
     */
    @Column(name = "isDelete")
    private int isDelete;

    /**
     * 过期时间
     */
    @Column(name = "expired")
    private Date expired;

    /**
     * 是否是超级管理员
     */
    @Column(name = "isAdmin")
    private int isAdmin;



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

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
