package com.zhaops.mlchecksvc.user.repository;

import com.zhaops.mlchecksvc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SoYuan
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
