package com.zhaops.mlchecksvc.user.service;

import com.zhaops.mlchecksvc.user.dto.GoodsDto;

/**
 * @author zps_s
 */
public interface GoodsService {
    /**
     * 添加商品
     * @param goods
     * @return
     */
    GoodsDto save(GoodsDto goods);
}
