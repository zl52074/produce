package com.zhangle.produce.service.user;

import com.zhangle.produce.domain.user.User;
import com.zhangle.produce.domain.user.UserFavorite;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public Integer getCountByUsername(String username) throws SQLException;
    public void save(User user) throws SQLException;
    public User findByUsernameAndPassword(User user) throws SQLException;
    public User findById(String id) throws SQLException;
    public void update(User user) throws SQLException;
    public void saveFavorite(UserFavorite userFavorite) throws SQLException;
    public List<UserFavorite> findUserFavoriteByUserId(String userId) throws SQLException;
    public void deleteUserFavorite(String id) throws SQLException;
    public UserFavorite findUserFavoriteByUserIdAndMarketProduce(UserFavorite userFavorite) throws SQLException;
}
