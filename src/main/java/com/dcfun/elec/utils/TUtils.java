package com.dcfun.elec.utils;

import java.lang.reflect.ParameterizedType;

public class TUtils {

	private TUtils() {
	}

	public static Class getTClass(Class entity) {
		ParameterizedType type =  (ParameterizedType) entity.getGenericSuperclass();
		Class entityClass = (Class) type.getActualTypeArguments()[0];
		
		return entityClass;
	}

}
