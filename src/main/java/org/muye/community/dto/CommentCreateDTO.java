package org.muye.community.dto;

import lombok.Data;

/**
 * @author Zz
 * create 2019--07--22--22:03
 **/
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
