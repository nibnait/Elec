<%@ page language="java" pageEncoding="UTF-8"%>
<html>
 
<head>
<title>资料图纸管理</title>
 
<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
<script language="javascript"> 
 
  	
  
function getUrl(ssdw, filename){
	var strUrl = "";
	strUrl = "${pageContext.request.contextPath }/UploadFile/Paper/"+ssdw+"/" ;
	
	strUrl = strUrl + filename;	
	openWindow(strUrl,800,450);
}
 
function onloadForm2() {
	var str ='informationAndPaper.do?pageNO=1&pageSize=10';
	Pub.submitActionWithForm('Form2',str,'Form1');
}
function returnMethod(){
	if(event.keyCode==13) return false;
}
function returnConfirm(){
	return confirm("你确定要删除这篇资料文档吗？");
}
 
</script>
</head>
 
<body>
 
<form id="Form1" name="Form1" action="elecFileUploadAction_luceneHome.do" method="post" style="margin:0px;"> 
	<table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
		<tr>
			<td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
			<font face="宋体" size="2"><strong>资料图纸管理</strong></font>
			</td>
		</tr>
		<TR height=10><td></td></TR>
		<tr>
			<td>
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				
				<tr>
					<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					所属单位：</td>
					<td class="ta_01" >
						
						<select name="projId" id="projId" style="width:160px">
						    <option value="" selected="selected">全部</option>
						    <option value="1">北京</option>
						    <option value="2">上海</option>
						    <option value="3">深圳</option>
						</select>
 
					</td>
					<td width="100" class="ta_01" align="center" bgcolor="#f5fafe" height="22">
					图纸类别：</td>
					<td class="ta_01" >
						 <font face="宋体" color="red"> 
					  	
						<select name="belongTo" id="belongTo" style="width:160px">
						    <option value="" selected="selected">全部</option>
						    <option value="1">国内图书</option>
						    <option value="2">国外图书</option>
						</select>
 
					 </font>
					</td>
		     </tr>
		     <tr>
		        <td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
		           按文件名称和描述搜素：</td>
		          <td class="ta_01">
		          	<input type="text" name="queryString" size="21" value="电池" id="queryString" onkeydown="returnMethod()"/>
		          </td>
		          <td width="100" colspan="2" class="ta_01" align="center" bgcolor="#f5fafe" height="22">
		            &nbsp;
		          </td>
		     </tr>
		    </table>
		   </td>
		</tr>
    </table>	
</form>
 
 
 
 
 
<br>
<form id="Form2" name="Form2" action="${pageContext.request.contextPath }/datachart/elecFileUploadAction_luceneHome.do" method="post" style="margin:0px;">
  <table cellspacing="0" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
	<TR>
		<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif" width=25><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
		<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif" width=100>资料图纸列表</TD>
		<td class="ta_01" align="right" bgcolor="#ffffff">
			注意：由于资源数据比较多，请指定条件查询结果-------------
			<input type="button" name="BT_Search" value="查询" style="font-size:12px; color:black; height=20;width=50" id="BT_Search" onclick="document.Form1.submit();">
			
			<input  id="BT_Add" type="button" value="添加" name="BT_Add" style="font-size:12px; color:black; height=20;width=50"   onclick="openWindow('${pageContext.request.contextPath }/dataChart/dataChartAdd.jsp',800,400);">
				
		</td>
	</TR>
  </TABLE>		
 <table cellspacing="1" cellpadding="0" width="90%" align="center" bgcolor="#f5fafe" border="0">
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe">
			<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1" style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
				<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
					<td align="center" width="6%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">序号</td>
					<td align="center" width="10%"  height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">所属单位</td>
					<td align="center" width="10%"  height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">图纸类别</td>
					<td align="center" width="48%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">图纸名称</td>
					<td align="center" width="10%"  height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">描述</td>
					<td width="6%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">删除</td>
						
				</tr>
				
					
						<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
							<td  align="center" width="6%">1</td>
							<td align="center"  width="10%">北京</td>
							<td align="center"  width="10%">国内图书</td>
							<td align="center"  width="48%">
							<a href="#" onclick="openWindow('${pageContext.request.contextPath }/datachart/elecFileUploadAction_download.do?seqId=2','700','400');"><font color='red'>电池</font>电阻使用帮助.txt</a></td>
							<td align="center"  width="20%">为了方便客户熟悉电网设备的电压，<font color='red'>电池</font>，<font color='red'>电池</font>组等</td>
							<td align="center" style="HEIGHT: 21px">
								<a href="elecFileUploadAction_delete.do?seqId=2" onclick="return returnConfirm()">
								<img src="${pageContext.request.contextPath }/images/delete.gif" width="16" height="16" border="0" style="CURSOR:hand">
								</a>												
							</td>
								
						</tr>
				
			</table>
			</td>
		</tr>
	
 </table>
</form>
 
 
 
 
</body>
</html>


