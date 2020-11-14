package com.zhaops.mlchecksvc.user.repository;

import com.zhaops.mlchecksvc.user.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zps_s
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

}
