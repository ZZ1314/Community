package org.muye.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.muye.community.model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Zz
 * create 2019--07--11--22:52
 **/
@Repository
public interface QuestionMapper {

    @Insert("INSERT INTO question (title,description,gmt_create,gmt_modified,creator,tag) " +
            "VALUES(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("SELECT * FROM question ORDER BY gmt_create DESC LIMIT #{offset},#{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("SELECT count(1) FROM question")
    Integer count();
    @Select("SELECT * FROM question WHERE creator = #{id} ORDER BY gmt_create DESC LIMIT #{offset},#{size}")
    List<Question> queryQuestionByCreator(Integer id,Integer offset,Integer size);
    @Select("SELECT count(1) FROM question WHERE creator = #{id}")
    Integer queryCountByCreator(Integer id);
    @Select("SELECT * FROM question WHERE id = #{id}")
    Question queryQuestionById(Integer id);
    @Update("UPDATE question SET title=#{title},gmt_modified=#{gmtModified},tag=#{tag},description=#{description} WHERE id = #{id}")
    void updateQuestion(Question question);
}
