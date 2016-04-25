package com.dcfun.elec.utils;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

public class FileUploadUtils {
	
	/**
	 * * 多文件上传的要求：
		  1：将上传的文件统一放置到upload的文件夹下
		  2：将每天上传的文件，使用日期格式的文件夹分开，将每个业务的模块放置统一文件夹下
		  3：上传的文件名要指定唯一，可以使用UUID的方式，也可以使用日期作为文件名
		  4：封装一个文件上传的方法，该方法可以支持多文件的上传，即支持各种格式文件的上传
		  5：保存路径path的时候，使用相对路径进行保存，这样便于项目的可移植性
		  
	 * @param upload：待上传文件
	 * @param fileName：文件名
	 * @param modelName: 模块名
	 * @return String filePath：上传到的 服务器的地址
	 */

	public static String uploadFiles(File upload, String fileName, String modelName) {
		
		String bathpath = ServletActionContext.getServletContext().getRealPath("/upload");
		String datepath = FormatDateUtils.Date2String(new Date());
		String filepath = bathpath+"/"+datepath+"/"+modelName;
		File file = new File(filepath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String destfileName = UUID.randomUUID().toString()+suffix;
		File destFile = new File(filepath,destfileName);
		upload.renameTo(destFile);
		
		return "/upload"+"/"+datepath+"/"+modelName+"/"+destfileName;
		
	}

}
