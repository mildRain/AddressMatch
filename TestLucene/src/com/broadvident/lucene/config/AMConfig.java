package com.broadvident.lucene.config;

import java.util.Map;

/**
 * 配置信息JavaBean，映射到AMConfig.xml文件的&lt;AMConfig&gt;顶层元素，具体参见AMConfig.xml
 * @author jizd
 *
 */
public class AMConfig {
	
	private CommonConfig CommonConfig;
	private Map Indexers;
	

	public CommonConfig getCommonConfig() {
		return CommonConfig;
	}

	public void setCommonConfig(CommonConfig commonConfig) {
		CommonConfig = commonConfig;
	}

	public Map getIndexers() {
		return Indexers;
	}

	public void setIndexers(Map indexers) {
		Indexers = indexers;
	}

	
}
