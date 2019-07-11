package org.muye.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.muye.community.dto.AccessTokenDTO;
import org.muye.community.dto.GithubUser;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Zz
 * create 2019--07--10--14:27
 **/
@Component
public class GithubProvider {
    public String getAccessToke(AccessTokenDTO accessTokenDTO) {
        //使用OkHttp
        OkHttpClient client = new OkHttpClient();
        //构建RequestBody的参数 传入参数为JSON
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        //创建RequestBody
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        //创建Request请求
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            //使用client发送Request请求获得Response
            Response response = client.newCall(request).execute();
            //转换成String
            String string = response.body().string();
            //分割出GitHub返回的AccessToken
            String token = string.split("&")[0].split("=")[1];
            return token;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        //使用OkHttp
        OkHttpClient client = new OkHttpClient();
        //创建Request请求
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            //使用client发送request请求并接收Response
            Response response = client.newCall(request).execute();
            //Response转换成String
            String string = response.body().string();
            //将返回的JSON数据注入GithubUser对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
