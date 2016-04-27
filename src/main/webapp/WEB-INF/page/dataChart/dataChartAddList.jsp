<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray"
	border="1" id="filesTb2" align="center"
	style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:700; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">

	<tr
		style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
		<td class="ta_01" align="center" width="20%"
			background="../images/tablehead.jpg" height=20>
			编号
		</td>
		<td class="ta_01" align="center" width="80%"
			background="../images/tablehead.jpg" height=20>
			已上传文件
		</td>
	</tr>
	<s:if test="#request.list!=null && #request.list.size()>0">
		<s:iterator value="#request.list" status="st"> <!-- status:循环变量，在map栈中-->
			<tr onmouseover="this.style.backgroundColor = 'white'"
				onmouseout="this.style.backgroundColor = '#F5FAFE';">
				<td class="ta_01" align="center" width="20%">
					<s:property value="#st.index+1"/>
				</td>
				<td class="ta_01" align="center" width="80%">
					<a href="#" onclick="openWindow('${pageContext.request.contextPath }/datachart/elecFileUploadAction_download.do?seqId=<s:property value="seqId"/>','700','400');"><s:property value="fileName"/></a>
				</td>
			</tr>
		</s:iterator>
	</s:if>
</table>
