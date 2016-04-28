<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

		<table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<TR height=10><td></td></TR>			
				<tr>
				  	<td>
		                <TABLE style="WIDTH: 105px; HEIGHT: 20px" border="0">
										<TR>
											<TD align="center" background="${pageContext.request.contextPath }/images/cotNavGround.gif"><img src="${pageContext.request.contextPath }/images/yin.gif" width="15"></TD>
											<TD class="DropShadow" background="${pageContext.request.contextPath }/images/cotNavGround.gif">用户列表</TD>
										</TR>
			             </TABLE>
                   </td>
					<td class="ta_01" align="right">
					    <input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="查询" name="BT_find" 
						 onclick="gotoquery('elecUserAction_home.do')">&nbsp;&nbsp;
						<input style="font-size:12px; color:black; height=20;width=80" id="BT_Add" type="button" value="添加用户" name="BT_Add" 
						 onclick="openWindow('${pageContext.request.contextPath }/system/elecUserAction_add.do','900','700')">&nbsp;&nbsp;
						<input style="font-size:12px; color:black; height=20;width=80" id="BT_Delete" type="button" value="批量删除" name="BT_Delete" 
						 onclick="return deleteAll()">&nbsp;&nbsp;
					</td>
				</tr>
					
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe" colspan="2">			
					
									
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
							<tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
							
								<td align="center" width="5%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg"><input type="checkbox" name="selectUserAll" onclick="checkAllUser(this)"></td>
								<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">登录名</td>
								<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">用户姓名</td>
								<td align="center" width="7%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">性别</td>
								<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">联系电话</td>
								<td align="center" width="15%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">入职时间</td>
								<td align="center" width="8%" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">职位</td>
								<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">编辑</td>
								<td width="10%" align="center" height=22 background="${pageContext.request.contextPath }/images/tablehead.jpg">查看</td>
							</tr>
							
							<s:if test="#request.userList!=null && #request.userList.size()>0">
								<s:iterator value="#request.userList">
									<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="HEIGHT:22px" align="center" width="5%">
											<input type="checkbox" name="userID" id="userID" value="<s:property value="userID"/>">
										</td>
										<td style="HEIGHT:22px" align="center" width="15%">
											<s:property value="logonName"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="15%">
											<s:property value="userName"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="7%">
											<s:property value="sexID"/>
										</td>
										<td style="HEIGHT:22px" align="center" width="15%">
											<s:property value="contactTel"/>
										</td>	
										<td style="HEIGHT:22px" align="center" width="15%">
											<s:date name="onDutyDate" format="yyyy-MM-dd"/>
										</td>								
										<td style="HEIGHT:22px" align="center" width="8%">
											<s:property value="postID"/>
										</td>
										
										<td align="center" style="HEIGHT: 22px" align="center" width="10%">																	
										   <a href="#" onclick="openWindow('${pageContext.request.contextPath }/system/elecUserAction_edit.do?userID=<s:property value="userID"/>','900','700');">
										   <img src="${pageContext.request.contextPath }/images/edit.gif" border="0" style="CURSOR:hand"></a>													
										</td>
										
										<td align="center" style="HEIGHT: 22px" align="center" width="10%">
											<a href="#" onclick="openWindow('${pageContext.request.contextPath }/system/elecUserAction_edit.do?userID=<s:property value="userID"/>&viewflag=1','900','700');">
											<img src="${pageContext.request.contextPath }/images/button_view.gif" width="20" height="18" border="0" style="CURSOR:hand"></a>												
										</td>
									</tr>
								</s:iterator>
							</s:if>
								
						</table>					
						
					</td>
				</tr>        
				<%@include file="/WEB-INF/page/pageUI.jsp" %>       
			</TBODY>
		</table>
