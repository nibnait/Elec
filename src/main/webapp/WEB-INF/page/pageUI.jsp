<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<tr>
      <td width="100%" height="1"  colspan="2">
        <table border="0" width="100%" cellspacing="0" cellpadding="0">
         <s:if test="#request.page!=null">
         		<tr>
		             <td width="15%" align="left">总记录数：<s:property value="%{#request.page.totalResult}"/>条</td> 
		             <td width="14%" align="right"></td>   
		             <s:if test="#request.page.firstPage">
		             	<td width="8%" align="center">首页&nbsp;&nbsp;|</td>
		             	<td width="10%" align="center">上一页&nbsp;&nbsp;&nbsp;|</td>
		             </s:if>   
		             <s:else>
		             	<td width="8%" align="center"><u><a href="#" onClick="gotopage('elecUserAction_home.do','start')">首页&nbsp;&nbsp;|</a></u></td>
		             	<td width="10%" align="center"><u><a href="#" onClick="gotopage('elecUserAction_home.do','prev')">上一页&nbsp;&nbsp;&nbsp;|</a></u></td>
		             </s:else>
		             <s:if test="#request.page.lastPage">
					 	<td width="10%" align="center">下一页&nbsp;&nbsp;&nbsp;|</td>
		             	<td width="8%" align="center">末页</td>
		             </s:if>
		             <s:else>
		             	<td width="10%" align="center"><u><a href="#" onClick="gotopage('elecUserAction_home.do','next')">下一页&nbsp;&nbsp;&nbsp;|</a></u></td>
		             	<td width="8%" align="center"><u><a href="#" onClick="gotopage('elecUserAction_home.do','end')">末页</a></u></td>
		             </s:else>
		             <td width="6%" align="center">第<s:property value="%{#request.page.pageNo}"/>页</td>
		             <td width="6%" align="center">共<s:property value="%{#request.page.sumPage}"/>页</td>
		             <td width="21%" align="right">至第<input size="1" type="text" name="goPage" >页
		
		
		
		             <u><a href="#" onClick="gotopage('elecUserAction_home.do','go')">确定</a></u></td>
		             
		             <td><input type="hidden" name="pageNO" value="${page.pageNo }" ></td> 
		             <td><input type="hidden" name="prevpageNO" value="${page.pageNo-1 }"></td>
		             <td><input type="hidden" name="nextpageNO" value="${page.pageNo+1 }"></td>
		             <td><input type="hidden" name="sumPage" value="${page.sumPage }" ></td>
	           </tr>
         </s:if>
          
        </table>       
      </td>
    </tr> 