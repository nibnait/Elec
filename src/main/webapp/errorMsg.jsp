<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<HTML><HEAD><TITLE>国家电力监测中心设备资源管理系统</TITLE>
<LINK href="${pageContext.request.contextPath }/css/MainPage.css" type="text/css" rel="stylesheet">
<LINK href="${pageContext.request.contextPath }/css/buttonstyle.css" type="text/css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="../css/buttonstyle.css">


</HEAD>
<BODY bgcolor="#FFFFFF" onload = "showTimer()" background="${pageContext.request.contextPath }/images/back1.jpg"> 
<Form name="form1" method="POST">
<table border="0" width="100%" id="table1" height="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center">
		<table border="0" width="60%" id="table2" cellspacing="0" cellpadding="0">
			<tr>
				<td width="49" height="45"><img border="0" src="${pageContext.request.contextPath }/images/build.gif" width="185" height="180"></td>
				<td style="word-break:break-all" align="center">
					<font face="黑体" size="2" color="red">
						<b>
						<s:if test="#request.errorMsg==null">
							服务器忙，请联系管理员！
						</s:if>
						<s:else>
							<s:property value="#request.errorMsg"/>
						</s:else>
						</b>
                	</font>
                </td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</Form>
</BODY>
