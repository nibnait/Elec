<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>运行监控模块编辑</title>
<link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/showText.css" type="text/css" rel="stylesheet">
<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
<script language="javascript" src="${pageContext.request.contextPath }/script/limitedTextarea.js"></script>
<script language="javascript" src="${pageContext.request.contextPath }/script/showText.js"></script>
<script language="javascript" src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
<script language="javascript" src="${pageContext.request.contextPath }/ckfinder/ckfinder.js"></script>

<LINK href="${pageContext.request.contextPath }/css/openView.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/script/highslide/highslide.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/highslide/highslide-html.js"></script>


  <script language="javascript"> 
  function checkchar(){
  		/**
	  	if(document.Form2.stationRun.value.length>2500){
	  
	     	alert("站点运行情况字数不能超过2500字");
	     	return;
	  	}
	  	if(document.Form2.devRun.value.length>2500){
	  
	     	alert("设备运行情况字数不能超过2500字");
	     	return;
	  	}
	  	*/
  		document.Form2.action="${pageContext.request.contextPath }/system/elecCommonMsgAction_save.do";
  		document.Form2.submit();
  		loading();
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
		//checkTextAreaLen();
  }
  
  /**添加不带百分比的进度条*/
  	var len = 500 ;
	var add = 0 ;
	function openContenFrame(){
	    var td1 = document.getElementById('tdOne') ;
	    var td2 = document.getElementById('tdTwo') ;
	    add = add+10 ;
	    td1.width = add ;
	    if(len - add <= 0){
	       td2.width = 1 ;
	    }else{
	       td2.width = len - add ;
	    }
	    if(add<=len) {
		   ;
	    }else{
	       td1.width = 1 ;
	       td2.width = 500 ;
	       add = 0 ;
	    }
	    setTimeout('openContenFrame()',100) ;
	}
	function loading(){
	    document.getElementById("load").style.display="";
	    document.getElementById("opperate1").style.display="none";
	    document.getElementById("opperate2").style.display="none";
	    openContenFrame();
	}

  
  </script>
  <script type="text/javascript">    
	    hs.graphicsDir = '${pageContext.request.contextPath }/script/highslide/graphics/';
	    hs.outlineType = 'rounded-white';
	    hs.outlineWhileAnimating = true;
  </script>
  

</head>

<body>
<form name="Form1" id="Form1" method="post">
    <table id="opperate1" cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
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
										<s:property value="stationRun"/>
									</div>
								</td>
								<td style="HEIGHT:22px" align="center" width="40%">
									<div>
										<a style="CURSOR:hand" href="${pageContext.request.contextPath }/system/elecCommonMsgAction_actingView.do?" onclick="return hs.htmlExpand(this, { contentId: 'highslide-html-1', objectType: 'ajax', preserveContent: true} )">
											<div class="scrollStyle" align="left" style="table-layout:fixed;">
												查看设备详细信息
											</div>
										</a>
										<div class="highslide-html-content" id="highslide-html-1" style="width: 700px">
										<div class="highslide-move" style="border: 0; height: 18px; padding: 2px; cursor: default">
											<a href="#" onClick="return hs.close(this)" class="control">[关 闭]</a>
										</div>
										<div class="highslide-body"></div>
										</div>
									</div>
									
								</td>
								<td style="HEIGHT:22px" align="center" width="20%">
									<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
							
						</table>
						
					</td>
				</tr>    
				
		</TBODY>
	</table>
</form>
<form name="Form2" id="Form2"  method="post">
	<table id="opperate2" cellspacing="1" cellpadding="5" width="90%" align="center" bgcolor="#f5fafe" style="border:1px solid #8ba7e3" border="0">

        <tr>
			<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
			<font face="宋体" size="2"><strong>运行监控编辑</strong></font>
			</td>
		</tr>
		<TR height=10><td></td><td></td></TR>
		
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">站点运行情况：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
				<s:textarea name="stationRun" id="stationRun" cssStyle="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('stationRun');"></s:textarea>
				<script type="text/javascript">
					CKEDITOR.replace("stationRun",{ height: 200, width: 820 });
				</script>
			</td>
			
		</tr>
		<tr>
			<td class="ta_01" align="center" bgcolor="#f5fafe" width="15%">设备运行情况：</td>
			<td class="ta_01" bgcolor="#ffffff" style="word-break: break-all">
				<s:textarea name="devRun" id="devRun"  cssStyle="width: 500px; height: 160px; padding: 1;FONT-FAMILY: 宋体; FONT-SIZE: 9pt" onkeydown="if(event.keyCode==13)addEnter('devRun');"></s:textarea>
				<script type="text/javascript">
					CKEDITOR.replace("devRun",{ height: 200, width: 820 });
				</script>
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
	<table id="load" width="700" border="0" align="center" bgcolor="#FAFAFA" cellpadding="0" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;display:none ">
	  <tr>
	    <td><br><br>
	    <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#287BCE" style="border-collapse:collapse ">
	        <tr bgcolor="#F7F7F6">
	          <td width="20%" height="100" valign="middle">
			    <table align='center' width='500'>
			      <tr>
			       <td colspan='2' align='center' id="progressPersent"><font size="2">
			        正在进行保存，用时较长，请稍后...
			        </font>
			       </td>
			      </tr>
			      <tr>
			        <td id='tdOne' height='25' width=1 bgcolor="blue">&nbsp;</td>
			        <td id='tdTwo' height='25' width=500 bgColor='#999999'>&nbsp;</td>
			      </tr>
			    </table>
	          </td>
	        </tr>
	    </table>
	    </td>
	  </tr>
	</table>
	
</form>
</body>
</html>