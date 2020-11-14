package com.zhaops.mlchecksvc.user.dto;

import com.zhaops.mlchecksvc.user.entity.Goods;
import com.zhaops.mlchecksvc.user.entity.User;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zps_s
 */
public class GoodsDto implements Serializable {

    public GoodsDto() {
    }

    public GoodsDto(Goods goods) {
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
    private Long id;

    /**
     * 条码
     */
    private String code;

    private int count;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 状态
     */
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
