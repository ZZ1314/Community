package org.muye.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.muye.community.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author Zz
 * create 2019--07--11--14:15
 **/
@Repository
public interface UserMapper {
    @Insert("INSERT INTO user (account_id,name,token,gmt_create,gmt_modified) " +
            "VALUES(#{accountID},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
