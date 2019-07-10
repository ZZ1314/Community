##Community项目
Code By Z
##GitHub常用命令
git status 查看当前状态
git add .
git commit -s "message"
git push

##请求Oauth登录 github
1.请求GitHub authorize接口
    回调redirect-uri 同时携带code
2.携带code请求access_token
    返回access_token
3.发送access_token信息请求user信息
    返回user信息
4.存入数据，更新登录状态