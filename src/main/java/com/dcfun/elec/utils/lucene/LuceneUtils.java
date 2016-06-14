package com.dcfun.elec.utils.lucene;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.NumericUtils;
import org.apache.lucene.util.Version;

import com.dcfun.elec.domain.ElecFileUpload;


public class LuceneUtils {

	/**向索引库中添加数据*/
	public static void addIndex(ElecFileUpload fileUpload) {
		Document doc = FileUploadDocument.FileUploadToDocument(fileUpload);
		try {
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_36, Configuration.getAnalyzer());
			IndexWriter indexWriter = new IndexWriter(Configuration.getDirectory(), indexWriterConfig);
		
			indexWriter.addDocument(doc);
			indexWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**使用查询条件，搜素索引库的数据，返回List<ElecFileUpload>*/
	public static List<ElecFileUpload> searcherIndexByCondition(String projId,
			String belongTo, String queryString) {
		
		List<ElecFileUpload> fileUploadList = new ArrayList<>();
		try {
			IndexSearcher indexSearcher = new IndexSearcher(
					IndexReader.open(Configuration.getDirectory()));

			BooleanQuery query = new BooleanQuery();
			// 组织lucene查询条件
			if (StringUtils.isNotBlank(projId)) {
				TermQuery query1 = new TermQuery(new Term("projId", projId));
				query.add(query1, Occur.MUST);
			}
			if (StringUtils.isNotBlank(belongTo)) {
				TermQuery query2 = new TermQuery(new Term("belongTo", belongTo));
				query.add(query2, Occur.MUST);
			}
			if (StringUtils.isNotBlank(queryString)) {
				QueryParser queryParser = new MultiFieldQueryParser(
						Version.LUCENE_36,
						new String[] { "fileName", "comment" },
						Configuration.getAnalyzer());
				Query query3 = queryParser.parse(queryString);
				query.add(query3, Occur.MUST);
			}
			
			//query.执行
			TopDocs topDocs = indexSearcher.search(query, 100);
			int totalHits = topDocs.totalHits;	//查询的总记录数
			ScoreDoc[] scorceDocs = topDocs.scoreDocs;
			
			/**添加设置文字高亮begin*/
			//html页面高亮显示的格式化，默认是<b></b>
			Formatter formatter = new SimpleHTMLFormatter("<font color='red'><b>","</b></font>");
			//执行查询条件，因为高亮的值就是查询条件
			Scorer scorer = new QueryScorer(query);
			Highlighter highlighter = new Highlighter(formatter,scorer);
			
			//设置文字摘要，此时摘要大小
			int fragmentSize = 10;
			Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
			highlighter.setTextFragmenter(fragmenter);
			/**添加设置文字高亮end*/
			
			if (scorceDocs!=null && scorceDocs.length>0) {
				for (ScoreDoc scoreDoc:scorceDocs) {
					int docID = scoreDoc.doc;
					Document document = indexSearcher.doc(docID);
					
					/**获取文字高亮的信息 begin*/
					//获取文字的高亮，一次只能获取一个字段高亮的结果，如果获取不到，返回null值
					//查找索引库字段为fileName
					String fileName = highlighter.getBestFragment(Configuration.getAnalyzer(), "fileName", document.get("fileName"));
					//如果null表示没有高亮的结果，如果高亮的结果，应该将原值返回
					if(StringUtils.isBlank(fileName)){
						fileName = document.get("fileName");
						if(fileName!=null && fileName.length()>fragmentSize){
							//截串，从0开始
							fileName = fileName.substring(0,fragmentSize);
						}
					}
					//将高亮后的结果放置到document中去
					document.getField("fileName").setValue(fileName);
					//查询索引库字段为comment
					String comment = highlighter.getBestFragment(Configuration.getAnalyzer(), "comment", document.get("comment"));
					//如果null表示没有高亮的结果，如果高亮的结果，应该将原值返回
					if(StringUtils.isBlank(comment)){
						comment = document.get("comment");
						if(comment!=null && comment.length()>fragmentSize){
							//截串，从0开始
							comment = comment.substring(0,fragmentSize);
						}
					}
					//将高亮后的结果放置到document中去
					document.getField("comment").setValue(comment);
					/**获取文字高亮的信息 end*/
					
					ElecFileUpload fileUpload = FileUploadDocument.documentToFileUpload(document);
					fileUploadList.add(fileUpload);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileUploadList;
	}

	/**使用ID，删除索引库*/
	public static void deleteIndex(Integer seqId) {
		//使用词条删除
		String id = NumericUtils.intToPrefixCoded(seqId);
		Term term = new Term("seqId", id);
		/**新增、修改、删除、查询都会使用分词器*/
		try {
			IndexWriterConfig indexWirterConfig = new IndexWriterConfig(Version.LUCENE_36,Configuration.getAnalyzer());
			IndexWriter indexWriter = new IndexWriter(Configuration.getDirectory(),indexWirterConfig);
			//添加数据
			indexWriter.deleteDocuments(term);
			indexWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	
}
