# Elec 总结一下 每天学到的技术点
itheima国家电力系统（10天）  
这是一个 基于maven的SSH demo整合项目。


[TOC]

##day01 系统框架的搭建


1. 按条件查找的DAO：  
要先有BaseQuery、  
	 - 用HashMaP<String, Object>()  -->buildWhere、  
	 - LinkedHashMap<String, String>()  -->buildOrderBy

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


##day04 用户管理（【用户-用户文件】 一对多的操作）


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
	1. 表单回显 **${OGNL表达式}**？？(从栈顶 想读啥 直接写)
	2. 将userFile按上传时间降序排序：  
		在user.hbm.xml的userFile那一项 加个order-by="progressTime desc"属性即可

7. 添加spring解决hibernate懒加载：  

	在web.xml的struts2过滤器前 *openSessionInViewFilter*
这样 一个servlet的生命周期 就可以延长至一个 request到response整个周期了。  
延迟了**session**的关闭时间，，，容易造成*“假死”*现象（一个session未关闭，则另一个session链接就连不上了。）

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
	[MD5keyBean.java](https://github.com/nibnait/Elec/blob/master/src/main/java/com/dcfun/elec/utils/MD5keyBean.java)是个好东西！

##day05角色管理（权限分配）(多对多的操作)

1. 
	 - 角色-权限 	多对多关系  
	角色是维护方

	 - 用户-角色 多对一的关系  
	 用户是维护方 ,
	
2. hibernate主键策略：  
>
	1. uuid		32位唯一的uuid
	2. increment	自增长
	3. assigned	代理主键（hibernate自动生成、一个没有任何含义的唯一主键）
	4. 。。。？？


3. 遍历所有 权限  
	使用 二级List 

4. jquery实现 首页权限选择的复选框的联动操作

>	[attribute^=value]	匹配给定的属性是以某些值**开始**的元素

>	[attribute$=value]	匹配给定的属性是以某些值**结尾**的元素

火狐firedebug jquery函数执行完 返回的那些东西都是什么？

5. 使用**包含**的技术 匹配 角色所具有的权限  
	（popedomList 中的所有mid->String  然后调用contains()）

6. 编辑页面的一些JS控制
	1. 一行jquery 搞定“全选/全不选”
	2. table的打开/关闭

7. 根据角色 查询对应的用户！！  
	（如果是普通用户、这数量也太大了、、、此处应该放到 用户编辑页面去做）
	 
8. 再加一个 添加权限的功能！！（！BUG）
9. 多对多的删除
	 - 级联删除 *谨慎使用
	
	
##day06 系统登录&权限管理模块

1. struts2校验机制 
	 - ValidationAware  -->
		 - addActionError(String anErrorMessage);  
		 <s:actionError />
		 - addFieldError(String fieldName, String errorMessage);
		
	 - 覆盖更改struts标签的样式（struts2-core --> template -->simple --> <s:... />)  
	 在src下 创建一个 template/simple文件夹，然后想覆盖那个 就复制那个标签，然后 直接更改。

2. HashMap 与 HashTable的区别：‘
	1. HashTable是线程安全的， 比如在登陆的时候 存放一些用户的 权限、角色信息

3. hibernate懒加载问题 之四种解决方案（详见 word笔记） 

4. 验证码	系统生成的imageNumber放到session域中，与request传过来的imageNumber比较   
	1. 正常应该写个LogonUtils的java类，输入图片流！
	2. 这里用的一个image.jsp嵌套的java代码、、好吧 

5. 额额额 又发现了一个问题（！BUG）前端向后台传密码 居然是明文传输、、、  
（要不要 先在前端用md5加个密，然后 再在后台 将密码二次加密后 再存到数据库中？[[-> _-》] 还是先不给自己找麻烦了]）

6. 利用客户端的Cookie（保存用户名、密码）“记住我”  
	1. Cookie中 不能存放中文
	2. 有效时间（生命周期）
	3. 有效路径
	4. JSESSIONID 浏览器自动生成 自动存在Cookie中

7. 拦截器  -->只能拦截.do（跳转action的操作），不能拦截跳转jsp servlet的操作  
	此时 需引入过滤器filter：  
	在跳转index.jsp页面之前 先获取Cookie, 传值的方式给index.jsp  
**自定义过滤器，放置到web.xml的struts2过滤器的前面**  

8. el表达式 获取request中的值： ${requestScope.password} 
9. [jquery的ztree插件]()的使用（完成动态加载树型结构）
10. HQL ：  
	**请不要 尝试封装 in语句**， hibernate找不到的。
11. xml中加个<![CDATA[...]]>  ...无需转义
12. ###角色和权限的控制  

	 -  角色控制
	 	- 系统管理员 点击“用户管理” --> /system/userIndex.jsp
	 	- 非系统管理员 点击“用户管理”  --> /system/userEdit.jsp

	 -  权限控制：
		 - 普通用户（非系统管理员） 没有编辑、保存数据字典的权限
	 - 	使用了大量的 <s:if test="">标签。。。感觉 并不是一个特别好的方法  
	--> 引入 **自定义标签**
13.  ###（session级别的）粗颗粒权限控制：    
	服务器中的session，10分钟不操作，就会被自动清空（web.xml-->
```<session-config>
	<session-timeout>10</session-timeout>
</session-config>  
```
	）
	
	【所以要在**struts过滤器**中 先将操作拦截下来 判断一下此操作的Session是否存在】  
	 - 存在 放行
	 - 否则 重定向回首页（登陆等 本来就没有Session的操作除外）

14.  ###自定义**struts2拦截器**,进行异常处理+日志备份  
	拦截所有.do结尾查找Action类的URL
```
	try{
		//如果没有异常，不会执行catch
		String result =  参数.invoke();//指定Action的类，返回Action类的返回值
		return result;
	}
	catch(Exception){
		//如果出现异常，执行catch体
		//日志备份（log4j技术）
		//异常处理
		return "errorMsg";
	}
```

15.  ###细颗粒度权限控制  
	**拦截器 + 自定义注解**


##day07 

1. webservice框架：
    1. jdk    ws.invoke();
    2. CKF
    3. axis2
    4. xfire
2. hibernate 二级缓存
	1. 用户首页： 使用数据类型和数据项的编号，获取数据项的值
	2. 用户新增/编辑页面：使用数据类型，加载对应数据类型下的集合

3. 联合查询+标量查询  
4. 导出设置  
	