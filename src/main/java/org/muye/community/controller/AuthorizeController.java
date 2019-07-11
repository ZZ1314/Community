package org.muye.community.controller;

import org.muye.community.dto.AccessTokenDTO;
import org.muye.community.dto.GithubUser;
import org.muye.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zz
 * create 2019--07--10--14:20
 **/
@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;
//根据properties配置文件注入属性
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.redirect.uri}")
    private String clientUri;
    @Value("${github.client.secret}")
    private String clientSecret;

    @GetMapping(value = "/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(
                clientId,
                clientSecret,
                code,
                clientUri,
                state
        );
        String accessToke = githubProvider.getAccessToke(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToke);
        System.out.println(user);
        if(user!=null){
            request.getSession().setAttribute("user", user);
            return "redirect:/";
//           登录成功 写cookie和session
        }else {
//            登录失败 重新登录
            return "redirect:/";
        }
    }
}
