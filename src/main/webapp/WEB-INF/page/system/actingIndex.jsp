

<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<title>运行监控模块编辑</title>
<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/showText.css" type="text/css" rel="stylesheet">
<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
<script language="javascript" src="${pageContext.request.contextPath }/script/limitedTextarea.js"></script>
<script language="javascript" src="${pageContext.request.contextPath }/script/showText.js"></script>
  <script language="javascript"> 
  function checkchar(){
  
	  	if(document.Form2.stationRun.value.length>2500){
	  
	     	alert("站点运行情况字数不能超过2500字");
	     	return;
	  	}
	  	if(document.Form2.devRun.value.length>2500){
	  
	     	alert("设备运行情况字数不能超过2500字");
	     	return;
	  	}
  		document.Form2.action="savePending.do";
  		document.Form2.submit();
  		alert(" 待办事宜保存成功!");
  }
  function addEnter(element){
   		document.getElementById(element).value = document.getElementById(element).value+"<br>";
   
  }
  function checkTextAreaLen(){
  		var stationRun = new Bs_LimitedTextarea('stationRun', 2500); 
        stationRun.infolineCssStyle = "font-family:arial; font-size:11px; color:gray;";
        stationRun.draw();	
  	
        var devRun = new Bs_LimitedTextarea('devRun', 2500); 
        devRun.infolineCssStyle = "font-family:arial; font-size:11px; color:gray;";
        devRun.draw();	
  }
  window.onload=function(){
		checkTextAreaLen();
  }
  
  </script>


</head>

<body>
<form name="Form1" id="Form1" method="post">
    <table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
		<TBODY>
			<TR height=10><td></td></TR>			
			<tr>
			  	<td>
	                <TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
									<TR>
										<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
										<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">运行监控列表</TD>
									</TR>
		             </TABLE>
                  </td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>			
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							
								<td align="center" width="40%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">站点运行情况</td>
								<td align="center" width="40%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">设备运行情况</td>
								<td align="center" width="20%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">创建日期</td>
							</tr>
							<div id="showInfomation" style="visibility: hidden"></div>
							<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="HEIGHT:22px" align="center" width="40%">
									<div class="scrollStyle" align="left" onmouseover="showInfoWithPanel(this)" onmouseout="hiddenInfoPanel(this)" style="table-layout:fixed;">
										9点站点运行正常
									</div>
								</td>
								<td style="HEIGHT:22px" align="center" width="40%">
									<div class="scrollStyle" align="left" onmouseover="showInfoWithPanel(this)" onmouseout="hiddenInfoPanel(this)" style="table-layout:fixed;">
										9点设备运行正常xcvcxvxcv设备运行正常xcvcxvxcv设备运行正常xcvcxvxcv设备运行正常xcvcxvxcv设备运行正常xcvcxvxcv设备运行正常xcvcxvxcv设备运行正常xcvcxvxcv设备运行正常xcvcxvxcv设备运行正常xcvcxvxcv设备运行正常xcvcxvxcv
									</div>
								</td>
								<td style="HEIGHT:22px" align="center" width="20%">
									2010-08-08 12:55:55 
								</td>
							</tr>
							
						</table>
						
					</td>
				</tr>    
				
		</TBODY>
	</table>
</form>
<form name="Form2" id="Form2"  method="post">
	<table cellspacing="1" cellpadding="5" width="90%" align="center" bgcolor="#f5fafe" style="border:1px solid #8ba7e3" border="0">

        <tr>
			<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
			<font face="宋体" size="2"><strong>运行监控编辑</strong></font>
			</td>
		</tr>
		<TR height=10><td></td><td></td></TR>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">站点运行情况：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
	
				<textarea name="stationRun" id="stationRun"   style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('stationRun');">9点站点运行正常</textarea>
			</td>
			
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">设备运行情况：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
				<textarea name="devRun" id="devRun"  style="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('devRun');">9点设备运行正常</textarea>
			</td>
			
		</tr>
        <tr>
			<td class="ta_01" style="width: 100%" align="center" bgcolor="#f5fafe" colspan="2">
			<input type="button" name="BT_Submit" value="保存" onclick="checkchar()" id="BT_Submit" style="font-size:12px; color:black; height=20;width=50">&nbsp;&nbsp;
				<input style="font-size:12px; color:black; height=20;width=80" id="BT_Export" type="button" value="导出设置" name="BT_Export" 
						 onclick="openWindow('${pageContext.request.contextPath }/system/exportExcel.jsp?belongTo=5-3','700','400')">&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</form>
</body>
</html>