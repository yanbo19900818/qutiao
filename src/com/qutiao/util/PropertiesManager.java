package com.qutiao.util;

import java.util.Properties;

import org.apache.log4j.Logger;

public final class PropertiesManager {
	private static final Logger logger = Logger
			.getLogger(PropertiesManager.class);

	public static String getValue(String name) {
		Properties prop = new Properties();
		try {
			prop.load(PropertiesManager.class.getResourceAsStream("/cms.properties"));
		} catch (Exception e) {
			logger.error("read excelPath.properties error!!!", e);
		}
		String propValue = (String) prop.get(name);
		logger.info(propValue);

		if (propValue == null || propValue.trim().length() == 0) {
			logger.error("getFilePath url not empty!");
		}
		return propValue;
	}

	/**
     * 根据文件名和参数名获取参数值
     * @param filename
     * @param name
     * @return
     */
    public static String getValue(String filename,String name) {
        Properties prop = new Properties();
        try {
            prop.load(PropertiesManager.class.getResourceAsStream(filename));
        } catch (Exception e) {
            logger.error("read "+filename+" error!!!", e);
        }
        String propValue = (String) prop.get(name);
        // propValue=PropertiesManager.subString(propValue);
        logger.info(propValue);

        if (propValue == null || propValue.trim().length() == 0) {
            logger.error("getFilePath "+filename+" value "+name+" empty!");
        }
        return propValue;
    }
  public static void main(String []args){
	 //String fileName=PropertiesManager.getValue("FileName");
	 String fileName=PropertiesManager.getValue("/site.properties","content");
	 System.out.println(fileName);
	 
  }
}
