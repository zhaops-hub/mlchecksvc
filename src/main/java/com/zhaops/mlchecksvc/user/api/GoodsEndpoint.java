package com.zhaops.mlchecksvc.user.api;

import com.zhaops.mlchecksvc.user.common.UserLoginToken;
import com.zhaops.mlchecksvc.user.dto.GoodsDto;
import com.zhaops.mlchecksvc.user.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zps_s
 */
@RestController
@RequestMapping("/goods")
public class GoodsEndpoint {
    @Autowired
    private GoodsService goodsServices;

    @UserLoginToken
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public GoodsDto add(@RequestBody GoodsDto goodsDto) {
        return  this.goodsServices.save(goodsDto);
    }
}
