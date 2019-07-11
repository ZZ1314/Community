package org.muye.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.muye.community.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author Zz
 * create 2019--07--11--14:15
 **/
@Repository
public interface UserMapper {
    @Insert("INSERT INTO user (account_id,name,token,gmt_create,gmt_modified,bio) " +
            "VALUES(#{accountID},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio})")
    void insert(User user);
    @Select("SELECT * FROM user WHERE token = #{token}")
    User findByToken(@Param("token") String token);
}
