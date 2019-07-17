package org.muye.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.muye.community.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author Zz
 * create 2019--07--11--14:15
 **/
@Repository
public interface UserMapper {
    @Insert("INSERT INTO user (account_id,name,token,gmt_create,gmt_modified,bio,avatar_url) " +
            "VALUES(#{accountID},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("SELECT * FROM user WHERE token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("SELECT * FROM user WHERE id = #{creator}")
    User findById(@Param("creator") Integer creator);

    @Select("SELECT * FROM user WHERE account_id = #{id}")
    User findByAccountId(@Param("id")Integer id);
    @Update("UPDATE user SET token = #{token} WHERE id = #{id}")
    void updateUserToken(@Param("id") Integer id, @Param("token") String token);
}
