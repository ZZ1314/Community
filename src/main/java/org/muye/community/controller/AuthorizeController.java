package org.muye.community.controller;

import org.muye.community.dto.AccessTokenDTO;
import org.muye.community.dto.GithubUser;
import org.muye.community.mapper.UserMapper;
import org.muye.community.model.User;
import org.muye.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        //生成请求GitHub Token的数据传输对象AccessTokenDTO
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(
                clientId,
                clientSecret,
                code,
                clientUri,
                state
        );
        String accessToke = githubProvider.getAccessToke(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToke);
        System.out.println(githubUser);

//           如果请求githubUser不为空 则登录成功
        if (githubUser != null) {
//            生成本地Token
            String token = UUID.randomUUID().toString();
//            新建本地User对象
            User user = new User();
            user.setAccountID(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
//          写入cookie
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
            return "redirect:/";
        } else {
//            登录失败 重新登录
            return "redirect:/";
        }
    }
}
