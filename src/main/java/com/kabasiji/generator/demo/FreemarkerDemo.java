package com.kabasiji.generator.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * demo
 * @author huang_kangjie
 * @create 2018-09-03 22:55
 **/
public class FreemarkerDemo {

     private static final String TEMPLATE_PATH = "com/kabasiji/generator/templates";
     private static final String CLASS_PATH = "D://test";
     private static final String SRC_Folder = "src/main/java";
     //private static final String CLASS_PATH = "com/kabasiji/generator/demo";

     public static void main(String[] args) {
          // step1 创建freeMarker配置实例
          Configuration configuration = new Configuration();
          Writer out = null;
          try {
               // step2 获取模版路径
               String rootJavaPath = new StringBuffer(System.getProperty("user.dir")).append("/").append(SRC_Folder).append("/").toString();
               configuration.setDirectoryForTemplateLoading(new File(rootJavaPath + TEMPLATE_PATH));
               // step3 创建数据模型
               Map<String, Object> dataMap = new HashMap<String, Object>();
               dataMap.put("classPath", "com.freemark.hello");
               dataMap.put("className", "User");
               dataMap.put("Id", "Id");
               dataMap.put("userName", "userName");
               dataMap.put("password","password");
               // step4 加载模版文件
               Template template = configuration.getTemplate("test.ftl");
               // step5 生成数据
               File docFile = new File(CLASS_PATH + "\\" + "User.java");
               if(!docFile.exists()) {
                    docFile.createNewFile();
               }
               out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
               // step6 输出文件
               template.process(dataMap, out);
               System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^User.java 文件创建成功 !");
          } catch (Exception e) {
               e.printStackTrace();
          } finally {
               try {
                    if (null != out) {
                         out.flush();
                    }
               } catch (Exception e2) {
                    e2.printStackTrace();
               }
          }
     }

}
