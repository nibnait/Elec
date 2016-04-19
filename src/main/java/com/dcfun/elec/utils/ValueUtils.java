package com.dcfun.elec.utils;

import org.apache.struts2.ServletActionContext;

public class ValueUtils {

	public static void putValueStack(Object object) {
		
		ServletActionContext.getContext().getValueStack().push(object);
	}

	
	
}
