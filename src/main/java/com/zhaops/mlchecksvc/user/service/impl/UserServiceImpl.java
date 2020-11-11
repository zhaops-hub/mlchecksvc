package com.zhaops.mlchecksvc.user.service.impl;

import com.zhaops.mlchecksvc.user.dto.UserDto;
import com.zhaops.mlchecksvc.user.entity.User;
import com.zhaops.mlchecksvc.user.repository.UserRepository;
import com.zhaops.mlchecksvc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author SoYuan
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected EntityManager entityManager;


    private CriteriaQuery<User> criteriaQuery = null;

    /**
     * 安全查询创建工厂
     */
    private CriteriaBuilder cb = null;

    /**
     * Root 定义查询的From子句中能出现的类型
     */
    private Root<User> register = null;

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.cb = this.entityManager.getCriteriaBuilder();
        this.criteriaQuery = this.cb.createQuery(User.class);
        this.register = this.criteriaQuery.from(User.class);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        Page<User> page = this.userRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<User> getUsers(User where, Pageable pageable) {
        this.criteriaQuery.where(this.getPredicates(where));
        this.criteriaQuery.orderBy(cb.desc(register.<Date>get("id")));
        TypedQuery<User> query = this.entityManager.createQuery(this.criteriaQuery);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<User> result = query.getResultList();
        Page<User> ll = new PageImpl<>(result, pageable, result.size());
        return ll;
    }

    @Override
    public UserDto save(@org.jetbrains.annotations.NotNull UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setCompanyName(userDto.getCompanyName());
        user.setIsDelete(0);
        user.setExpired(userDto.getExpired());
        user = this.userRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public UserDto load(Long id) {
        User user = this.userRepository.findById(id).get();
        return new UserDto(user);
    }

    @Override
    public void delete(Long id) {
        User user = this.userRepository.getOne(id);
        user.setIsDelete(0);
        this.userRepository.save(user);
    }

    @Override
    public UserDto login(String userName, String password) {
        User user = this.userRepository.login(userName, password);

        if (user != null) {
            /**修改最后登录时间 */
            user.setLastLoginTime(new Date());
            user = this.userRepository.save(user);
        }

        return user != null ? new UserDto(user) : null;
    }

    @Override
    public UserDto existUserName(String userName) {
        User user = this.userRepository.getUserByUserName(userName);
        return user == null ? null : new UserDto(user);
    }


    /**
     * 设置过滤条件
     *
     * @param student
     * @return
     */
    private Predicate[] getPredicates(User user) {
        List<Predicate> predicate = new ArrayList<Predicate>();
        if (!user.getUserName().isEmpty()) {
            predicate.add(this.cb.like(this.register.<String>get("userName"), "%" + user.getUserName() + "%"));
        }

        return predicate.toArray(new Predicate[0]);
    }


}
