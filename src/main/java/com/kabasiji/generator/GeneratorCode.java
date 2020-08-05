package com.kabasiji.generator;

import com.kabasiji.generator.core.Param;
import com.kabasiji.generator.core.factroy.GeneratoerFactroy;
import com.kabasiji.generator.util.DailogUtil;
import com.kabasiji.generator.util.DatasourceUtils;
import com.kabasiji.generator.util.PropertyUtils;
import com.kabasiji.generator.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 *
 * 一键无痛苦，自动生成 model、service、controller、service
 *
 * 文件生成位置destFilePath
 *
 * @author huang_kangjie
 * @create 2018-09-04 9:36
 **/
public class GeneratorCode {

     /** 生成文件的地址 */
     private static String destFilePath =        "D://generatore-code";
     /** model包地址 */
     private static String modelPackagePath =    "com.kabasiji.generator.model";
     /** controller的包地址 */
     private static String controllerClassPath = "com.kabasiji.generator.controller";
     /** service的包地址 */
     private static String serviceClassPath =    "com.kabasiji.generator.service";
     /** mapper的包地址 */
     private static String mapperClassPath =     "com.kabasiji.generator.mapper";

     private static String tableName = "";

     private static Param param = new Param();

     public static void main(String[] args) throws IOException {
          //先删除这个文件夹下的所有文件
          File file = new File(destFilePath);
          File[] files = file.listFiles();
          for(File f : files) {
               System.out.println("============> 删除文件：" + f.getName());
               f.delete();
          }
          String tableNames = PropertyUtils.getValue("table.name");
          String[] tbs = tableNames.split(",");
          for(String tname : tbs){
               tableName = tname;
               loadConfig();
               loadDataSource();
               generatorModel();
               generatorDao();
               generatorDaoImpl();
               //generatorMapper();
               //generatorService();
               //generatorController();
               //generatorIService();
               generator(GeneratoerFactroy.GeneratoerType.CONCTROLLER_2);
          }
          System.out.println("============> 文件地址： " + destFilePath);
          DailogUtil.openDailog(destFilePath);

     }

     /**
      * 加载配置文件
      */
     public static void loadConfig(){
          destFilePath = !StringUtils.isEmpty(PropertyUtils.getValue("dest.file.path")) ? PropertyUtils.getValue("dest.file.path") : destFilePath;
          modelPackagePath = !StringUtils.isEmpty(PropertyUtils.getValue("model.package.path")) ? PropertyUtils.getValue("model.package.path") : modelPackagePath;
          controllerClassPath = !StringUtils.isEmpty(PropertyUtils.getValue("controller.class.path")) ? PropertyUtils.getValue("controller.class.path") : controllerClassPath;
          serviceClassPath = !StringUtils.isEmpty(PropertyUtils.getValue("service.class.path")) ? PropertyUtils.getValue("service.class.path") : serviceClassPath;
          mapperClassPath = !StringUtils.isEmpty(PropertyUtils.getValue("mapper.class.path")) ? PropertyUtils.getValue("mapper.class.path") : mapperClassPath;

          File destFile = new File(destFilePath);
          if(!destFile.exists()) {
               destFile.mkdir();
          }

          //tableName = PropertyUtils.getValue("table.name");
          if(StringUtils.isEmpty(tableName)) {
               System.err.println("请检查配置文件table.name属性的值是否配置？");
               return;
          }
          param.setTableName(tableName);
          param.setAuther(PropertyUtils.getValue("auther"));

          param.setControllerClassPath(controllerClassPath);
          param.setDestFilePath(destFilePath);
          param.setModelPackagePath(modelPackagePath);
          param.setServiceClassPath(serviceClassPath);
          param.setMapperClass(mapperClassPath);

          if(tableName.contains("_")){
               String[] names = tableName.split("_");
               String newName = "";
               for (String name : names) {
                    newName = newName + DatasourceUtils.getInstance().initcap(name);
               }
               if(tableName.startsWith("t_")){
                    newName = newName.substring(1, newName.length()) + "Entity";
               } else {
                    newName = newName.substring(0,1).toUpperCase() + newName.substring(1, newName.length());
               }

               param.setFileName(newName);
          } else {
               param.setFileName(tableName);
          }



          System.out.println("=======================配置文件加载成功！============================");
     }

     /**
      * 加载数据源
      */
     public static void loadDataSource(){
          DatasourceUtils.load();
     }

     /**
      * 根据模板生成model
      */
     public static void generatorModel(){
          GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.MODEL).generator(param);
     }

     /**
      * 根据模板生成controller
      */
     public static void generatorController(){
          GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.CONCTROLLER).generator(param);
     }

     /**
      * 根据模板生成service
      */
     public static void generatorService(){
          GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.SERVICE).generator(param);
     }

     /**
      * 根据模板生成mapper
      */
     public static void generatorMapper(){
          GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.MAPPER).generator(param);
     }

     /**
      * 根据模板生成dao
      */
     public static void generatorDao(){
          GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.DAO).generator(param);
     }

     /**
      * 根据模板生成daoImpl
      */
     public static void generatorDaoImpl(){
          GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.DAO_IMPL).generator(param);
     }

     /**
      * 根据模板生成daoImpl
      */
     public static void generatorIService(){
          GeneratoerFactroy.getGenerator(GeneratoerFactroy.GeneratoerType.I_SERVICE).generator(param);
     }

     /**
      * 公共的生成，传入枚举
      * @param generatoerType
      */
     public static void generator(GeneratoerFactroy.GeneratoerType generatoerType){
          GeneratoerFactroy.getGenerator(generatoerType).generator(param);
     }

}
