package org.muye.community.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.muye.community.model.Notification;
import org.muye.community.model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zz
 * create 2019--07--20--23:38
 **/
@Repository
public interface OriNotifyMapper {
    @Select("SELECT * FROM notification WHERE receiver = #{id} AND status = 0 ORDER BY gme_create DESC LIMIT #{offset},#{size}")
    List<Notification> queryNotifyByReceiver(Integer id, Integer offset, Integer size);
}
