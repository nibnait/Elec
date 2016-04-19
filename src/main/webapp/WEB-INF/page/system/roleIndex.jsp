<%@ page language="java" pageEncoding="UTF-8"%>
<style type="text/css">
<!--
fieldset div {
	float:left;
	width:24%;
	text-align:left;
	line-height:25px;
}
td div {
	float:left;
	width:24%;
	text-align:left;
	line-height:25px;
}
-->
</style>
<HTML>
	<HEAD>
		<title>角色权限管理</title>		
		<LINK href="${pageContext.request.contextPath }/css/Style.css"  type="text/css" rel="stylesheet">
		<script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
		<script language="javascript">
		  
		 function saveRole(){
		 
           document.Form2.roleID.value=document.Form1.roleID.value;
		   document.Form2.action="saveRole.do";
		   document.Form2.submit();
		}
		
       
       function selectRole(){
          if(document.Form1.roleID.value=="0"){
          
             document.Form1.action="roleIndex.jsp";
             document.Form1.submit();            
          }else{
            Pub.submitActionWithForm('Form2','./roleEdit.jsp','Form1');
          }
       }
       /**
	   function checkAllOper(oper){
          var selectoper = document.getElementsByName("selectoper");
          for(var i=0;i<selectoper.length;i++){
          	selectoper[i].checked = oper.checked;
          }
       }
	   function checkAllUser(user){
          var selectuser = document.getElementsByName("selectuser");
          for(var i=0;i<selectuser.length;i++){
          	selectuser[i].checked = user.checked;
          }
       }
       **/
       /**
       function displayuser(){
			if(document.getElementById("dataUser").style.display == "none"){
			    document.getElementById("userflag").innerText = "用户分配 关闭";
				document.getElementById("dataUser").style.display = "";
			}
			else{
				document.getElementById("userflag").innerText = "用户分配 打开";
				document.getElementById("dataUser").style.display = "none";
			}
		}
		function displaypermission(){
			if(document.getElementById("dataPopedom").style.display == "none"){
				document.getElementById("permissionflag").innerText = "权限分配 关闭";
				document.getElementById("dataPopedom").style.display = "";
			}
			else{
				document.getElementById("permissionflag").innerText = "权限分配 打开";
				document.getElementById("dataPopedom").style.display = "none";
			}
		}
		**/
		function displayuser(){
			if($("#dataUser").css("display")== "none"){
			    $("#userflag").text("用户分配 关闭");
				$("#dataUser").css("display","");
			}
			else{
				 $("#userflag").text("用户分配 打开");
				 $("#dataUser").css("display","none");
			}
		}
		function displaypermission(){
			if($("#dataPopedom").css("display") == "none"){
				$("#permissionflag").text("权限分配 关闭");
				$("#dataPopedom").css("display","");
			}
			else{
				$("#permissionflag").text("权限分配 打开");
				$("#dataPopedom").css("display","none");
			}
		}
		//权限：全部选中/不选中
		function checkAllOper(oper){
			$("input[type='checkbox'][name='selectoper']").attr("checked",oper.checked);
		}
		//用户：全部选中/不选中
		function checkAllUser(user){
			$("input[type='checkbox'][name='selectuser']").attr("checked",user.checked);
		}
		//选中复选框，触发事件
		function goSelect(id){
			//按照_符号分隔
			var array = id.split("_");
			if(array[0] == array[1]){//此时说明操作的（父）节点
				//选中父
				if($("#"+id)[0].checked){
					//子都选中
					$("input[type='checkbox'][name='selectoper'][id^='"+array[0]+"']").attr("checked",true);
				}
				//取消父
				else{
					//子都取消
					$("input[type='checkbox'][name='selectoper'][id^='"+array[0]+"']").attr("checked",false);
				}
			}
			else{//说明此时操作的子设置中的一个(子)
				//当选中子设置中的一个，则父一定被选中
				if($("#"+id)[0].checked){
					$("input[type='checkbox'][name='selectoper'][id^='"+array[0]+"'][id$='"+array[0]+"']").attr("checked",true);
				}
				//当取消子设置中的一个
				else{
					//先查找子设置的对象
					var $check = $("input[type='checkbox'][name='selectoper'][id^='"+array[0]+"']:not([id$='"+array[0]+"'])");
					//遍历子设置的对象
					/**
					 * flag:用于判断当前子设置的对象是否有被选中
					 *   * flag=false，子对象都没有被选中，此时父要被取消
					 *   * flag=true，子对象中至少有一个被选中，此时不做任何操作
					 */
					var flag = false;
					$check.each(function(index,domEle){
						if(domEle.checked){
							flag = true;
							return false;
						}
					})
					if(!flag){
						$("input[type='checkbox'][name='selectoper'][id^='"+array[0]+"'][id$='"+array[0]+"']").attr("checked",false);
					}
				}
			}
		}
		</script>
	</HEAD>
		
	<body>
	 <Form name="Form1" id="Form1"  method="post" style="margin:0px;">
			<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" colspan=2 align="center" background="${pageContext.request.contextPath }/images/b-info.gif">
							<font face="宋体" size="2"><strong>角色管理</strong></font>
						</td>
					</tr>	
					<tr>
					   <td class="ta_01" colspan=2 align="right" width="100%"  height=10>
					   </td>
					</tr>		
					<tr>
						<td class="ta_01" align="right" width="35%" >角色类型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="ta_01" align="left"  width="65%" >
						  	<select name="roleID" id="Form1_roleID" class="bg" style="width:180px" onchange="selectRole()">
							    <option value="0">请选择</option>
							    <option value="1">系统管理员</option>
							    <option value="2">高级管理员</option>
							    <option value="3">中级管理员</option>
							    <option value="4">业务用户</option>
							    <option value="5">一般用户</option>
							    <option value="6">普通用户</option>
							</select>
						</td>				
					</tr>
				    <tr>
					   <td class="ta_01" align="right" colspan=2 align="right" width="100%"  height=10></td>
					</tr>
				</TBODY>
			  </table>
	 </form>
	 
	<form id="Form2" name="Form2" action="${pageContext.request.contextPath }/system/elecRoleAction_home.do" method="post" style="margin:0px;">
	 
	  <table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
		 <tr>
		  <td>
		   <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;"><legend align="left">权限分配</legend>
		 
		     <table cellSpacing="0" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">			 
					  <tr>
						 <td class="ta_01" colspan=2 align="left" width="100%" > 
						 
						        <table cellspacing="0" align="center" width="100%" cellpadding="1" rules="all" bordercolor="gray" border="1" 
									style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
													
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg" >
															<input type="checkbox"  name="selectoper" id="aa_aa" value="aa" onClick='goSelect(this.id)' >
															技术设施维护管理：
														</td>
													
															<td class="ta_01"  align="left" width="82%" height="22">
															
														<div>
														<input type="checkbox"  name="selectoper" id="aa_ab" value="ab" onClick='goSelect(this.id)' >
														仪器设备管理
														</div>
													
													
														<div>
														<input type="checkbox"  name="selectoper" id="aa_ac" value="ac" onClick='goSelect(this.id)' >
														设备校准检修
														</div>
													
													
														<div>
														<input type="checkbox"  name="selectoper" id="aa_ad" value="ad" onClick='goSelect(this.id)' >
														设备购置计划
														</div>
												
													
														
															</td>
														</tr>
															
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ae_ae" value="ae" onClick='goSelect(this.id)' >
															技术资料图纸管理：
														</td>
													
													
												
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ae_af" value="af" onClick='goSelect(this.id)' >
														资料图纸管理
													
												
														
															</td>
															</tr>
															
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ag_ag" value="ag" onClick='goSelect(this.id)' >
															站点设备运行管理：
														</td>
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														<div>
														<input type="checkbox"  name="selectoper" id="ag_ah" value="ah" onClick='goSelect(this.id)' >
														站点基本信息
														</div>
												
														<div>
														<input type="checkbox"  name="selectoper" id="ag_ai" value="ai" onClick='goSelect(this.id)' >
														运行情况
														</div>
												
														<div>
														<input type="checkbox"  name="selectoper" id="ag_aj" value="aj" onClick='goSelect(this.id)' >
														维护情况
														</div>
												
															</td>
															</tr>
															
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ak_ak" value="ak" onClick='goSelect(this.id)' >
															监测台建筑管理：
														</td>
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ak_al" value="al" onClick='goSelect(this.id)' >
														监测台建筑管理
													
												
														
															</td>
															</tr>
															
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="am_am" value="am" onClick='goSelect(this.id)' >
															系统管理：
														</td>
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														<div>
														<input type="checkbox"  name="selectoper" id="am_an" value="an" onClick='goSelect(this.id)' >
														用户管理
														</div>
												
														<div>
														<input type="checkbox"  name="selectoper" id="am_ao" value="ao" onClick='goSelect(this.id)' >
														角色管理
														</div>
												
													
														<div>
														<input type="checkbox"  name="selectoper" id="am_ap" value="ap" onClick='goSelect(this.id)' >
														运行监控
														</div>
														
														<div>
														<input type="checkbox"  name="selectoper" id="am_aq" value="aq" onClick='goSelect(this.id)' >
														数据字典维护
														</div>
												
														
															</td>
															</tr>
															
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ar_ar" value="ar" onClick='goSelect(this.id)' >
															审批流转：
														</td>
													
													
												
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ar_as" value="as" onClick='goSelect(this.id)' >
														审批流程管理
													
												
													
														
														<input type="checkbox"  name="selectoper" id="ar_at" value="at" onClick='goSelect(this.id)' >
														申请模板管理
													
												
														
														<input type="checkbox"  name="selectoper" id="ar_au" value="au" onClick='goSelect(this.id)' >
														起草申请
													
												
														
														<input type="checkbox"  name="selectoper" id="ar_av" value="av" onClick='goSelect(this.id)' >
														待我审批
													
												
														
														<input type="checkbox"  name="selectoper" id="ar_aw" value="aw" onClick='goSelect(this.id)' >
														我的申请查询
													
												
														
															</td>
															</tr>
															
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ba_ba" value="ba" onClick='goSelect(this.id)' >
															系统登录：
														</td>
													
													
												
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ba_bb" value="bb" onClick='goSelect(this.id)' >
														首页显示
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ba_bc" value="bc" onClick='goSelect(this.id)' >
														标题
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ba_bd" value="bd" onClick='goSelect(this.id)' >
														菜单
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ba_be" value="be" onClick='goSelect(this.id)' >
														加载左侧树型菜单
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ba_bf" value="bf" onClick='goSelect(this.id)' >
														改变框架
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ba_bg" value="bg" onClick='goSelect(this.id)' >
														加载主显示页面
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ba_bh" value="bh" onClick='goSelect(this.id)' >
														站点运行
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ba_bi" value="bi" onClick='goSelect(this.id)' >
														设备运行
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ba_bj" value="bj" onClick='goSelect(this.id)' >
														重新登录
													
												
													
													
														
															</td>
															</tr>
															
														
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ca_ca" value="ca" onClick='goSelect(this.id)' >
															运行监控：
														</td>
													
													
												
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ca_cb" value="cb" onClick='goSelect(this.id)' >
														保存
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ca_cc" value="cc" onClick='goSelect(this.id)' >
														ajax进度条
													
												
													
													
														
															</td>
															</tr>
															
														
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="da_da" value="da" onClick='goSelect(this.id)' >
															导出设置：
														</td>
													
													
												
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="da_db" value="db" onClick='goSelect(this.id)' >
														导出设置设置
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="da_dc" value="dc" onClick='goSelect(this.id)' >
														导出设置保存
													
												
													
													
														
															</td>
															</tr>
															
														
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ea_ea" value="ea" onClick='goSelect(this.id)' >
															数据字典：
														</td>
													
													
												
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ea_eb" value="eb" onClick='goSelect(this.id)' >
														编辑
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ea_ec" value="ec" onClick='goSelect(this.id)' >
														保存
													
												
													
													
														
															</td>
															</tr>
															
														
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="fa_fa" value="fa" onClick='goSelect(this.id)' >
															用户管理：
														</td>
													
													
												
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="fa_fb" value="fb" onClick='goSelect(this.id)' >
														新增
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="fa_fc" value="fc" onClick='goSelect(this.id)' >
														保存
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="fa_fd" value="fd" onClick='goSelect(this.id)' >
														编辑
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="fa_fe" value="fe" onClick='goSelect(this.id)' >
														删除
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="fa_ff" value="ff" onClick='goSelect(this.id)' >
														验证登录名
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="fa_fg" value="fg" onClick='goSelect(this.id)' >
														导出excel
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="fa_fh" value="fh" onClick='goSelect(this.id)' >
														excel导入页面
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="fa_fi" value="fi" onClick='goSelect(this.id)' >
														excel导入
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="fa_fj" value="fj" onClick='goSelect(this.id)' >
														人员统计
													
												
													
													
														
															</td>
															</tr>
															
														
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ga_ga" value="ga" onClick='goSelect(this.id)' >
															角色管理：
														</td>
													
													
												
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ga_gb" value="gb" onClick='goSelect(this.id)' >
														编辑
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ga_gc" value="gc" onClick='goSelect(this.id)' >
														保存
													
												
													
													
														
															</td>
															</tr>
															
														
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ha_ha" value="ha" onClick='goSelect(this.id)' >
															申请流程管理：
														</td>
													
													
												
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ha_hb" value="hb" onClick='goSelect(this.id)' >
														部署流程定义
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ha_hc" value="hc" onClick='goSelect(this.id)' >
														保存
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ha_hd" value="hd" onClick='goSelect(this.id)' >
														查看流程图
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ha_he" value="he" onClick='goSelect(this.id)' >
														删除流程定义
													
												
													
													
														
															</td>
															</tr>
															
														
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ia_ia" value="ia" onClick='goSelect(this.id)' >
															申请模板管理：
														</td>
													
													
												
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ia_ib" value="ib" onClick='goSelect(this.id)' >
														新增
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ia_ic" value="ic" onClick='goSelect(this.id)' >
														新增保存
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ia_id" value="id" onClick='goSelect(this.id)' >
														编辑
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ia_ie" value="ie" onClick='goSelect(this.id)' >
														编辑保存
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ia_if" value="if" onClick='goSelect(this.id)' >
														删除
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ia_ig" value="ig" onClick='goSelect(this.id)' >
														下载
													
												
													
													
														
															</td>
															</tr>
															
														
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ja_ja" value="ja" onClick='goSelect(this.id)' >
															申请流程管理：
														</td>
													
													
												
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ja_jb" value="jb" onClick='goSelect(this.id)' >
														提交申请页面
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ja_jc" value="jc" onClick='goSelect(this.id)' >
														提交申请
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ja_jd" value="jd" onClick='goSelect(this.id)' >
														我的申请查询首页
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ja_je" value="je" onClick='goSelect(this.id)' >
														待我审批首页
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ja_jf" value="jf" onClick='goSelect(this.id)' >
														跳转审批处理页面
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ja_jg" value="jg" onClick='goSelect(this.id)' >
														下载
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ja_jh" value="jh" onClick='goSelect(this.id)' >
														审核
													
												
													
													
													
														
														<input type="checkbox"  name="selectoper" id="ja_ji" value="ji" onClick='goSelect(this.id)' >
														审核历史
						     	</table> 
						     	
						   </td>
						</tr>						
					 <input type="hidden" name="roleID" >						
				 </table>	
		        </fieldset>
			  </td>
			 </tr>					
	  </table>			    
	</Form>
	</body>
</HTML>
