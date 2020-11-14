package com.zhaops.mlchecksvc.user.entity;

import com.zhaops.mlchecksvc.user.dto.GoodsDto;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zps_s
 */
@Entity
@Table(name = "goods")
public class Goods {
    public Goods() {
    }

    public Goods(GoodsDto goods) {
        this.id = goods.getId();
        this.code = goods.getCode();
        this.count = goods.getCount();
        this.userId = goods.getUserId();
        this.createTime = goods.getCreateTime();
        this.updateTime = goods.getUpdateTime();
        this.status = goods.getStatus();
    }

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 条码
     */
    @Column(name = "code", length = 200)
    private String code;

    @Column(name = "count")
    private int count;

    /**
     * 用户id
     */
    @Column(name = "userId")
    private Long userId;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "updateTime")
    private Date updateTime;

    /**
     * 状态
     */
    @Column(name = "status")
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
