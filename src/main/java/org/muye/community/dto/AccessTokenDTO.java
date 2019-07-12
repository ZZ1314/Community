package org.muye.community.dto;

import lombok.Data;

/**
 * @author Zz
 * create 2019--07--10--14:28
 **/


//        client_id	string	Required. The client ID you received from GitHub for your GitHub App.
//        client_secret	string	Required. The client secret you received from GitHub for your GitHub App.
//        code	string	Required. The code you received as a response to Step 1.
//        redirect_uri	string	The URL in your application where users are sent after authorization.
//        state	string	The unguessable random string you provided in Step 1.
//AccessToken数据传输包装类
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

    public AccessTokenDTO(String client_id, String client_secret, String code, String redirect_uri, String state) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.redirect_uri = redirect_uri;
        this.state = state;
    }

    public AccessTokenDTO() {
    }
}