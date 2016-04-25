package com.dcfun.elec.utils;

import org.apache.struts2.ServletActionContext;

public class ValueStackUtils {

	public static void pushValueStack(Object object) {
		
		ServletActionContext.getContext().getValueStack().push(object);
	}

	public static void putValueStack(String key,Object object) {
		
		ServletActionContext.getContext().put(key, object);
	}
	
}
