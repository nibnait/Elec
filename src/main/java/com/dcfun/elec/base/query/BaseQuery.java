package com.dcfun.elec.base.query;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseQuery {


	//将收集到的表单元素封装成map
	private Map<String, Object> keyValues = new HashMap<String, Object>();

	public Map<String, Object> getKeyValues() {
		return keyValues;
	}

	public void setKeyValues(Map<String, Object> keyValues) {
		this.keyValues = keyValues;
	}

	public abstract Map<String, Object> buildWhere();
}
