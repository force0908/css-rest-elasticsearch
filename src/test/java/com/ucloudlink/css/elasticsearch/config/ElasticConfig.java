package com.ucloudlink.css.elasticsearch.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElasticConfig {
	private static Logger log = LogManager.getLogger(ElasticConfig.class);
	private static Properties config = null;
	
	public static Properties getInstance(String properties){
		InputStream in = null; 
		try {
			in = ClassLoader.getSystemResourceAsStream(properties);
			config = new Properties();
			config.load(in);
		} catch (IOException e) {
			log.error("--Canal Properties read error!",e);
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (Exception e) {
					log.error("--Canal InputStream read error!",e);
				}
			}
		}
		return config;
	}
	
	public static String getProperty(String key) throws Exception{
		if(config==null){
			config = getInstance("elastic.properties");
		}
		return config.getProperty(key);
	}
}