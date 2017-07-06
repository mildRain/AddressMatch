package bocom.lucene.searcher;

import java.io.File;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;

public class Startup {

	public static void main(String[] args) throws Exception {

	        Directory dir = FSDirectory.open(Paths.get("d:/tmp/jhglz1"));
	        Dictionary dic = Dictionary.getInstance("d:/tmp/jhglz1");
	        Analyzer a = new ComplexAnalyzer(dic);
	        IndexReader reader = DirectoryReader.open(dir);
		   
		   //Directory dir = FSDirectory.open(new File("d:/tmp/jhglz1"));
		   //IndexReader reader = IndexReader.open(dir1,true);
		   
		   
	        IndexSearcher is = new IndexSearcher(reader);
	        QueryParser parser = new QueryParser("contents", a);
	        Query query = parser.parse("津蓟高速52");
	        
	        TopDocs topDocs = is.search(query, 1000);
	        System.out.println("总共匹配多少个：" + topDocs.totalHits);
	        ScoreDoc[] hits = topDocs.scoreDocs;
	        // 应该与topDocs.totalHits相同
	        System.out.println("多少条数据：" + hits.length);
	        for (ScoreDoc scoreDoc : hits) {
	            System.out.println("匹配得分：" + scoreDoc.score);
	            System.out.println("文档索引ID：" + scoreDoc.doc);
	            Document document = is.doc(scoreDoc.doc);
	            System.out.println(document.get("info"));
	        }
	        reader.close();
	      //  dir.close();
	}

}
