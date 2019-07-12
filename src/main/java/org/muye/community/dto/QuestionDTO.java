package org.muye.community.dto;

import lombok.Data;
import org.muye.community.model.User;

/**
 * @author Zz
 * create 2019--07--12--22:52
 **/
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
