package com.broadvident.lucene.config;
/**
 * 配置信息JavaBean，映射到AMConfig.xml文件的&lt;IndexerConfig&gt;元素，具体参见AMConfig.xml
 * @author jizd
 * @version 1.1 2011-12-14 yuanL
 */
public class IndexerConfig {
	private String IndexName;
	private String AnalyzerClass;
	private String BreakPoint;
	private String FullSelectSql;
	private String IncrementSelectSql;
	private String PKFieldName;
	private String IndexFiledName;
	private String DataFiledName;
	private String DataSource;
	private String IndexDir;	
	
	public String getIndexDir() {
		return IndexDir;
	}
	public void setIndexDir(String indexDir) {
		IndexDir = indexDir;
	}
	public String getDataSource() {
		return DataSource;
	}
	public void setDataSource(String dataSource) {
		DataSource = dataSource;
	}
	public String getAnalyzerClass() {
		return AnalyzerClass;
	}
	public void setAnalyzerClass(String analyzerClass) {
		AnalyzerClass = analyzerClass;
	}
	public String getDataFiledName() {
		return DataFiledName;
	}
	public void setDataFiledName(String dataFiledName) {
		DataFiledName = dataFiledName;
	}
	public String getFullSelectSql() {
		return FullSelectSql;
	}
	public void setFullSelectSql(String fullSelectSql) {
		FullSelectSql = fullSelectSql;
	}
	public String getIncrementSelectSql() {
		return IncrementSelectSql;
	}
	public void setIncrementSelectSql(String incrementSelectSql) {
		IncrementSelectSql = incrementSelectSql;
	}
	public String getIndexFiledName() {
		return IndexFiledName;
	}
	public void setIndexFiledName(String indexFiledName) {
		IndexFiledName = indexFiledName;
	}
	public String getPKFieldName() {
		return PKFieldName;
	}
	public void setPKFieldName(String fieldName) {
		PKFieldName = fieldName;
	}
	public String getBreakPoint() {
		return BreakPoint;
	}
	public void setBreakPoint(String breakPoint) {
		BreakPoint = breakPoint;
	}
	public String getIndexName() {
		return IndexName;
	}
	public void setIndexName(String indexName) {
		IndexName = indexName;
	}
	
	
}
