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



##day03 数据字典	SystemDDL

1.Hql投影查询  
	使用hql语句直接将投影查询的字段放置到对象中，例如
```
String hql = "SELECT DISTINCT new cn.itcast.elec.domain.ElecSystemDDL(o.keyword) FROM ElecSystemDDL o";  
List<ElecSystemDDL> list = this.getHibernateTemplate().find(hql);
```
 
2.使用JQuery 进行表格的添加删除tr

3.添加数据类型的JS校验：  
	 - Trim()!="";  
	 - 新增数据类型 不能与已有的一致  
	 - 新增数据类型的itemnames不能重复  

4.c3p0连接池  
	还可将hibernate.cfg.xml完全整合到beans.xml中

5.

VO	值对象	（查询条件对象）  
PO	持久层对象（与数据库对应）

6.数据字典 编辑页面那个Form2页面还是有问题！   
只能删除第1个itemname 无法删除其他的，Debug了一下 发现jQuery.remove 方法无效  

7.？ 当使用数据字典的时候，需要数据的转换，多了很多的sql语句（待优化！）

8.function.js/openWindow(sHref,strWidth,strHeight) 

9.