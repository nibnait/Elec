<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<title>上传文件</title>
		<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script language="javascript" src="${pageContext.request.contextPath }/script/public.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src="${pageContext.request.contextPath }/script/validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
<script language="javascript">
	 function init(){
	   document.getElementById("BT_Submit").disabled=true;
	   //获取所属单位和图纸类别的条件
	   var projId = document.getElementById("projId").value;
	   var belongTo = document.getElementById("belongTo").value;
	   //如果没有指定条件，不按照条件查询
	   if(projId!=0 || belongTo!=0){
		   changeList();
	   }
	 }
		function insertRows(){ 
          document.getElementById("BT_Submit").disabled=false;
		  var tempRow=0; 
		  var tbl=document.getElementById("filesTbl");
		  tempRow=tbl.rows.length; 
		  var Rows=tbl.rows;//类似数组的Rows 
		  var newRow=tbl.insertRow(tbl.rows.length);//插入新的一行 
		  var Cells=newRow.cells;//类似数组的Cells 
		  for (i=0;i<4;i++)//每行的3列数据 
		  { 
			 var newCell=Rows[newRow.rowIndex].insertCell(Cells.length); 
			 newCell.align="center"; 
			 switch (i) 
			{ 
			  case 0 : newCell.innerHTML=""+tempRow+"";break; 
			  case 1 : newCell.innerHTML="<input name=\"uploads\"  type=\"file\" size=\"25\">";break; 
			  case 2 : newCell.innerHTML="<input name=\"comments\"  type=\"text\" size=\"40\">";break; 
			  case 3 : newCell.innerHTML="<a href='javascript:delTableRow(\""+tempRow+"\")'><img src=\"${pageContext.request.contextPath }/images/delete.gif\" width=15 height=14 border=0 style=CURSOR:hand></a>";break;
			} 
		  } 
		 } 
 
 
		function delTableRow(rowNum){ 
 
		   var tbl=document.getElementById("filesTbl");
			
			if (tbl.rows.length >rowNum){ 
			  
			   tbl.deleteRow(rowNum); 
			 
			  for (i=rowNum;i<tbl.rows.length;i++)
			   {
				 tbl.rows[i].cells[0].innerHTML=i;
				 tbl.rows[i].cells[3].innerHTML="<a href='javascript:delTableRow(\""+i+"\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>";      
			  }
		   }
		   if(tbl.rows.length <=1)
		   {
		     document.getElementById("BT_Submit").disabled=true;
		   }
		} 
 
		function addFileList(){
			
			var tbl=document.getElementById("filesTbl");
		      for (i=1;i<tbl.rows.length;i++){
		   	  	var filePath = tbl.rows[i].cells[1].getElementsByTagName("input")[0].value;
		   	  	if(filePath == ""){
		   	  		 alert("请选择第"+ i +"行的文件路径！");
		   	  		 return false;
		   	  	}
		   	  }
		   	  if ( check() && overWriteFile("filesTbl","filesTb2") )
		   	  {		   	  
						document.Form1.action = "elecFileUploadAction_save.do";			
				     	document.Form1.submit(); 
				        //window.opener.onloadForm2();
				        //window.close();
				
			  }
			  else
			  		return false ;
		   	 
                 
		}
		function check() {
		 if(document.getElementById("projId").value == 0)
		  {
		      alert("请选择所属单位！");
		      document.getElementById("projId").focus();
		      return false;
		  }
		  if(document.getElementById("belongTo").value == 0)
		  {
		    alert("请选择图纸分类！");
		    document.getElementById("belongTo").focus();
		    return false;
		  } 
		  return true;
		}
		function overWriteFile(tabName1,tabName2)
		{
			var tbl=document.getElementById(tabName1);
			var tb2=document.getElementById(tabName2);			
			var fileName1,fileName2 ;
			
		
			//1.检查"添加上传文件列表"中的文件是否有重名
 
 
			for (i=1;i<tbl.rows.length;i++)		
			{
				fileName1 = tbl.rows[i].cells[1].getElementsByTagName("input")[0].value;;	//准备上传的完整文件路径
				fileName1 = fileName1.substr(fileName1.lastIndexOf("\\")+1 ) ;		//文件名
				if (fileName1.lastIndexOf("'")!=-1)
				{
						alert("上传文件名带有'错误字符'") ;
						return false ;
				}
				for (j=i+1;j<tbl.rows.length;j++)
				{
					fileName2 = tbl.rows[j].cells[1].getElementsByTagName("input")[0].value;	
					fileName2 = fileName2.substr(fileName2.lastIndexOf("\\")+1 ) ;
					
					if (Trim(fileName1) == Trim(fileName2))
					{
						alert("添加上传文件列表中存在与\""+fileName1+"\"的重名文件") ;
						return false ;
					}
				}
			}
			
			//2.检查"添加上传文件列表"与"已上传文件列表"中是否有重名文件			
			for (i=1;i<tbl.rows.length;i++)
			{				
				fileName1 = tbl.rows[i].cells[1].getElementsByTagName("input")[0].value;	//准备上传的完整文件路径
				fileName1 = fileName1.substr(fileName1.lastIndexOf("\\")+1 ) ;		//文件名
				for (j=1;j<tb2.rows.length;j++)
				{
					fileName2= tb2.rows[j].cells[1].getElementsByTagName("a")[0].innerHTML;	//已经上传的文件名
					if (Trim(fileName1) == Trim(fileName2))	//存在重名文件
					{
						alert("待上传的文件\""+fileName1+"\"已经存在，不允许上传，或者修改文件名，或者通知管理员删除同名文件后再上传");
						return false ;
					}
					else
						continue ;
				}
			}
			
			return true ;
		}
		
		function getUrl(projId, filename){
			var strUrl = "";
			strUrl = "${pageContext.request.contextPath }/UploadFile/Paper/"+projId+"/" ;
			strUrl = strUrl + filename;	
			OpenWindow("new",strUrl,800,450);
		}
		
		function changeList() {
			var projId = document.getElementById("projId").value;
			var belongTo = document.getElementById("belongTo").value;
			var str = 'dataChartAddList.jsp?projId='+projId+'&belongTo='+belongTo;
			Pub.submitActionWithFormGet('Form2',str,'Form1');
		}	
