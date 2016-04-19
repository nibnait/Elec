Skip to content
This repository
Search
Pull requests
Issues
Gist
 @nibnait
 Unwatch 1
  Star 0
  Fork 0 nibnait/Elec
 Code  Issues 0  Pull requests 0  Wiki  Pulse  Graphs  Settings
Branch: master Find file Copy pathElec/readme.md
a18b1bc  6 minutes ago
@nibnait nibnait 加了个CKEditor,但是文件上传还是有点问题
1 contributor
RawBlameHistory     30 lines (21 sloc)  554 Bytes
Elec 总结一下 每天学到的技术点
itheima国家电力系统（10天）

day01 系统框架的搭建

1.按条件查找的DAO：
要先有BaseQuery、 buildWhere

day02 运行监控

1.js特效(一)：JS控制table数据过长

2.js特效(二)：JS控制文本域或输入字符串的大小

3.js定时器 设置每10分钟刷新一次

    Window.onload=function(){
        setTimeout('refresh10()',1000*60);
    }
    function refresh10(){
        window.loaction.reload();
    }
4.CKEditor+CKFinder文本编辑器 (CkFinder 文件上传)

5.
Status API Training Shop Blog About
© 2016 GitHub, Inc. Terms Privacy Security Contact Help
