package com.dcfun.elec.service.impl;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.elec.dao.IElecFileUploadDao;
import com.dcfun.elec.dao.IElec_Dao;
import com.dcfun.elec.domain.ElecFileUpload;
import com.dcfun.elec.domain.ElecText;
import com.dcfun.elec.service.IElecFileUploadService;
import com.dcfun.elec.service.IElec_Service;
import com.dcfun.elec.utils.*;
import com.dcfun.elec.utils.lucene.LuceneUtils;

@Service(IElecFileUploadService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecFileUploadServiceImpl implements IElecFileUploadService {

	@Resource(name = IElecFileUploadDao.SERVICE_NAME)
	IElecFileUploadDao elecFileUploadDao;

	/**
	 * @Name: saveFileUpload
	 * @Description: 保存资料图纸管理（管理员进行文件上传操作）
	 * @Author: dcfun
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016-04-26 17:56:23
	 * @Parameters: 无
	 * @Return: String：重定向到dataChart/dataChartAdd.jsp
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveFileUpload(ElecFileUpload elecFileUpload) {

		// 获取当前时间，转换成String类型
		String progressTime = FormatDateUtils.dateToString(new Date());
		// 1：文件上传
		// 2：组织PO对象，保存到资料图纸管理的数据库表中
		// 获取所属单位
		String projId = elecFileUpload.getProjId();
		// 获取图纸类别
		String belongTo = elecFileUpload.getBelongTo();
		// 获取上传文件的数组
		File[] uploads = elecFileUpload.getUploads();
		// 获取上传文件的文件名
		String[] uploadsFileNames = elecFileUpload.getUploadsFileName();
		// 获取上传文件的描述
		String[] comments = elecFileUpload.getComments();
		// 遍历循环组织PO对象，完成保存
		if (uploads != null && uploads.length > 0) {
			for (int i = 0; i < uploads.length; i++) {
				// 组织PO对象
				ElecFileUpload fileUpload = new ElecFileUpload();
				fileUpload.setProjId(projId);// 所属单位
				fileUpload.setBelongTo(belongTo);// 图纸类别
				fileUpload.setProgressTime(progressTime);// 当前时间
				fileUpload.setFileName(uploadsFileNames[i]);// 上传的文件名
				// 文件上传的同时，返回路径path
				String fileUrl = FileUploadUtils.fileUploadReturnPath(
						uploads[i], uploadsFileNames[i], "资料图纸管理");
				fileUpload.setFileUrl(fileUrl);// 上传的路径
				fileUpload.setComment(comments[i]);// 上传文件的描述
				// 执行保存
				elecFileUploadDao.save(fileUpload);
				// 添加lucene，向索引库中添加数据
				 LuceneUtils.addIndex(fileUpload);
			}
		}

	}

	/**
	 * @Name: addList
	 * @Description: 使用所属单位和图纸类别查询对应的文件上传的数据信息
	 * @Author: dcfun
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2016-04-26 17:56:32
	 * @Parameters: 无
	 * @Return: String：跳转到dataChart/dataChartAddList.jsp
	 *          将dataChartAddList.jsp页面的内容，放置到dataChartAdd.jsp的Form2表单中
	 */
	public List<ElecFileUpload> findFileUploadListByCondition(
			ElecFileUpload elecFileUpload) {

		// 组织查询条件
		Map<String, Object> condition = new HashMap<String, Object>();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		if (!elecFileUpload.getProjId().equals("0")) {
			condition.put("o.projId", elecFileUpload.getProjId());
		}
		if (!elecFileUpload.getBelongTo().equals("0")) {
			condition.put("o.belongTo", elecFileUpload.getBelongTo());	
		}
		orderby.put("o.progressTime", "asc");
		
		String scalar = "o.seqID,a.ddlName,b.ddlName,o.FileName,o.FileURL,o.progressTime,o.comment";
		String From = "Elec_FileUpload o";
		ArrayList<String> innerJoin = new ArrayList<String>();
		innerJoin.add(" elec_systemddl a ON o.projID = a.ddlCode AND a.keyword='所属单位' ");
		innerJoin.add(" elec_systemddl b ON o.belongTo = b.ddlCode AND b.keyword='图纸类别' ");
		
//		List<ElecFileUpload> list = elecFileUploadDao
//				.findCollectionByConditionNoPageWithSql(condition, orderby, scalar, innerJoin, From);
		List<Object[]> list = elecFileUploadDao
				.findCollectionByConditionNoPageWithSql(condition, orderby,
						scalar, innerJoin, From);

		// 将查询的结果，封装到List<ElecFileUpload>
		List<ElecFileUpload> fileUploadList = new ArrayList<ElecFileUpload>();
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] arrays = list.get(i);
				// 组织页面显示的对象
				// o.seqID,a.ddlName,b.ddlName,o.FileName,o.FileURL,o.progressTime,o.comment
				ElecFileUpload elecFileUpload1 = new ElecFileUpload();
				elecFileUpload1
						.setSeqId(Integer.parseInt(arrays[0].toString()));
				elecFileUpload1.setProjId(arrays[1].toString());
				elecFileUpload1.setBelongTo(arrays[2].toString());
				elecFileUpload1.setFileName(arrays[3].toString());
				elecFileUpload1.setFileUrl(arrays[4].toString());
				elecFileUpload1.setProgressTime(arrays[5].toString());
				elecFileUpload1.setComment(arrays[6].toString());
				fileUploadList.add(elecFileUpload1);
			}
		}
		return fileUploadList;
