package com.zhangle.produce.service.user.impl;


import com.zhangle.produce.dao.user.UserDao;
import com.zhangle.produce.domain.user.User;
import com.zhangle.produce.domain.user.UserFavorite;
import com.zhangle.produce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/3/21 13:59
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * @description 根据username获取数量
     * @param username
     * @return java.lang.Integer
     * @author zl52074
     * @time 2020/4/21 22:16
     */
    @Override
    public Integer getCountByUsername(String username) throws SQLException {
        return userDao.getCountByUsername(username);
    }

    /**
     * @description 保存用户
     * @param user
     * @return void
     * @author zl52074
     * @time 2020/4/21 22:16
     */
    @Override
    public void save(User user) throws SQLException {
        userDao.save(user);
    }

    /**
     * @description 根据用户名和密码获取用户
     * @param user
     * @return com.zhangle.produce.domain.user.User
     * @author zl52074
     * @time 2020/4/21 22:39
     */
    @Override
    public User findByUsernameAndPassword(User user) throws SQLException {
        return userDao.findByUsernameAndPassword(user);
    }

    /**
     * @description 根据id查询user
     * @param id
     * @return com.zhangle.produce.domain.user.User
     * @author zl52074
     * @time 2020/4/22 20:59
     */
    @Override
    public User findById(String id) throws SQLException {
        return userDao.findById(id);
    }

    /**
     * @description 更新用户信息
     * @param user
     * @return void
     * @author zl52074
     * @time 2020/4/22 20:59
     */
    @Override
    public void update(User user) throws SQLException {
        userDao.update(user);
    }

    /**
     * @description 保存用户收藏
     * @param userFavorite
     * @return void
     * @author zl52074
     * @time 2020/4/22 20:59
     */
    @Override
    public void saveFavorite(UserFavorite userFavorite) throws SQLException {
        userDao.saveFavorite(userFavorite);
    }

    /**
     * @description 根据userId获取用户收藏
     * @param userId
     * @return java.util.List<com.zhangle.produce.domain.user.UserFavorite>
     * @author zl52074
     * @time 2020/4/22 21:10
     */
    @Override
    public List<UserFavorite> findUserFavoriteByUserId(String userId) throws SQLException {
        return userDao.findUserFavoriteByUserId(userId);
    }

    /**
     * @description 取消收藏
     * @param id
     * @return java.util.List<com.zhangle.produce.domain.user.UserFavorite>
     * @author zl52074
     * @time 2020/4/22 21:33
     */
    @Override
    public void deleteUserFavorite(String id) throws SQLException {
        userDao.deleteUserFavoriteById(id);
    }

    @Override
    public UserFavorite findUserFavoriteByUserIdAndMarketProduce(UserFavorite userFavorite) throws SQLException {
        return userDao.findUserFavoriteByUserIdAndMarketProduce(userFavorite);
    }


}
