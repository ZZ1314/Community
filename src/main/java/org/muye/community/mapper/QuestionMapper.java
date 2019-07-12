package org.muye.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
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

    @Select("SELECT * FROM question")
    List<Question> list();
}
