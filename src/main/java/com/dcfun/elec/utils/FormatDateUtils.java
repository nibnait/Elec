package com.dcfun.elec.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class FormatDateUtils {

	public static String Date2String(Date date) {
		return new SimpleDateFormat("yyMMdd").format(date);
	}
	
	/**将日期类型转换成String类型，yyyy-MM-dd HH:mm:ss格式*/
	public static String dateToString(Date date) {
		String sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return sDate;
	}
}
