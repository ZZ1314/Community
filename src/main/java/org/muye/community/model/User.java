package org.muye.community.model;


import lombok.Data;

/**
 * @author Zz
 * create 2019--07--11--14:20
 **/
@Data
public class User {
    private Integer id;
    private String name;
    private String accountID;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}