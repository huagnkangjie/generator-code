package com.kabasiji.generator.core.impl;

import com.kabasiji.generator.core.GeneratorService;
import com.kabasiji.generator.core.Param;
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
 * @author huang_kangjie
 * @create 2018-09-04 10:57
 **/
public class GeneratorDaoServiceImpl extends GeneratorService {

     @Override
     public void generator(Param param) {
          create(param);
     }

     @Override
     public String getFileName(Param param) {
          return "I" + param.getFileName() + "Dao.java";
     }

     @Override
     public String getTemplateName() {
          return "template_dao.ftl";
     }

     @Override
     public void print() {
          System.out.println("=======================dao生成成功！============================");
     }
}
