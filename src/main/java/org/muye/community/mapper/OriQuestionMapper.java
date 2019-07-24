package org.muye.community.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.muye.community.model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zz
 * create 2019--07--20--23:38
 **/
@Repository
public interface OriQuestionMapper {
    int updateByPrimaryKey(Question record);
    @Select("SELECT * FROM question ORDER BY gmt_modified DESC LIMIT #{offset},#{size}")
    List<Question> list(Integer offset, Integer size);
    @Select("SELECT * FROM question WHERE creator = #{id} ORDER BY gmt_create DESC LIMIT #{offset},#{size}")
    List<Question> queryQuestionByCreator(Integer id, Integer offset, Integer size);
    @Update("UPDATE question SET view_count = view_count + 1 WHERE id = #{id}")
    void increaseViewCount(Integer id);
    @Update("UPDATE question SET comment_count = comment_count + 1 WHERE id = #{id}")
    void increaseCommentCount(Integer id);
}
