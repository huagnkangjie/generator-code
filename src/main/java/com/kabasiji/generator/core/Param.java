package com.kabasiji.generator.core;

/**
 * @author huang_kangjie
 * @create 2018-09-04 11:12
 **/
public class Param {

     private String destFilePath;
     private String modelPackagePath;
     private String controllerClassPath;
     private String serviceClassPath;
     private String mapperClass;
     private String tableName;
     private String auther;
     private String fileName;
     private String srcFolder = "src/main/java";
     private String templatePath = "com/kabasiji/generator/templates";

     public String getTemplatePath() {
          return templatePath;
     }

     public void setTemplatePath(String templatePath) {
          this.templatePath = templatePath;
     }

     public String getSrcFolder() {
          return srcFolder;
     }

     public void setSrcFolder(String srcFolder) {
          this.srcFolder = srcFolder;
     }

     public String getFileName() {
          return fileName;
     }

     public void setFileName(String fileName) {
          this.fileName = fileName;
     }

     public String getAuther() {
          return auther;
     }

     public void setAuther(String auther) {
          this.auther = auther;
     }

     public String getDestFilePath() {
          return destFilePath;
     }

     public void setDestFilePath(String destFilePath) {
          this.destFilePath = destFilePath;
     }

     public String getModelPackagePath() {
          return modelPackagePath;
     }

     public void setModelPackagePath(String modelPackagePath) {
          this.modelPackagePath = modelPackagePath;
     }

     public String getControllerClassPath() {
          return controllerClassPath;
     }

     public void setControllerClassPath(String controllerClassPath) {
          this.controllerClassPath = controllerClassPath;
     }

     public String getServiceClassPath() {
          return serviceClassPath;
     }

     public void setServiceClassPath(String serviceClassPath) {
          this.serviceClassPath = serviceClassPath;
     }

     public String getMapperClass() {
          return mapperClass;
     }

     public void setMapperClass(String mapperClass) {
          this.mapperClass = mapperClass;
     }

     public String getTableName() {
          return tableName;
     }

     public void setTableName(String tableName) {
          this.tableName = tableName;
     }
}
