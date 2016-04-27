package com.dcfun.elec.utils.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.util.NumericUtils;

import com.dcfun.elec.domain.ElecFileUpload;

public class FileUploadDocument {

	/**将ElecFileUpload对象转换成Document对象*/
	public static Document FileUploadToDocument(ElecFileUpload elecFileUpload){
		Document document = new Document();
		String seqId = NumericUtils.intToPrefixCoded(elecFileUpload.getSeqId());
		//主键ID
		document.add(new Field("seqId",seqId,Store.YES,Index.NOT_ANALYZED));
		//文件名
		document.add(new Field("fileName", elecFileUpload.getFileName(), Store.YES, Index.ANALYZED));
		//文件描述
		document.add(new Field("comment", elecFileUpload.getComment(), Store.YES, Index.ANALYZED));
		//所属单位
		document.add(new Field("projId",elecFileUpload.getProjId(),Store.YES,Index.NOT_ANALYZED));
		//图纸类别
		document.add(new Field("belongTo",elecFileUpload.getBelongTo(),Store.YES,Index.NOT_ANALYZED));
		return document;
	}
	
	/**将Document对象转换成ElecFileUpload对象*/
	public static ElecFileUpload documentToFileUpload(Document document){
		ElecFileUpload elecFileUpload = new ElecFileUpload();
		Integer seqId = NumericUtils.prefixCodedToInt(document.get("seqId"));
		//主键ID
		elecFileUpload.setSeqId(seqId);
		//文件名
		elecFileUpload.setFileName(document.get("fileName"));
		//文件描述
		elecFileUpload.setComment(document.get("comment"));
		//所属单位
		elecFileUpload.setProjId(document.get("projId"));
		//图纸类别
		elecFileUpload.setBelongTo(document.get("belongTo"));
		return elecFileUpload;
	}
}
