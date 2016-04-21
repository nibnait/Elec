# Elec 总结一下 每天学到的技术点
itheima国家电力系统（10天）  
这是一个简单的SSH整合项目。


[TOC]

##day01 系统框架的搭建


1. 按条件查找的DAO：  
要先有BaseQuery、
	用HashMaP<>() buildWhere、LinkedHashMap<>() buildOrderBy

##day02 运行监控

1. js特效(一)：JS控制table数据过长  
	一个隐藏的 div  
	.innerHTML

2. js特效(二)：JS控制文本域或输入字符串的大小  
	引入一个 LimitedTextarea.js

3. js定时器	设置每10分钟刷新一次
```
	Window.onload=function(){
		setTimeout('refresh10()',1000*60);
	}
	function refresh10(){
		window.loaction.reload();
	}
```

4. **CKEditor+CKFinder文本编辑器**  
	(CkFinder	文件上传)   （*！BUG）

5. 文本编辑器 处理大数据 需进行**截串存取**  

6. js特效(三)：JS进度条  
（实际是一个table、2个td td1.width每次+10px,td2.width-10px）
	setTimeout('',100); 0.1s执行一次

7. js特效(四)：JS浮动框

8. js特效(五)：highslideJs插件



##day03 数据字典	SystemDDL

1. Hql投影查询  
	使用hql语句直接将投影查询的字段放置到对象中，例如
```
String hql = "SELECT DISTINCT new cn.itcast.elec.domain.ElecSystemDDL(o.keyword) FROM ElecSystemDDL o";  
List<ElecSystemDDL> list = this.getHibernateTemplate().find(hql);
```

2. 使用JQuery 进行表格的添加删除tr

3. 添加数据类型的JS校验：  
	 - Trim()!="";  
	 - 新增数据类型 不能与已有的一致  
	 - 新增数据类型的itemnames不能重复  

4. c3p0连接池  
	还可将hibernate.cfg.xml完全整合到beans.xml中


5. 
VO	栈值对象	  
PO	持久层对象  
Hibernate 持久化、游离态、...？？？


6. 数据字典 编辑页面那个Form2页面还是有问题！   
只能删除第1个itemname 无法删除其他的，Debug了一下 发现jQuery.remove 方法无效  （*！BUG）

7. ？ 当使用数据字典的时候，需要数据的转换，多了很多的sql语句（待优化！）（*！BUG）  
  打开子窗口
function.js/openWindow(sHref,strWidth,strHeight) 


##day04 用户管理


1. jquery的ajax实现二级联动

2. jquery的ajax实现登录名的校验  
鼠标失去焦点时 执行checkUser(this)方法：onblur="checkUser(this);"  

3. jquery对象校验上传的表单
?(!BUG) 为什么不执行这句话了？？？
```$("#Form1").attr("action","${pageContext.request.contextPath }/system/elecUserAction_save.do");
```

4. 用户表单的保存：  
	 - 跳转到 close.jsp  
		 关闭子页面，并刷新父页面      
```	opener.location.reload();//子页面操作父页面用opener属性    
    window.close();  
```  
5. 文件上传
	1. 方案一：使用URL存放路径
	 - struts2	文件上传
	 - **fileURL 是文件在服务器上的相对路径**
	 - <result name="input">/WEB-INF/page/system/error.jsp</result>
		```
			errorReason:
		    <s:fielderror></s:fielderror><br>
    		<s:actionerror/><br>
		```

	 - 改变struts2上次文件的大小
		```
		<constant name="struts.multipart.maxSize" value="20971520"></constant>
		```
	2. 方案二：使用Blob字段 将数据直接存放在数据库中

6. 用户编辑	
	1. 表单回显 *OGNL表达式*？？
	2. 将userFile按上传时间降序排序：  
		在user.hbm.xml的userFile那一项 加个order-by="progressTime desc"属性即可

7. 添加spring解决hibernate懒加载：  

	在web.xml的struts2过滤器前 *openSessionInViewFilter*


8. 文件下载
	- 方式一：javaweb 【InputStreame OutputStream】
		- 输入流写入输出流： 一个文件从-1开始，-1结束？？？ 
		```
			for(int b=-1;(b=in.read())!=-1;){
				out.write(b);
			}
		```
		- 内容的处理形式：

			- response.setHeader("Content-disposition","inline");  
	 	内联方式：	即直接打开

			- response.setHeader("Content-disposition","attachment;filename="+filename):  
	 	附件方式：	
		
		- 处理文件名中的**中文**
		```
		filename = newString(filename.getByte("gbk"), "iso8859-1");
		```
	- 方式二：struts2  
	直接将 InputStream 压入值栈顶 over.

9. 用户删除

	不仅要删除数据库中的， 还要把服务器上的userFiles.delete;  
用户&用户文件-->级联删除：
	在user.hbm.xml的userFile那一项 加个cascade="delete"属性

10. 查看用户明细
	1. 在"userEdit.jsp"中加个viewflag属性，  
		然后 将查看用户页面中一些不需要显示的信息 将其viewflag
	2. Action.edit中 获得viewflag VO对象 并将其转化为PO对象
	3. 即 实现了 编辑用户 和查看用户页面的通用

11.使用md5的密码加密，处理用户的密码  
	[MD5keyBean.java](com.dcfun.elec.utils)是个好东西！