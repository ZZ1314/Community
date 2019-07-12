package org.muye.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zz
 * create 2019--07--10--15:03
 **/
//GithubUser对象包装类
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;

    public GithubUser(String name, Long id, String bio, String avatar_url) {
        this.name = name;
        this.id = id;
        this.bio = bio;
        this.avatar_url = avatar_url;
    }

    public GithubUser() {
    }
}
