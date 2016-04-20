# Elec 总结一下 每天学到的技术点
itheima国家电力系统（10天）


##day01 系统框架的搭建

1.按条件查找的DAO：  
要先有BaseQuery、
	用HashMaP<>() buildWhere、LinkedHashMap<>() buildOrderBy

##day02 运行监控

1.js特效(一)：JS控制table数据过长  
	一个隐藏的 div  
	.innerHTML

2.js特效(二)：JS控制文本域或输入字符串的大小  
	引入一个 LimitedTextarea.js

3.js定时器	设置每10分钟刷新一次
```
	Window.onload=function(){
		setTimeout('refresh10()',1000*60);
	}
	function refresh10(){
		window.loaction.reload();
	}
```

4.**CKEditor+CKFinder文本编辑器**
	(CkFinder	文件上传)   

5.文本编辑器 处理大数据 需进行**截串存取**  

6.js特效(三)：JS进度条  
（实际是一个table、2个td td1.width每次+10px,td2.width-10px）
	setTimeout('',100); 0.1s执行一次

7.js特效(四)：JS浮动框

8.js特效(五)：highslideJs插件
