<h1>Community项目</h1><br>
<h4>Code By Z<h4><hr>
<h1>GitHub常用命令</h1>
git status 查看当前状态<br>
git add .<br>
git commit -s "message"<br>
git push<hr>
<h1>请求Oauth登录 github</h1>
1.请求GitHub authorize接口<br>
    回调redirect-uri 同时携带code<br>
2.携带code请求access_token<br>
    返回access_token<br>
3.发送access_token信息请求user信息<br>
    返回user信息<br>
4.存入数据，更新登录状态<hr>