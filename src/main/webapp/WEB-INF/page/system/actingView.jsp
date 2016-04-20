<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>显示运行监控记录</title>
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<table cellspacing="1" cellpadding="0" width="100%" align="center" bgcolor="#f5fafe" border="0">
		<TR height="10">
			<td class="ta_01">
				<s:property value="devRun"/>
			</td>
		</TR>
    </table>
    
</body>
</html>
