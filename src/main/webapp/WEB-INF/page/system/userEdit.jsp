<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
   <title>
   <s:if test="viewflag==null">
   	编辑用户
   </s:if>
   <s:else>
   	查看用户明细
   </s:else>
   </title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
   <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/showText.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/limitedTextarea.js"></script>
   <script language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
   <Script language="javascript">
    /**DOM对象*/
	/**function check_null(){
	
		var theForm=document.Form1;
	    
	  
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
	   //如果是否在职选择是，则入职时间必须要填写，如果是否在职选择否，则离职日期必须要填写
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
	   else{
		   if(theForm.offDutyDate.value==""){
			   alert("该用户属于离职人员，请填写离职时间");
			   theForm.offDutyDate.focus();
			   return false; 
		   }
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
	   document.Form1.action="editUser.do";
	   document.Form1.submit();
	  	
	}
    */
    /**jquery对象*/
    function check_null(){
	    if($.trim($("input[name='userName']").val())=="")
		{
			alert("用户姓名不能为空");
			$("input[name='userName']")[0].focus();
			return false;
		}
		if($("select[name='sexID']").val()=="")
		{
			alert("请选择性别");
			$("select[name='sexID']")[0].focus();
			return false;
		}
	    if($("select[name='jctID']").val()=="")
		{
			alert("请选择所属单位");
			$("select[name='jctID']")[0].focus();
			return false;
		}
	    if($.trim($("input[name='onDutyDate']").val())=="")
		{
			alert("入职时间不能为空");
			$("input[name='onDutyDate']")[0].focus();
			return false;
		}
	    if($("select[name='postID']").val()=="")
		{
			alert("请选择职位");
			$("select[name='postID']")[0].focus();
			return false;
		}
        if($("input[name='logonPwd']").val()!=$("input[name='passwordconfirm']").val()){
		  	alert("两次输入密码不一致，请重新输入");
		  	return false;
		}
        if($("textarea[name='remark']").val().length>250){
           
         	alert("备注字符长度不能超过250");
         	$("textarea[name='remark']")[0].focus();
 			return false; 
        }
       	//选择[是]
       	if($("#isDuty option:nth-child(1)").is(":selected")){
    	   if($.trim($("input[name='onDutyDate']").val())==""){
 			   alert("该用户属于在职人员，请填写入职时间");
 			   $("input[name='onDutyDate']")[0].focus();
 			   return false; 
 		   }
       	} 
        //选择[否]
        if($("#isDuty option:nth-child(2)").is(":selected")){
        	if($.trim($("input[name='offDutyDate']").val())==""){
        		alert("该用户属于离职人员，请填写离职时间！");
        	    $("input[name='offDutyDate']")[0].focus();
     		    return false;
        	}
        }
 	    //上传的文件不能为空
 	    var $tbl=$("#filesTbl tr");
        var flag = false;
 	    $tbl.each(function(index,domEle){
 		   //去掉表头
 		   if(index==0){
 			   return true;//相当于continue
 		   }
 		   //从1开始是为了去掉表头
 		   else{
 			   var $uploads = $(this).find("td:nth-child(2)").find("input[name='uploads']").val();
 			   if($.trim($uploads)==""){
 				  alert("请选择第"+ index +"行的文件路径！");
 				  flag = true;
 				  return false;//相当于break
 			   }
 		   }
 	    })
 	    //说明附件存在错误
 	    if(flag){
 		   return false;
 	    }
      
	    /**正则表达式的使用*/	
        var theForm=document.Form1;
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
  		
  	   $("form:first").attr("action","${pageContext.request.contextPath }/system/elecUserAction_save.do");
	   $("form:first").submit();
	}
	function checkTextAreaLen(){
  		var remark = new Bs_LimitedTextarea('remark', 250); 
  		remark.infolineCssStyle = "font-family:arial; font-size:11px; color:gray;";
  		remark.draw();	
    }
    window.onload=function(){
		checkTextAreaLen();
    }
    
    /**如果选择离职时间，则【是否在职】默认选择"否"，如果没有选择离职时间，则【是否在职】默认选择"是"*/
    function checkIsDuty(o){   
 	   var offDutyDate = o.value;
 	   var isDutySelect = document.getElementById("isDuty");
 	   if(offDutyDate!=""){
 		   isDutySelect.options[1].selected = true; //否
 	   }
 	   else{
 		   isDutySelect.options[0].selected = true; //是
 	   }
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
    <s:hidden name="userID"></s:hidden>
    <s:hidden name="password" value="%{logonPwd}"></s:hidden>
    <table cellSpacing="1" cellPadding="5" width="680" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">

	 <tr>
		<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
		 <font face="宋体" size="2"><strong>
		 <s:if test="viewflag==null">
		   	编辑用户
		   </s:if>
		   <s:else>
		   	查看用户明细
		   </s:else>
		 </strong></font>
		</td>
    </tr>
       
     <tr>
         <td align="center" bgColor="#f5fafe" class="ta_01">登&nbsp;&nbsp;录&nbsp;&nbsp;名：<font color="#FF0000">*</font></td>
         <td class="ta_01" bgColor="#ffffff">
         	<s:textfield name="logonName" maxlength="25" id="logonName" size="20" readonly="true"></s:textfield>
         	<div id="check"></div>
         </td>
         <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户姓名：<font color="#FF0000">*</font></td>
         <td class="ta_01" bgColor="#ffffff">
         	<s:textfield name="userName" maxlength="25" id="userName" size="20"></s:textfield>
         </td>
    </tr>
	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">性别：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.sexList" name="sexID" id="sexID"
					  listKey="ddlCode" listValue="ddlName"
					  headerKey="" headerValue="请选择"
					  cssStyle="width:155px">
			</s:select>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">职位：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.postList" name="postID" id="postID"
					  listKey="ddlCode" listValue="ddlName"
					  headerKey="" headerValue="请选择"
					  cssStyle="width:155px">
			</s:select>
		</td>
	</tr>
	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">所属单位：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.jctList" name="jctID" id="jctID"
					  listKey="ddlCode" listValue="ddlName"
					  headerKey="" headerValue="请选择"
					  cssStyle="width:155px" onchange="findJctUnit(this)">
			</s:select>
			
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">单位名称：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.jctUnitList" name="jctUnitID" id="jctUnitID"
					  listKey="ddlCode" listValue="ddlName"
					  cssStyle="width:155px">
			</s:select>
		</td>
	</tr>
	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">密码：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:password name="logonPwd" id="logonPwd" showPassword="true" maxlength="25"  size="22"></s:password>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:password name="passwordconfirm" id="passwordconfirm" value="%{logonPwd}" showPassword="true" maxlength="25"  size="22"></s:password>
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:date name="birthday" format="yyyy-MM-dd" var="birthdayDate"/>
			<s:textfield name="birthday" id="birthday" value="%{birthdayDate}" maxlength="50"  size="20" onClick="WdatePicker()"></s:textfield>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">联系地址：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:textfield name="address" maxlength="50"  size="20"></s:textfield>
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:textfield name="contactTel" maxlength="25"  size="20"></s:textfield>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">手机：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:textfield name="mobile" maxlength="25"  size="20"></s:textfield>
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">电子邮箱：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:textfield name="email" maxlength="50"  size="20"></s:textfield>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">是否在职：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:select list="#request.isDutyList" name="isDuty" id="isDuty"
					  listKey="ddlCode" listValue="ddlName"
					  cssStyle="width:155px">
			</s:select>
		</td>
	</tr>

	<tr>
		<td align="center" bgColor="#f5fafe" class="ta_01">入职日期：<font color="#FF0000">*</font></td>
		<td class="ta_01" bgColor="#ffffff">
			<s:date name="onDutyDate" format="yyyy-MM-dd" var="onDutyDateDate"/>
			<s:textfield name="onDutyDate" id="onDutyDate" value="%{onDutyDateDate}" maxlength="50" size="20" onClick="WdatePicker()"></s:textfield>
		</td>
		<td align="center" bgColor="#f5fafe" class="ta_01">离职日期：</td>
		<td class="ta_01" bgColor="#ffffff">
			<s:date name="offDutyDate" format="yyyy-MM-dd" var="offDutyDateDate"/>
			<s:textfield name="offDutyDate" id="offDutyDate" value="%{offDutyDateDate}" maxlength="50" size="20" onClick="WdatePicker()"></s:textfield>
		</td>
	</tr>
    
	<TR>
		<TD class="ta_01" align="center" bgColor="#f5fafe">备注：</TD>
		<TD class="ta_01" bgColor="#ffffff" colSpan="3">
			<s:textarea name="remark"  cssStyle="WIDTH:95%"  rows="4" cols="52"></s:textarea>
		</TD>
	</TR>
	
	<TR>
		<TD class="ta_01" align="center" bgColor="#f5fafe">附件（下载）：</TD>
		<TD class="ta_01" bgColor="#ffffff" colSpan="3">
			<s:if test="elecUserFiles!=null && elecUserFiles.size()>0">
				<s:iterator value="elecUserFiles">
					<a href="#" onclick="openWindow('${pageContext.request.contextPath }/system/elecUserAction_download.do?fileID=<s:property value="fileID"/>','700','400');">
						<s:property value="fileName"/>
					</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:date name="progressTime" format="yyyy-MM-dd HH:mm:ss"/>
					<br>
				</s:iterator>
			</s:if>
		</TD>
	</TR>
	<TR>
	<s:if test="viewflag==null">
	<td  align="center"  colSpan="4"  class="ta_01" style="WIDTH: 100%" align="left" bgColor="#f5fafe">
		<input type="button" id="BT_File" name="BT_File" value="打开附件"  style="font-size:12px; color:black; height=22;width=55"   onClick="fileTr()">
		<input type="button" id="item" name="item" value="添加选项" style="difont-size:12px; color:black; display: none;height=20;width=80 " onClick="insertRows()">
	</td>
	</s:if>
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
			<s:if test="viewflag==null">
				<input type="button" id="BT_Submit" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"  onClick="check_null()">
			</s:if>
		    <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
		    <input style="font-size:12px; color:black; height=22;width=55" type="button" value="关闭"  name="Reset1"  onClick="window.close()">
	    </td>
	</tr>
</table>　
</form>

</body>
</html>
