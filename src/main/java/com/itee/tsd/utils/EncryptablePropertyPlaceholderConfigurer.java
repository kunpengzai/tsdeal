package com.itee.tsd.utils;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {  
  
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)  
        throws BeansException {
    		String key = Constants.DES_KEY;
            try {  
                String username = props.getProperty("username");  
                if (username != null) {  
                	String realUserName = Des3Util.decode(key , username);
                    props.setProperty("username", realUserName);  
                }  
                  
                String password = props.getProperty("password");  
                if (password != null) {
                	String realPassword = Des3Util.decode(key , password);
                    props.setProperty("password", realPassword);
                }  
                  
//                String url = props.getProperty("jdbc.url");  
//                if (url != null) {  
//                    props.setProperty("jdbc.url", Des3Util.decode(key , url));  
//                }  
//                  
//                String driverClassName = props.getProperty("jdbc.driverClassName");  
//                if(driverClassName != null){  
//                    props.setProperty("jdbc.driverClassName", Des3Util.decode(key , driverClassName));  
//                }  
                super.processProperties(beanFactory, props);
                
            } catch (Exception e) {  
                e.printStackTrace();  
                throw new BeanInitializationException(e.getMessage());  
            }  
        }
}