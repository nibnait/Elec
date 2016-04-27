<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<title>上传文件</title>
		<LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
		<script language="javascript" src="${pageContext.request.contextPath }/script/public.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src="${pageContext.request.contextPath }/script/validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
<script language="javascript">
 	 /***
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
		*/
		/**jquery对象*/
		function init(){
			$("#BT_Submit").attr("disabled",true);
		   //获取所属单位和图纸类别的条件
		   var projId = $("#projId").val();
		   var belongTo = $("#belongTo").val();
		   //如果没有指定条件，不按照条件查询
		   if(projId!=0 || belongTo!=0){
			   changeList();
		   }
		 }
		function insertRows(){ 
			$("#BT_Submit").attr("disabled",false);
			//获取表格对象
	    	var tb1 = $("#filesTbl");
	    	var tempRow = $("#filesTbl tr").size();//获取表格的行数,+1的目的去掉添加选项的按钮
	    	var $tdNum = $("<td align='center'></td>");
	    	$tdNum.html(tempRow);
	    	
	    	var $tdName = $("<td align='center'></td>");
	    	$tdName.html("<input name=\"uploads\"  type=\"file\" size=\"25\" id=\""+tempRow+"\">");
	    	
	    	var $tdComment = $("<td align='center'></td>");
	    	$tdComment.html("<input name=\"comments\"  type=\"text\" size=\"40\">");
	    	
	    	var $tdDel = $("<td align='center'></td>");
	    	$tdDel.html("<a href='javascript:delTableRow(\""+tempRow+"\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>");
	    	
	    	
	    	// 创建tr，将3个td放置到tr中
	    	var $tr = $("<tr></tr>");
	    	$tr.append($tdNum);
	    	$tr.append($tdName);
	    	$tr.append($tdComment);
	    	$tr.append($tdDel);
	    	//在表格的最后追加新增的tr
	    	tb1.append($tr);
	    	
		 } 
 
 
		function delTableRow(rowNum){ 
		   //改变行号和删除的行号
	       var tb1 = $("#filesTbl");
	       var tempRow = $("#filesTbl tr").size();//获取表格的行数
	       if (tempRow >rowNum){     
	    	  //获取删除行的id指定的对象，例如：<input name=\"itemname\" type=\"text\" id=\""+tempRow+"\" size=\"45\" maxlength=25>
	    	  $("#"+rowNum).parent().parent().remove();
	    	  //加1表示寻找下一个id，目的是将后面tr的格式向上移动
	          for (i=(parseInt(rowNum)+1);i<tempRow;i++){
	        	  //将i-1的值赋值给编号
	        	  $("#"+i).parent().prev().html(i-1);
	        	  //将i-1的值赋值给超链接的删除
	        	  $("#"+i).parent().next().next().html("<a href='javascript:delTableRow(\""+(i-1)+"\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>");//
	        	  //将i-1的值赋值给文本框的id，用于删除
	        	  $("#"+i).attr("id",(i-1));//将id设置成i-1
	          }
	       }
	       var tempRow1 = $("#filesTbl tr").size();//获取表格的行数
	       if(tempRow1 <=1)
		   {
	    	   $("#BT_Submit").attr("disabled",true);
		   }
		} 
 
		function addFileList(){
			var $tbl=$("#filesTbl tr");
		       var flag = false;
		 	   $tbl.each(function(index,domEle){
		 		   //第一行
		 		   if(index==0){
		 			   return true;//继续循环，类似于循环continue
		 		   }
		 		   //从1开始是为了去掉表头
		 		   else{
		 			   var $uploads = $(this).find("td:nth-child(2)").find("input[name='uploads']").val();
		 			   if($.trim($uploads)==""){
		 				  alert("请选择第"+ index +"行的文件路径！");
		 				  flag = true;
		 				  return false;//退出循环，类似于循环break
		 			   }
		 		   }
		 	   })
		 	   //说明附件存在错误
		 	   if(flag){
		 		   return false;
		 	   }
		   	   if ( check() && overWriteFile("filesTbl","filesTb2") )
		   	   {		   		
						$("#Form1").attr("action","elecFileUploadAction_save.do");
				     	$("#Form1").submit();
				
			   }
			   else{
			  		return false ;
			   }
                 
		}
		function check() {
		  var projId = $("#projId").val();
		  if(projId == 0)
		  {
		      alert("请选择所属单位！");
		      $("#projId")[0].focus();
		      return false;
		  }
		  var belongTo = $("#belongTo").val();
		  if(belongTo == 0)
		  {
		    alert("请选择图纸分类！");
		    $("#belongTo")[0].focus();
		    return false;
		  }
		  return true;
		}
		function overWriteFile(tabName1,tabName2)
		{
			var $tabName1 = $("#"+tabName1);
			var $tabName2 = $("#"+tabName2);		
			var fileName1,fileName2 ;
			var flag = false;
			$tabName1.find("tr:gt(0)").each(function(index,domEle){
	 			   fileName1 = $(this).find("td:nth-child(2)").find("input[name='uploads']").val();
	 			   fileName1 = fileName1.substr(fileName1.lastIndexOf("\\")+1 ) ;		//文件名
	 			   if (fileName1.lastIndexOf("'")!=-1)
				   {
							alert("上传文件名带有'错误字符'") ;
							flag = true;
							return false ;
				   }
	 			   /**当前的tr与之后的所有tr进行比对，判断名称是否有冲突*/
	 			  $("#"+tabName1+" tr:gt("+(index+1)+")").each(function(index1,domEle1){
	 				   fileName2 = $(domEle1).find("td:nth-child(2)").find("input[name='uploads']").val();
					   fileName2 = fileName2.substr(fileName2.lastIndexOf("\\")+1 ) ;
					   if ($.trim(fileName1) == $.trim(fileName2))
					   {
							alert("添加上传文件列表中的第"+(index1+index+2)+"行与第"+(index+1)+"行,存在与\""+fileName1+"\"的重名文件") ;
							flag = true;
							return false ;
					   }
	 			  })
			});
			//2.检查"添加上传文件列表"与"已上传文件列表"中是否有重名文件	
			$tabName1.find("tr:gt(0)").each(function(index,domEle){
				fileName1 = $(this).find("td:nth-child(2)").find("input[name='uploads']").val();
	 			fileName1 = fileName1.substr(fileName1.lastIndexOf("\\")+1 ) ;		//文件名
	 			//大于1的表格的值
	 			$tabName2.find("tr:gt(0)").each(function(index1,domEle1){
	 				fileName2 = $(domEle1).find("td:nth-child(2)").find("a").html();
	 				if ($.trim(fileName1) == $.trim(fileName2))	//存在重名文件
					{
						alert("待上传的文件\""+fileName1+"\"已经存在，不允许上传，或者修改文件名，或者通知管理员删除同名文件后再上传");
						flag = true;
						return false;//退出循环，相当于break
					}
					else
						return true;//继续循环，相当于continue
	 			})
			})
			if(flag){
		 		 return false;
		 	}
			return true ;
		}
		
		function changeList() {
			var projId = document.getElementById("projId").value;
			var belongTo = document.getElementById("belongTo").value;
			var str = 'elecFileUploadAction_addList.do?projId='+projId+'&belongTo='+belongTo;
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
										
										<s:select list="#request.jctList" name="projId" id="projId"
												  listKey="ddlCode" listValue="ddlName"
												  headerKey="0" headerValue="全部"
												  cssStyle="width:160px" onchange="changeList()">
										</s:select>
 
									</td>
									<td width="100" class="ta_01" align="center" bgcolor="#f5fafe"
										height="22">
										图纸类别：
									</td>
									<td class="ta_01">
										<font face="宋体" color="red"> 
											
											<s:select list="#request.picList" name="belongTo" id="belongTo"
													  listKey="ddlCode" listValue="ddlName"
													  headerKey="0" headerValue="全部"
													  cssStyle="width:160px" onchange="changeList()">
											</s:select>
 
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

 

