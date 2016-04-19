

<%@ page language="java" pageEncoding="UTF-8"%>

<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
 <tr>
  <td>
   <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;"><legend align="left"><a href="#" onclick="displaypermission()"><span id="permissionflag">权限分配&nbsp;关闭</span></a></legend>
 
     <table cellSpacing="0" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0" id="dataPopedom">			 
			  <tr>
				 <td class="ta_01" colspan=2 align="left" width="100%" > 
				 	全选/全不选<input type="checkbox" name="selectOperAll" onclick="checkAllOper(this)">
				 	<br>
				         
				         <table cellspacing="0" align="center" width="100%" cellpadding="1" rules="all" bordercolor="gray" border="1" 
									style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
													
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="aa_aa" value="aa" onClick='goSelect(this.id)' checked="checked">
															技术设施维护管理：
														</td>
													
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="aa_ab" value="ab" onClick='goSelect(this.id)' checked="checked">
														仪器设备管理
													
													
													
														
														<input type="checkbox"  name="selectoper" id="aa_ac" value="ac" onClick='goSelect(this.id)' checked="checked">
														设备校准检修
													
													
													
														
														<input type="checkbox"  name="selectoper" id="aa_ad" value="ad" onClick='goSelect(this.id)' checked="checked">
														设备购置计划
													
												
													
														
															</td>
														</tr>
															
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ae_ae" value="ae" onClick='goSelect(this.id)' checked="checked">
															技术资料图纸管理：
														</td>
													
													
												
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ae_af" value="af" onClick='goSelect(this.id)' checked="checked">
														资料图纸管理
													
												
														
															</td>
															</tr>
															
														
														<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
														<td class="ta_01"  align="left" width="18%" height="22" background="../images/tablehead.jpg">
															<input type="checkbox"  name="selectoper" id="ag_ag" value="ag" onClick='goSelect(this.id)' >
															站点设备运行管理：
														</td>
													
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="ag_ah" value="ah" onClick='goSelect(this.id)' >
														站点基本信息
													
												
														
														<input type="checkbox"  name="selectoper" id="ag_ai" value="ai" onClick='goSelect(this.id)' >
														运行情况
													
												
														
														<input type="checkbox"  name="selectoper" id="ag_aj" value="aj" onClick='goSelect(this.id)' >
														维护情况
													
												
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
															<input type="checkbox"  name="selectoper" id="am_am" value="am" onClick='goSelect(this.id)' checked="checked">
															系统管理：
														</td>
													
													
														
															<td class="ta_01"  align="left" width="82%" height="22">
															
														
														<input type="checkbox"  name="selectoper" id="am_an" value="an" onClick='goSelect(this.id)' checked="checked">
														用户管理
													
												
														
														<input type="checkbox"  name="selectoper" id="am_ao" value="ao" onClick='goSelect(this.id)' checked="checked">
														角色管理
													
												
													
														
														<input type="checkbox"  name="selectoper" id="am_ap" value="ap" onClick='goSelect(this.id)' checked="checked">
														运行监控
													
												
														
														<input type="checkbox"  name="selectoper" id="am_aq" value="aq" onClick='goSelect(this.id)' checked="checked">
														数据字典维护
													
												
														
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

	
	<tr>
		<td height=10></td>
	</tr>
	
  <tr>
	<td>
	
   <fieldset style="width:100%; border : 1px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;"><legend align="left"><a href="#" onclick="displayuser()"><span id="userflag">用户分配&nbsp;关闭</span></a></legend>
 
	<table cellspacing="0" align="center" width="100%" cellpadding="1" rules="all" bordercolor="gray" border="1" id="dataUser"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
			    
				<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
				   <td class="ta_01"  align="center" width="20%" height=22 background="../images/tablehead.jpg">选中<input type="checkbox" name="selectUserAll" onclick="checkAllUser(this)"></td>
				   <td class="ta_01"  align="center" width="40%" height=22 background="../images/tablehead.jpg">登录名</td>
				   <td class="ta_01"  align="center" width="40%" height=22 background="../images/tablehead.jpg">用户姓名</td>
				</tr>
				 <tr onmouseover="this.style.backgroundColor = 'white'"
					onmouseout="this.style.backgroundColor = '#F5FAFE';">
					<td style="HEIGHT: 22px" class="ta_01" align="center" width="20%">
						<input type="checkbox" name="selectuser" value="123456789" checked>
					</td>
					<td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
						zhangsan
					</td>
					<td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
						张三
					</td>
				</tr>
				<tr onmouseover="this.style.backgroundColor = 'white'"
					onmouseout="this.style.backgroundColor = '#F5FAFE';">
					<td style="HEIGHT: 22px" class="ta_01" align="center" width="20%">
						<input type="checkbox" name="selectuser" value="123456789">
					</td>
					<td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
						lisi
					</td>
					<td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
						李四
					</td>
				</tr>
		</table>
    </fieldset>
	 </td>
	 </tr>
			<tr>
		   <td class="ta_01" align="center" colspan=3 width="100%"  height=20>
		   <input type="button" name="saverole" onclick="saveRole()" style="font-size:12px; color:black; height=20;width=50" value="保存">
		   </td>
		</tr>
   </table>
