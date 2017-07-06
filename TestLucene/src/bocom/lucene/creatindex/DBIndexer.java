package bocom.lucene.creatindex;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.broadvident.framework.database.jdbc.client.JdbcUtilsHelper;
import com.broadvident.lucene.config.CommonConfig;
import com.broadvident.lucene.config.ConfigReader;
import com.broadvident.lucene.config.IndexerConfig;
import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;

public class DBIndexer {
	private static Logger log = Logger.getLogger(DBIndexer.class);
	protected static Map indexerMap = ConfigReader.getAMConfig().getIndexers();
	protected static CommonConfig cc = ConfigReader.getAMConfig().getCommonConfig();

	public void fullIndex(String indexName) {
		// 获取配置信息
		// 索引基础路径
		String indexBase = cc.getIndexBase().replaceAll("\\\\", "/");
		if (!indexBase.endsWith("/"))
			indexBase = indexBase + "/";
		// 索引路径
		String indexDir = indexBase + indexName;
		// 索引器配置信息
		IndexerConfig ic = (IndexerConfig) indexerMap.get(indexName);
		if (ic == null) {
			log.error("执行完全索引失败,原因是给定的索引名称" + indexName + "无效.");
			return;
		}

		// 构造indexwriter对象，执行索引操作
		Date start = new Date();
		try {
			// 初始化索引器配置
			Analyzer an = new ComplexAnalyzer();
			//Analyzer an = (Analyzer) Class.forName(ic.getAnalyzerClass()).newInstance();
			Directory dir = FSDirectory.open(Paths.get(indexDir));
			IndexWriterConfig iwc = new IndexWriterConfig(an);
			IndexWriter writer = new IndexWriter(dir, iwc);
		   	//IndexWriter writer = new IndexWriter(FSDirectory.open(new File(indexDir)), an , false, IndexWriter.MaxFieldLength.UNLIMITED);
			indexDBFull(writer, ic);
			log.info("正在优化索引...");
			writer.close();

			Date end = new Date();
			log.info("索引建立完毕,共耗时:" + (end.getTime() - start.getTime()) + "ms");

		} catch (Exception e) {
			log.error("执行完全索引失败,错误信息为：", e);
		}
	}

	private void indexDBFull(IndexWriter writer, IndexerConfig ic) throws Exception {
		String dsname = ic.getDataSource();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 读取索引表数据
		try {
			conn = JdbcUtilsHelper.getConnection(dsname);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(ic.getFullSelectSql());

			DBDocument.buildDocument(writer, rs, ic); // 索引执行

		} catch (Exception e) {
			log.error("执行数据库完全索引过程失败.", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

	}
}
