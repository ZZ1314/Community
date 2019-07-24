package org.muye.community.dto;

import lombok.Data;

/**
 * @author Zz
 * create 2019--07--23--10:54
 **/
@Data
public class CommentResultDTO {
    private Integer code;
    private String message;

    public static CommentResultDTO errorOf(Integer code,String message){
        CommentResultDTO commentResultDTO = new CommentResultDTO();
        commentResultDTO.setCode(code);
        commentResultDTO.setMessage(message);
        return commentResultDTO;
    }
}
