package com.broadvident.lucene.config;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

/**
 * ������Ϣ��ȡ�࣬����xStream��ʽ��XML�����ļ��е�������Ϣ���ص�JavaBeans��<br/>
 * ���ü��ز��þ�̬�෽ʽ���������ļ�ʱ���Զ���ɼ��ع��̣�ϵͳ�����в��ٶ��μ���<br/>
 * ����ͨ��reload�����ֹ����¼���
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
	 * ���¼�������
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
			log.fatal("�޷����������ļ�.",e);
			return false;
		}
		return true;
	}

	/**
	 * ��ȡ������Ϣ
	 * @return
	 */
	public static AMConfig getAMConfig() {
		return msc;
	}

}