</script>
	</HEAD>
	<body onload="init();">
		<br>
		    <form id="Form1" name="Form1" action="${pageContext.request.contextPath }/datachart/elecFileUploadAction_add.do" method="post" enctype="multipart/form-data">
			<table cellSpacing="0" cellPadding="0" width="700" align="center"
				bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" background="${pageContext.request.contextPath }/images/b-info.gif"
							colspan=3>
							<font face="宋体" size="2"><strong>文件上传管理</strong> </font>
						</td>
					</tr>
					
					<tr height="50">
						<td colspan="3">
							
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="ta_01" align="center" bgcolor="#f5fafe" height="22">
										所属单位：
									</td>
									<td class="ta_01">
										
										<select name="projId" id="projId" style="width:160px" onchange="changeList();">
										    <option value="">全部</option>
										    <option value="1">北京</option>
										    <option value="2">上海</option>
										    <option value="3">深圳</option>
										</select>
 
									</td>
									<td width="100" class="ta_01" align="center" bgcolor="#f5fafe"
										height="22">
										图纸类别：
									</td>
									<td class="ta_01">
										<font face="宋体" color="red"> 
											
											<select name="belongTo" id="belongTo" style="width:160px" onchange="changeList();">
											    <option value="">全部</option>
											    <option value="1">国内图书</option>
											    <option value="2">国外图书</option>
											</select>
 
											</font>
 
									</td>
 
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						
							<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif">
								<img src="${pageContext.request.contextPath }/images/yin.gif" width="15">
							</TD>
							<TD class="DropShadow" align="left"
								background="${pageContext.request.contextPath }/images/cotNavGround.gif" width=100>
								添加上传文件列表
							</TD>
						
						
						<TD width="80%" align="right">
							
								<input type="button" name="BT_Add" value="添加" id="BT_Add"
									onclick="insertRows();"
									style="font-size:12px; color:black; height=20">
							
							
								<input type="button" name="BT_Submit" value="上传" id="BT_Submit"
									onclick="addFileList();"
									style="font-size:12px; color:black; height=20">
							
							<input type="button" value="关闭" onClick="window.close();"
								style="font-size:12px; color:black; height=20">
						</TD>
					</tr>
					
						<tr>
							<td class="ta_01" align="center" bgColor="#f5fafe" colspan=3>
								<table cellspacing="0" cellpadding="1" rules="all"
									bordercolor="gray" border="1" id="filesTbl"
									style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
									<tr
										style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
										<td class="ta_01" align="center" width="10%"
											background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
											编号
										</td>
										<td class="ta_01" align="center" width="40%"
											background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
											选择待上传文件
										</td>
										<td class="ta_01" align="center" width="40%"
											background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
											文件描述
										</td>
										<td class="ta_01" align="center" width="10%"
											background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
											删除
										</td>
									</tr>
								</table>
							</td>
						</tr>
					
					
					<tr height=10>
						<td colspan=3 bgcolor="#ffffff"></td>
					</tr>
 
					<tr>
						<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif">
							<img src="${pageContext.request.contextPath }/images/yin.gif" width="15">
						</TD>
						<TD class="DropShadow" align="left"
							background="${pageContext.request.contextPath }/images/cotNavGround.gif" width=100>
							已上传文件列表
						</TD>
						<TD width="80%"></TD>
					</tr>
				</TBODY>
			</table>
			</form>
 
 
 
 
			
			<form id="Form2" name="Form2" action="${pageContext.request.contextPath }/datachart/elecFileUploadAction_add.do" method="post" style="margin:0px;">
				<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray"
					border="1" id="filesTb2" align="center"
					style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:700; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
					<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
						<td class="ta_01" align="center" width="20%"
							background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
							编号
						</td>
						<td class="ta_01" align="center" width="80%"
							background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
							已上传文件
						</td>
					</tr>
				</table>
			</form>
 
 
 
 
	</body>
</HTML>

 

