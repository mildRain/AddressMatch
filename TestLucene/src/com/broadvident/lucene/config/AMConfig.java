package com.broadvident.lucene.config;

import java.util.Map;

/**
 * ������ϢJavaBean��ӳ�䵽AMConfig.xml�ļ���&lt;AMConfig&gt;����Ԫ�أ�����μ�AMConfig.xml
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