//		return list;
	}

	/**  
	* @Name: findFileByID
	* @Description: 使用主键ID，查询资料图纸信息
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-08（创建日期）
	* @Parameters: Integer：主键ID
	* @Return: ElecFileUpload：资料图纸的对象
	*/
	public ElecFileUpload findFileByID(Integer fileID) {
		return elecFileUploadDao.findObjectById(fileID);
	}

	/**  
	* @Name: findFileUploadListByLuceneCondition
	* @Description: 使用lucene组织条件先查询索引库，使用主键ID查询数据库，返回List<ElecFileUpload>
	* @Author: dcfun
	* @Version: V1.00 （版本号）
	* @Create Date: 2016-04-27 10:15:15
	* @Parameters: ElecFileUpload：VO对象
	* @Return: List<ElecFileUpload>：存放文件上传的集合
	*/
	public List<ElecFileUpload> findFileUploadListByLuceneCondition(
			ElecFileUpload elecFileUpload) {
		
		List<ElecFileUpload> fileUploadList = new ArrayList<ElecFileUpload>();

		//组织查询条件，进行 lucene索引查询
		String projId = elecFileUpload.getProjId();
		String belongTo = elecFileUpload.getBelongTo();
		String queryString = elecFileUpload.getQueryString();
		List<ElecFileUpload> list = LuceneUtils.searcherIndexByCondition(projId, belongTo, queryString);
				
		//将lucene查到的ElecFileUpload 转成数据库中的ElecFileUpload
		if (list!=null && !list.isEmpty()) {
			for(ElecFileUpload fileUpload:list){
				Integer seqId = fileUpload.getSeqId();
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("seqId", seqId);
				List<ElecFileUpload> file = elecFileUploadDao.findCollectionByConditionNoPage(condition, null);
				
				//将索引库中查询的高亮，设置到查询数据库返回的ElecFileUpload对象中
				//主键查询只有惟一值
				ElecFileUpload upload = file.get(0);
				upload.setFileName(fileUpload.getFileName());
				upload.setComment(fileUpload.getComment());
				
				
				fileUploadList.addAll(file);
			}
		}
		
		return fileUploadList;
	}

	/**  
	* @Name: deleteFileUploadByID
	* @Description: 删除资料图纸管理数据
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-08（创建日期）
	* @Parameters: Integer：主键ID
	* @Return: 无
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteFileUploadByID(Integer seqId) {
		ElecFileUpload elecFileUpload = elecFileUploadDao.findObjectById(seqId);
		//路径
		String path = ServletActionContext.getServletContext().getRealPath("")+elecFileUpload.getFileUrl();
		//1：删除附件
		if(StringUtils.isNotBlank(path)){
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
		
		//2：删除数据库的数据
		elecFileUploadDao.deleteObjectByIDs(seqId);
		//3：删除索引库（让数据库的数据与索引库同步）
		LuceneUtils.deleteIndex(seqId);
	}
}
