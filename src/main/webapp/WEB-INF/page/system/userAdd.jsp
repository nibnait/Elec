
<%@ page language="java" pageEncoding="UTF-8"%>


<html>
  <head>
   <title>添加用户</title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
   <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/showText.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/limitedTextarea.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
   
<Script language="javascript">


	function check_null(){
	    
	     var theForm=document.Form1;
	    
	    if(Trim(theForm.logonName.value)=="")
		{
			alert("登录名不能为空");
			theForm.logonName.focus();
			return false;
		}
	    if(Trim(theForm.userName.value)=="")
		{
			alert("用户姓名不能为空");
			theForm.userName.focus();
			return false;
		}
		if(theForm.sexID.value=="")
		{
			alert("请选择性别");
			theForm.sexID.focus();
			return false;
		}
	    if(theForm.jctID.value=="")
		{
			alert("请选择所属单位");
			theForm.jctID.focus();
			return false;
		}
	    if(Trim(theForm.onDutyDate.value)=="")
		{
			alert("入职时间不能为空");
			theForm.onDutyDate.focus();
			return false;
		}
	    if(theForm.postID.value=="")
		{
			alert("请选择职位");
			theForm.postID.focus();
			return false;
		}
        if(theForm.logonPwd.value!=theForm.passwordconfirm.value){
		
		  alert("两次输入密码不一致，请重新输入");
		  return;
		}
		if(checkNull(theForm.contactTel)){
         if(!checkPhone(theForm.contactTel.value))
		  {
			alert("请输入正确的电话号码");
			theForm.contactTel.focus();
			return false;
		  }
		}
		
	    if(checkNull(theForm.mobile)){
         if(!checkMobilPhone(theForm.mobile.value))
		  {
			alert("请输入正确的手机号码");
			theForm.mobile.focus();
			return false;
		  }
		}
		
	   if(checkNull(theForm.email))	{
         if(!checkEmail(theForm.email.value))
		 {
			alert("请输入正确的EMail");
			theForm.email.focus();
			return false;
		 }
	   }
		
	   if(theForm.remark.value.length>250){
     
        	alert("备注字符长度不能超过250");
			theForm.remark.focus();
			return false; 
       }
	   var isDutySelect = document.getElementById("isDuty");
	   //选择[是]
	   if(isDutySelect.options[0].selected){
		   if(theForm.onDutyDate.value==""){
			   alert("该用户属于在职人员，请填写入职时间");
			   theForm.onDutyDate.focus();
			   return false; 
		   }
	   }
	   //选择[否]
	   if(isDutySelect.options[1].selected){
		   alert("不允许新增用户操作，选择离职！");
		   theForm.isDuty.focus();
		   return false;
	   }
	   //上传的文件不能为空
	   var tbl=document.getElementById("filesTbl");
       for (i=1;i<tbl.rows.length;i++){
   	  	   var filePath = tbl.rows[i].cells[1].getElementsByTagName("input")[0].value;
   	  	   if(filePath == ""){
   	  		   alert("请选择第"+ i +"行的文件路径！");
   	  		   return false;
   	  	   }
   	   }
	   document.Form1.action="saveUser.do";
	   document.Form1.submit();
	}
	function checkTextAreaLen(){
  		var remark = new Bs_LimitedTextarea('remark', 250); 
  		remark.infolineCssStyle = "font-family:arial; font-size:11px; color:gray;";
  		remark.draw();	
    }
    window.onload=function(){
		checkTextAreaLen();
    }
    //ajax的二级联动，使用选择的所属单位，查询该所属单位下对应的单位名称列表
    function findJctUnit(o){
    	//货物所属单位的文本内容
    	var jct = $(o).find("option:selected").text();
    	$.post("elecUserAction_findJctUnit.do",{"jctID":jct},function(data,textStatus){
	   	    //先删除单位名称的下拉菜单，但是请选择要留下
	   	    $("#jctUnitID option").remove();
	        if(data!=null && data.length>0){
	            for(var i=0;i<data.length;i++){
	   		       	var ddlCode = data[i].ddlCode;
	   		       	var ddlName = data[i].ddlName;
	   		       	//添加到单位名称的下拉菜单中
	   		       	var $option = $("<option></option>");
	   		       	$option.attr("value",ddlCode);
	   		       	$option.text(ddlName);
	   		       	$("#jctUnitID").append($option);
	   	        }
	        }
        });
    	
    }
    
    /**校验登录名是否出现重复*/
    function checkUser(o){
    	//alert(o.value);//dom的写法
    	//alert($(o).val());//jquery的写法
    	var logonName = $(o).val();
    	//以登录名作为查询条件，查询该登录名是否在数据库表中存在记录
    	$.post("elecUserAction_checkUser.do",{"logonName":logonName},function(data){
    		//如果栈顶是模型驱动的对象，取值的时候应该使用data.message的方式
    		//如果栈顶是模型驱动的对象的某个属性，取值的时候应该使用data即可
    		if(data==1){
				$("#check").html("<font color='red'>登录名不能为空</font>");
				$(o)[0].focus();
				$("#BT_Submit").attr("disabled","none");
			}
			else if(data==2){
				$("#check").html("<font color='red'>登录名已经存在</font>");
				$(o)[0].focus();
				$("#BT_Submit").attr("disabled","none");
			}
			else{
				$("#check").html("<font color='green'>登录名可以使用</font>");
				$("#BT_Submit").attr("disabled","");
			}
    	});
    }
    function fileTr(){
    	var value = $("#BT_File").val();
		if(value == "打开附件"){
			$("#trFile").css("display","");
			$("#BT_File").val("隐藏附件");
			$("#item").css("display","");
		}
		else{
			$("#trFile").css("display","none");
			$("#BT_File").val("打开附件");
			$("#item").css("display","none");
		}
    }
    function insertRows(){ 
    	//获取表格对象
    	var tb1 = $("#filesTbl");
    	var tempRow = $("#filesTbl tr").size();//获取表格的行数,+1的目的去掉添加选项的按钮
    	var $tdNum = $("<td align='center'></td>");
    	$tdNum.html(tempRow);
    	
    	var $tdName = $("<td align='center'></td>");
    	$tdName.html("<input name=\"uploads\"  type=\"file\" size=\"25\" id=\""+tempRow+"\">");
    	
    	var $tdDel = $("<td align='center'></td>");
    	$tdDel.html("<a href='javascript:delTableRow(\""+tempRow+"\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>");
    	
    	
    	// 创建tr，将3个td放置到tr中
    	var $tr = $("<tr></tr>");
    	$tr.append($tdNum);
    	$tr.append($tdName);
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
        	  $("#"+i).parent().next().html("<a href='javascript:delTableRow(\""+(i-1)+"\")'><img src=${pageContext.request.contextPath }/images/delete.gif width=15 height=14 border=0 style=CURSOR:hand></a>");//
        	  //将i-1的值赋值给文本框的id，用于删除
        	  $("#"+i).attr("id",(i-1));//将id设置成i-1
          }
       }
    } 
   </script>
  </head>
  
 <body>
 
  <form name="Form1" method="post" enctype="multipart/form-data">
 <br>
    <table cellSpacing="1" cellPadding="5" width="680" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

    <tr>
		<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
		 <font face="宋体" size="2"><strong>添加用户</strong></font>
		</td>
    </tr>
     <tr>
         <td align="center" bgColor="#f5fafe" class="ta_01">登&nbsp;&nbsp;录&nbsp;&nbsp;名：<font color="#FF0000">*</font></td>
         <td class="ta_01" bgColor="#ffffff">
         	<input name="logonName" type="text" maxlength="25" id="logonName" size="20" onblur="checkUser(this);">
         	<div id="check"></div>
         </td>
         <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户姓名：<font color="#FF0000">*</font></td>
         <td class="ta_01" bgColor="#ffffff">
         	<input name="userName" type="text" maxlength="25" id="userName" size="20"> 
         </td>
    </tr>
	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">性别：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<select name="sexID" id="sexID" style="width:155px">
			<option value=""></option>
			<option value="1">男</option>
			<option value="2">女</option>
			</select>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">职位：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<select name="postID" id="postID" style="width:155px">
	    		<option value=""></option>
			    <option value="1">总经理</option>
			    <option value="2">部门经理</option>
			    <option value="3">员工</option>
			</select>
		</td>
	</tr>
	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">所属单位：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<select name="jctID" id="jctID" style="width:155px">
			<option value=""></option>
			
			<option value="1">北京</option>	
		
			<option value="2">上海</option>	
			
			<option value="3">深圳</option>	
			
			<option value="4">厦门</option>	
			
			<option value="5">成都</option>	
			
			<option value="6">海尔滨</option>	
			
			<option value="7">长春</option>	
			
			<option value="8">沈阳</option>	
			
			<option value="9">广州</option>	
			
			<option value="10">西安</option>	
			
			<option value="11">南宁</option>	
			
			<option value="12">天津</option>	
			
			<option value="13">海南</option>	
			
			</select>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">单位名称：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<select id="jctUnitID" name="jctUnitID" style="width:155px"></select>
		</td>
	</tr>
	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">密码：</td>
		<td class="ta_01" bgColor="#ffffff">
			<input name="logonPwd" id="logonPwd" type="password" maxlength="25"  size="22">
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
		<td class="ta_01" bgColor="#ffffff">
			<input name="passwordconfirm" type="password" maxlength="25"  size="22">
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
		<td class="ta_01" bgColor="#ffffff">
			<input name="birthday" id="birthday" type="text" maxlength="50"  size="20" onClick="WdatePicker()" >
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">联系地址：</td>
		<td class="ta_01" bgColor="#ffffff">
			<input name="address" type="text" maxlength="50"  size="20">
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
		<td class="ta_01" bgColor="#ffffff">
			<input name="contactTel" type="text" maxlength="25"  size="20">
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">手机：</td>
		<td class="ta_01" bgColor="#ffffff">
			<input name="mobile" type="text" maxlength="25"  size="20">
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">电子邮箱：</td>
		<td class="ta_01" bgColor="#ffffff">
			<input name="email" type="text" maxlength="50"  size="20">
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">是否在职：</td>
		<td class="ta_01" bgColor="#ffffff">
			<select name="isDuty" id="isDuty" style="width:155px">
			<option value="1" selected>是</option>
			<option value="2">否</option>
			</select>
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">入职日期：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<input name="onDutyDate" id="onDutyDate" type="text" maxlength="50" size="20" onClick="WdatePicker()">
		</td>
		<td align="center" bgColor="#ffffff" class="ta_01"></td>
		<td class="ta_01" bgColor="#ffffff">
		</td>
	</tr>
    
	<TR>
		<TD class="ta_01" align="center" bgColor="#f5fafe">备注：</TD>
		<TD class="ta_01" bgColor="#ffffff" colSpan="3">
			<textarea name="remark"  style="WIDTH:95%"  rows="4" cols="52"></textarea>
		</TD>
	</TR>
	
	<TR>
	<td  align="center"  colSpan="4"  class="ta_01" style="WIDTH: 100%" align="left" bgColor="#f5fafe">
		<input type="button" id="BT_File" name="BT_File" value="打开附件"  style="font-size:12px; color:black; height=22;width=55"   onClick="fileTr()">
		<input type="button" id="item" name="item" value="添加选项" style="difont-size:12px; color:black; display: none;height=20;width=80 " onClick="insertRows()">
	</td>
	</TR>
	
	<TR id="trFile" style="display: none">
	<td  align="center"  colSpan="4"  class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe">
			<table cellspacing="0"   cellpadding="1" rules="all" bordercolor="gray" border="1" id="filesTbl"
		    style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
						
				<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
					<td class="ta_01" align="center" width="10%"
						background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
						编号
					</td>
					<td class="ta_01" align="center" width="40%"
						background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
						选择待上传文件
					</td>
					<td class="ta_01" align="center" width="10%"
						background="${pageContext.request.contextPath }/images/tablehead.jpg" height=20>
						删除
					</td>
				</tr>
			
	     </table>
		</td>
	</TR>
	<TR>
	<td  align="center"  colSpan="4"  class="sep1"></td>
	</TR>
	<tr>
		<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
		<input type="button" id="BT_Submit" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"   onClick="check_null()">
		 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
		<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="window.close()">
			
		</td>
	</tr>
</table>　
</form>

</body>
</html>
