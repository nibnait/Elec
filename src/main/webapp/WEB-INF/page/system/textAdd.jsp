

<%@ page language="java" pageEncoding="UTF-8"%>
   <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<html>
<head>
<title>测试专用jsp</title>
<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">

  <script language="javascript"> 
   function checkchar(){
  		document.Form1.action="${pageContext.request.contextPath }/system/elecTextAction_save.do";
  		document.Form1.submit();
  }
  function addEnter(element){
   document.getElementById(element).value = document.getElementById(element).value+"<br>";
   
  }
  </script>


</head>

<body>
<form name="Form1" id="Form1" method=post>

	<table cellspacing="1" cellpadding="5" width="90%" align="center" bgcolor="#f5fafe" style="border:1px solid #8ba7e3" border="0">

        <tr>
			<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
			<font face="宋体" size="2"><strong>测试专用jsp</strong></font>
			</td>
		</tr>
		<TR height=10><td></td><td></td></TR>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">测试名称：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
	
			<textarea name="textName" id="textName"   style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('textName');"></textarea>
			</td>
			
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">测试日期：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
	
			<input name="textDate" type="text" maxlength="50" size=20 onclick="WdatePicker()">
			</td>
			
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">测试备注：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
			<textarea name="textRemark" id="textRemark"  style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('textRemark');"></textarea>
			</td>
			
		</tr>
        <tr>
			<td class="ta_01" style="width: 100%" align="center" bgcolor="#f5fafe" colspan="2">
			<input type="button" name="BT_Submit" value="保存" onclick="checkchar()" id="BT_Submit" style="font-size:12px; color:black; height=20;width=50">
			</td>
		</tr>
	</table>
	　 
</form>

</body>
</html>