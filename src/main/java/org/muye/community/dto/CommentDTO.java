package org.muye.community.dto;

import lombok.Data;
import org.muye.community.model.User;

/**
 * @author Zz
 * create 2019--07--24--11:09
 **/
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String content;
    private User user;
}
