package org.muye.community.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.muye.community.model.Comment;
import org.muye.community.model.CommentExample;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    int countByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    int deleteByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    int insert(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    int insertSelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    List<Comment> selectByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    Comment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbggenerated Mon Jul 22 22:09:14 CST 2019
     */
    int updateByPrimaryKey(Comment record);
}