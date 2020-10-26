package com.zhangle.produce.dao.user;

import com.zhangle.produce.domain.user.User;
import com.zhangle.produce.domain.user.UserFavorite;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface UserDao {

    /**
     * @description 根据username获取用户数量
     * @param username
     * @return java.lang.Integer
     * @author zl52074
     * @time 2020/4/21 22:02
     */
    @Select("select count(*) from user where username = #{username}")
    public Integer getCountByUsername(String username) throws SQLException;

    /**
     * @description 保存用户
     * @param user
     * @return void
     * @author zl52074
     * @time 2020/4/21 22:15
     */
    @Update("insert into user values(#{id},#{username},#{password},#{name},#{gender},#{email})")
    public void save(User user) throws SQLException;

    /**
     * @description 根据用户名和密码获取用户
     * @param user
     * @return com.zhangle.produce.domain.user.User
     * @author zl52074
     * @time 2020/4/21 22:38
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    public User findByUsernameAndPassword(User user) throws SQLException;

    /**
     * @description 根据id获取用户
     * @param id
     * @return com.zhangle.produce.domain.user.User
     * @author zl52074
     * @time 2020/4/21 23:26
     */
    @Select("select * from user where id = #{id}")
    public User findById(String id) throws SQLException;

    @Update("update user set password = #{password}, name = #{name}, gender = #{gender}, email = #{email}  where id = #{id}")
    public void update(User user) throws SQLException;

    /**
     * @description 保存用户收藏
     * @param userFavorite
     * @return void
     * @author zl52074
     * @time 2020/4/22 20:57
     */
    @Update("insert into user_favorite values (#{id},#{user},#{marketProduce})")
    public void saveFavorite(UserFavorite userFavorite) throws SQLException;

    /**
     * @description 根据userId获取用户收藏
     * @param userId
     * @return java.util.List<com.zhangle.produce.domain.user.UserFavorite>
     * @author zl52074
     * @time 2020/4/22 21:09
     */
    @Select("select * from user_favorite where user = #{userId}")
    public List<UserFavorite> findUserFavoriteByUserId(String userId) throws SQLException;


    @Update("delete from user_favorite where id = #{id}")
    public void deleteUserFavoriteById(String id) throws SQLException;

    /**
     * @description 取消收藏
     * @param userFavorite
     * @return java.util.List<com.zhangle.produce.domain.user.UserFavorite>
     * @author zl52074
     * @time 2020/4/22 21:28
     */
    @Select("select * from user_favorite where user = #{user} and marketProduce = #{marketProduce}")
    public UserFavorite findUserFavoriteByUserIdAndMarketProduce(UserFavorite userFavorite) throws SQLException;



}
