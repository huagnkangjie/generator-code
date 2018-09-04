/*
 * 文 件 名:  PropertyUtil.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014年4月11日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.kabasiji.generator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 此工具类会动态读取配置文件，配置文件修改后会重新加载配置项。
 * @author huang_kangjie
 * @create 2016-06-03 22:55
 **/
public class PropertyUtils
{
    private static Properties props = new Properties();
    
    //配置文件
    private static File configFile;
    
    //配置文件的最后修改时间
    private static long fileLastModify = 0L;
    
    static
    {
        try
        {
        	String configPath = System.getProperty("config.path");
        	if(configPath==null)
        	{
                configPath = System.getProperty("user.dir");
        		configPath = configPath + "/src/resources/config.properties";
        		configFile = new File(configPath);
        	}
        	else
        	{
        		configFile = new File(configPath);
        	}
            
            load();
        }
        catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	catch (ExceptionInInitializerError e)
		{
			e.printStackTrace();
		}
        
    }
    
    /**
     * 根据配置文件中的key,查找值
     * 
     * @param name
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getValue(String name)
    {
        long lm = configFile.lastModified();
        if (lm != fileLastModify)
        {
            load();
        }
        return props.getProperty(name);
    }
    

    /**
     * 重新加载配置文件配置项
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private static void load()
    {
        try
        {
            InputStream is = new FileInputStream(configFile);
            props.load(is);
            fileLastModify = configFile.lastModified();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        /*System.out.println(getValue4Array("serverIP")[0]);
        F:\previous\generator-code\src\resources\config.properties
        String[] ipArray = PropertyUtil.getValue4Array("serverIP");
        List<String> listArray = Arrays.asList(ipArray);
        System.out.println(listArray.contains("192.168.24.23"));
        */
    	System.out.println(">>>>>>>>>>>"+PropertyUtils.getValue("jdbc.url"));
    	
    }
    
}
