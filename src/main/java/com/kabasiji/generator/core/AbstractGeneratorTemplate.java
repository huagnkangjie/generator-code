package com.kabasiji.generator.core;

import com.kabasiji.generator.core.factroy.GeneratoerFactroy;
import com.kabasiji.generator.util.StringUtils;
import com.kabasiji.generator.util.TimeUtil;
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
 * 模板的模板类
 *
 * @author huang_kangjie
 * @date 2020/8/5 0005 14:31
 */
public abstract class AbstractGeneratorTemplate {

     public GeneratoerFactroy.GeneratoerType generatoerType;

     public abstract String getFileName(Param param);

     public abstract String getTemplateName();

     public abstract void print();

     public ThreadLocal<Map<String, Object>> dataMapLocal = ThreadLocal.withInitial(() -> new HashMap<>());



     public void create(Param param) {
          Writer out = null;
          try {
               // step1 创建freeMarker配置实例
               Configuration configuration = new Configuration();
               // step2 获取模版路径
               String rootJavaPath = new StringBuffer(System.getProperty("user.dir")).append("/").append(param.getSrcFolder()).append("/").toString();
               configuration.setDirectoryForTemplateLoading(new File(rootJavaPath + param.getTemplatePath()));
               // step3 创建数据模型
               Map<String, Object> dataMap = dataMapLocal.get();
               String fileName = param.getFileName();
               dataMap.put("mapper", fileName.substring(0, 1).toLowerCase() + fileName.substring(1, fileName.length()));
               dataMap.put("fileName", param.getFileName());
               dataMap.put("lowcaseFileName", StringUtils.lowcaseFirst(param.getFileName()));
               dataMap.put("mapperClassPath", param.getMapperClass());
               dataMap.put("serviceClassPath", param.getServiceClassPath());
               dataMap.put("controllerClassPath", param.getControllerClassPath());
               dataMap.put("modelClassPath", param.getModelPackagePath());
               dataMap.put("auther", param.getAuther());
               dataMap.put("time", TimeUtil.getDate());

               // step4 加载模版文件
               Template template = configuration.getTemplate(this.getTemplateName());
               // step5 生成数据
               File docFile = new File(param.getDestFilePath() + "\\" + this.getFileName(param));
               if(!docFile.exists()) {
                    docFile.createNewFile();
               }
               out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
               // step6 输出文件
               template.process(dataMap, out);

               this.print();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}

