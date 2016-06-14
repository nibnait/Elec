package com.dcfun.elec.web.action;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcfun.elec.base.action.BaseAction;
import com.dcfun.elec.domain.ElecExportFields;
import com.dcfun.elec.service.IElecExportFieldsService;
import com.dcfun.elec.service.IElecFileUploadService;
import com.dcfun.elec.utils.annotation.AnnotationLimit;
			 
@Controller("elecExportFieldsAction")
@Scope(value="prototype")
public class ElecExportFieldsAction extends BaseAction<ElecExportFields>{

	ElecExportFields elecExportFields = this.getModel();
	
	@Resource(name=IElecExportFieldsService.SERVICE_NAME)
	IElecExportFieldsService elecExportFieldsService;

	/**
	 * @Name: setExportFields
	 * @Description: 导出设置
	 * @Author: dcfun
	 * @Version: V1.00
	 * @Create Date: 2016-04-25 22:58:23
	 * @Parameters: ElecExportFields
	 * @Return: String: 跳转到/system/exportExcel.jsp
	 */
	@AnnotationLimit(mid="db",pid="da")
	public String setExportFields(){
		
		//根据belongTo查到ElecExportFields
		String belongTo = elecExportFields.getBelongTo();
		ElecExportFields elecExportFields = elecExportFieldsService.findElecExportFieldsById(belongTo);
		/**
		 * 组织2个Map集合，分别存放导出的字段和未导出的字段
		   * map集合的key：表示英文的名称
		   * map集合的value：表示中文的名称
		 */
		Map<String, String> map = new LinkedHashMap<>();
		Map<String, String> nomap = new LinkedHashMap<>();
		
		String[] eArray = getFieldsArray(elecExportFields.getExpFieldName(), "#");
		String[] zArray = getFieldsArray(elecExportFields.getExpNameList(), "#");
		String[] noeArray = getFieldsArray(elecExportFields.getNoExpFieldName(), "#");
		String[] nozArray = getFieldsArray(elecExportFields.getNoExpNameList(), "#");
		
		if (eArray!=null && eArray.length>0) {
			for (int i = 0; i < eArray.length; i++) {
				map.put(eArray[i], zArray[i]);
			}
		}
		
		if (noeArray!=null && noeArray.length>0) {
			for (int i = 0; i < noeArray.length; i++) {
				nomap.put(noeArray[i], nozArray[i]);
			}
		}
		
		//存放到request中
		request.setAttribute("map", map);
		request.setAttribute("nomap", nomap);
		return "setExportFields";
	}
	private String[] getFieldsArray(String expFieldName, String string) {
		if (StringUtils.isNotBlank(expFieldName)) {
			return expFieldName.split(string);
		}
		return null;
	}

	/**  
	* @Name: saveSetExportExcel
	* @Description: 保存更新导出设置的配置
	* @Author: dcfun
	* @Version: V1.00 （版本号）
	* @Create Date: 2016-04-26 01:48:40
	* @Parameters: 无
	* @Return: String：跳转到close.jsp（关闭子页面，刷新父页面）
	*/
	@AnnotationLimit(mid="dc",pid="da")
	public String saveSetExportExcel(){
		//1：获取页面中传递的5个隐藏域的值，执行update操作
		elecExportFieldsService.saveSetExportExcel(elecExportFields);
		return "close";
	}
}
