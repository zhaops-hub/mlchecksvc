package com.zhaops.mlchecksvc.user.service.impl;

import com.zhaops.mlchecksvc.user.dto.GoodsDto;
import com.zhaops.mlchecksvc.user.entity.Goods;
import com.zhaops.mlchecksvc.user.repository.GoodsRepository;
import com.zhaops.mlchecksvc.user.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zps_s
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public GoodsDto save(GoodsDto goods) {
        Goods good = new Goods(goods);
        good = this.goodsRepository.save(good);
        return new GoodsDto(good);
    }
}
