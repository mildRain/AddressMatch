package bocom.lucene.helloword;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;

import bocom.lucene.until.File2DocumentUntil;


public class helloWord {

	static String filePath = "D:\\WorkSpace\\TestLucene\\src\\data\\words.dic";
	//static String filePath = "D:\\WorkSpace\\TestLucene\\luceneDatasource\\testLucene.txt";
	static String indexPath ="D:\\WorkSpace\\TestLucene\\luceneIndex";
	static Analyzer analyzer = new ComplexAnalyzer();
	
	/**
	 * 创建索引
	 */
	@Test
	public static void creatIndex() throws Exception{
		
		//Analyzer analyzer = new StandardAnalyzer();
		
		Path path = Paths.get(indexPath);
		
		Directory dir = FSDirectory.open(path);
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter indexWriter = new IndexWriter(dir, iwc);
		
		Document document = File2DocumentUntil.File2Document(filePath);
		
		indexWriter.addDocument(document);
		
		indexWriter.close();
	}
	
	/**
	 * 搜索
	 * @throws ParseException 
	 */
	@Test
	public static void search() throws Exception {
		String queryString ="﻿﻿津蓟高速1111";
		//Analyzer analyzer = new StandardAnalyzer();
		//把要搜索的文本解析为 query
		QueryParser queryParser = new QueryParser("content",analyzer);
		
		Query query =  queryParser.parse(queryString);
		
		//进行查询
		Directory directory = FSDirectory.open(Paths.get(indexPath)); 
		IndexReader r = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(r);
		
		TopDocs topDocs = searcher.search(query, 100);
		System.out.println("查到的数量"+topDocs.totalHits);
		
		//打印结果
		for(ScoreDoc scoreDoc : topDocs.scoreDocs){
			
			int docsh =  scoreDoc.doc;//文档内部编号
			Document doc = searcher.doc(docsh);//根据文档编号取出相应的文档
			
			File2DocumentUntil.printDocumentInfo(doc);
			
		}
		r.close();
	}
	public static void main(String[] args) throws Exception {

		//creatIndex();
		search();
	}
	
}
