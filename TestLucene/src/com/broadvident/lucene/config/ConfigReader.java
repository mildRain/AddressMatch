package com.broadvident.lucene.config;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

/**
 * 配置信息读取类，采用xStream方式将XML配置文件中的配置信息加载到JavaBeans中<br/>
 * 配置加载采用静态类方式，加载类文件时，自动完成加载过程，系统运行中不再二次加载<br/>
 * 但可通过reload方法手工重新加载
 * @author jizd
 *
 */
public class ConfigReader {
	private static AMConfig msc = null;
	private static Logger log = Logger.getLogger(ConfigReader.class);
	static{
		reload();
	}	
	
	/**
	 * 重新加载配置
	 * @return
	 */
	public static boolean reload(){
		XStream xStream = new XStream(); 
		xStream.alias("AMConfig",AMConfig.class); 
		xStream.alias("IndexerName", String.class);
		xStream.alias("IndexerConfig", IndexerConfig.class);
		try {
			msc = (AMConfig)xStream.fromXML(ConfigReader.class.getClassLoader().getResourceAsStream("AMConfig.xml"));
		} catch (Exception e) {
			log.fatal("无法加载配置文件.",e);
			return false;
		}
		return true;
	}

	/**
	 * 获取配置信息
	 * @return
	 */
	public static AMConfig getAMConfig() {
		return msc;
	}

}
