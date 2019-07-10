package org.muye.community.controller;

import org.muye.community.dto.AccessTokenDTO;
import org.muye.community.dto.GithubUser;
import org.muye.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Zz
 * create 2019--07--10--14:20
 **/
@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(
                "74f355cbac8c0dca15a7",
                "0e13f53e86a983bf80e88db2bcd8a5d5e457257f",
                code,
                "http://127.0.0.1:8080/callback",
                state
        );
//        accessTokenDTO.setCode(code);
//        accessTokenDTO.setRedirect_uri("http://127.0.0.1:8080/callback");
//        accessTokenDTO.setState(state);
//        accessTokenDTO.setClient_id("74f355cbac8c0dca15a7");
//        accessTokenDTO.setClient_secret("0e13f53e86a983bf80e88db2bcd8a5d5e457257f");
        String accessToke = githubProvider.getAccessToke(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToke);
        System.out.println(user);
        return "index";
    }
}
