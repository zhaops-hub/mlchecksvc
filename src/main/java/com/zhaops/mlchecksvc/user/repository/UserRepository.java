package com.zhaops.mlchecksvc.user.repository;

import com.zhaops.mlchecksvc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author SoYuan
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    @Query(value = "select * from userinfo where user_name = ?1 and password = ?2 ", nativeQuery = true)
    public User login(String userName, String password);

    /**
     * 通过用户名查找账号
     * @param userName
     * @return
     */
    @Query(value = "select * from userinfo where user_name = ?1 ", nativeQuery = true)
    public  User getUserByUserName(String userName);
}
