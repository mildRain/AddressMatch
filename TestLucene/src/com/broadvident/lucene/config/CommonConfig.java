package com.broadvident.lucene.config;

/**
 * 配置信息JavaBean，映射到AMConfig.xml文件的&lt;CommonConfig&gt;元素，具体参见AMConfig.xml
 * @author jizd
 *
 */
public class CommonConfig {
	private String IndexBase;


	public String getIndexBase() {
		return IndexBase;
	}
	public void setIndexBase(String indexBase) {
		IndexBase = indexBase;
	}

	
	
}
