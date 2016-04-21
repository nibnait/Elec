package com.dcfun.elec.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Util_FormatDate {

	public static String Date2String(Date date) {
		return new SimpleDateFormat("yyMMdd").format(date);
	}

}
