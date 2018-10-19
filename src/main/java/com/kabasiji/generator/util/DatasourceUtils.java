package com.kabasiji.generator.util;

import com.kabasiji.generator.core.Param;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据库操作工具
 * @author huang_kangjie
 * @create 2018-09-04 11:28
 **/
public class DatasourceUtils {

     private Param param = null;
     /**作者*/
     private String authorName = "Nmggy";
     /**表名 */
     private static String tablename = "";
     /**列名数组*/
     private String[] colnames;
     /**列名类型数组*/
     private String[] colTypes;
     /**每个字段的注释新*/
     private String[] remarks;
     /**列名大小数组*/
     private int[] colSizes;
     /**是否需要导入包java.util.**/
     private boolean f_util = false;
     /**是否需要导入包java.sql.**/
     private boolean f_sql = false;

     private static DatasourceUtils datasource = null;

     //数据库连接
     private static String URL = "jdbc:mysql://localhost:3306/test";
     private static String NAME = "root";
     private static String PASS = "123456";
     private static String DRIVER = "com.mysql.jdbc.Driver";

     /**
      * 加载一次配置文件
      */
     public static void load() {
          DatasourceUtils.URL = PropertyUtils.getValue("jdbc.url");
          DatasourceUtils.NAME = PropertyUtils.getValue("jdbc.username");
          DatasourceUtils.PASS = PropertyUtils.getValue("jdbc.password");
     }

     /**
      * 单利获取数据源工具
      * @return
      */
     public static synchronized DatasourceUtils getInstance(){
          if(datasource == null) {
               datasource = new DatasourceUtils();
          }
          return datasource;
     }


     /**
      * 生成实体
      * @param param
      */
     public void generatorModel(Param param) {
          this.param = param;
          //创建连接
          Connection con = null;
          //查要生成实体类的表
          String sql = "select * from " + param.getTableName();
          PreparedStatement pStemt = null;
          try {
               try {
                    Class.forName(DRIVER);
               } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
               }
               con = DriverManager.getConnection(URL, NAME, PASS);
               //获取表在字段
               pStemt = con.prepareStatement(sql);
               ResultSetMetaData rsmd = pStemt.getMetaData();
               //统计列
               int size = rsmd.getColumnCount();
               colnames = new String[size];
               colTypes = new String[size];
               remarks = new String[size];
               colSizes = new int[size];
               //获取各个字段的注释信息
               this.remarks = getReaks(con, remarks);

               for (int i = 0; i < size; i++) {
                    colnames[i] = rsmd.getColumnName(i + 1);
                    colTypes[i] = rsmd.getColumnTypeName(i + 1);

                    if (colTypes[i].equalsIgnoreCase("datetime")) {
                         f_util = true;
                    }
                    if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                         f_sql = true;
                    }
                    colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
               }

               String content = parse(colnames, colTypes, colSizes);

               try {
                    String outputPath = param.getDestFilePath() + "/" + fileName(param.getTableName()) + ".java";
                    FileWriter fw = new FileWriter(outputPath);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(content);
                    pw.flush();
                    pw.close();
               } catch (IOException e) {
                    e.printStackTrace();
               }

          } catch (SQLException e) {
               e.printStackTrace();
          } finally {
               try {
                    pStemt.close();
                    con.close();
               } catch (Exception e) {

               }
          }
     }

     /**
      * 获取表的所有注释
      * @param con
      * @param remarks
      * @return
      */
     public String[] getReaks(Connection con, String remarks[]){
          try {
               Statement stmt = con.createStatement();
               ResultSet rs = stmt.executeQuery(
                        "SELECT\n" +
                            "    TABLE_NAME,\n" +
                            "    column_name,\n" +
                            "    DATA_TYPE,\n" +
                            "    column_comment\n" +
                            "FROM \n" +
                            "    information_schema. COLUMNS\n" +
                            "WHERE\n" +
                            "    TABLE_SCHEMA = '"+getDateBase(URL)+"'\n" +
                            "and TABLE_NAME = '"+this.param.getTableName()+"' ");
               int i = 0;
               while (rs.next()) {
                    remarks[i] = rs.getString(4);
                    i++;
               }
               rs.close();
               stmt.close();
          } catch (Exception e) {
               e.printStackTrace();
          }
          return remarks;

     }

     public String fileName(String fileName){

          if(fileName.startsWith("t_") && fileName.length() > 3){
               String[] names = fileName.split("_");
               String newName = "";
               for (String name : names) {
                    newName = newName + initcap(name);
               }
               newName = newName.substring(1, newName.length()) + "Entity";
               return newName;
          }

          return fileName;
     }


     /**
      * 功能：生成实体类主体代码
      *
      * @param colnames
      * @param colTypes
      * @param colSizes
      * @return
      */
     private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
          StringBuffer sb = new StringBuffer();
          sb.append("package " + this.param.getModelPackagePath() + ";\r\n");

          sb.append("import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\r\n" +
                  "import com.fasterxml.jackson.annotation.JsonInclude;\r\n" +
                  "import com.fasterxml.jackson.annotation.JsonProperty;\r\n" +
                  "import io.swagger.annotations.ApiModel;\r\n" +
                  "import io.swagger.annotations.ApiModelProperty;\r\n" +
                  "import lombok.Data;\r\n" +
                  "\r\n" +
                  "import javax.persistence.Column;\r\n" +
                  "import javax.persistence.Id;\r\n" +
                  "import javax.persistence.Table;\r\n" +
                  "import java.util.Date;\r\n");

          //判断是否导入工具包
          if (f_util) {
               sb.append("import java.util.Date;\r\n");
          }
          if (f_sql) {
               sb.append("import java.sql.*;\r\n");
          }
          sb.append("\r\n");
          //注释部分
          sb.append("/**\r\n");
          sb.append(" *\r\n");
          sb.append(" * " + this.param.getTableName() + " 实体类\r\n");
          sb.append(" *\r\n");
          sb.append(" * @author " + this.param.getAuther() + "\r\n");
          sb.append(" * @create " + this.date() + "\r\n");
          sb.append("*/\r\n");
          //实体部分
          sb.append("@Data\r\n" +
                    "@ApiModel\r\n" +
                    "@Table(name = \""+ this.param.getTableName() +"\")\r\n" +
                    "@JsonInclude(JsonInclude.Include.NON_NULL)\r\n" +
                    "@JsonIgnoreProperties(ignoreUnknown = true)\r\n");
          sb.append("public class " + initcap(fileName(this.param.getTableName())) + "{\r\n");
          //属性
          processAllAttrs(sb);
          //get set方法
          //processAllMethod(sb);
          sb.append("}\r\n");

          return sb.toString();
     }

     public String date(){
          Date currentTime = new Date();
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
          String dateString = formatter.format(currentTime);
          return dateString;
     }

     /**
      * 功能：生成所有属性
      *
      * @param sb
      */
     private void processAllAttrs(StringBuffer sb) {

          for (int i = 0; i < colnames.length; i++) {
               String colname = colnames[i];
               String colNewName = "";
               //是否需要转换json
               boolean josnFlag = false;
               if(colname.contains("_")) {
                    String[] names = colname.split("_");
                    colNewName = names[0];
                    for(int j = 1; j < names.length; j++){
                         colNewName = colNewName + initcap(names[j]);
                    }
                    josnFlag = true;
               } else {
                    colNewName = colname;
               }
               sb.append("\t\r\n");
               if(i == 0) {
                    sb.append("\t@Id\r\n");
               }
               String remark = this.remarks[i];
               if(!StringUtils.isEmpty(remark)) {
                    sb.append("\t@ApiModelProperty(\""+ remark +"\")\r\n");
               }
               if(josnFlag){
                    sb.append("\t@JsonProperty(value = \""+ colname +"\")\r\n");
               }
               sb.append("\t@Column(name = \""+ colname +"\")\r\n");
               sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colNewName + ";\r\n");
               sb.append("\t\r\n");
          }

     }

     /**
      * 功能：生成所有方法
      *
      * @param sb
      */
     private void processAllMethod(StringBuffer sb) {

          for (int i = 0; i < colnames.length; i++) {
               sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " +
                       colnames[i] + "){\r\n");
               sb.append("\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
               sb.append("\t}\r\n");
               sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
               sb.append("\t\treturn " + colnames[i] + ";\r\n");
               sb.append("\t}\r\n");
          }

     }

     /**
      * 功能：将输入字符串的首字母改成大写
      *
      * @param str
      * @return
      */
     public String initcap(String str) {

          if(!StringUtils.isEmpty(str)) {
               char[] ch = str.toCharArray();
               if (ch[0] >= 'a' && ch[0] <= 'z') {
                    ch[0] = (char) (ch[0] - 32);
               }

               return new String(ch);
          }
          return str;

     }

     /**
      * 功能：获得列的数据类型
      * 可能没有列举完全，如果发现生成的实体有null则新增该数据类型
      * @param sqlType
      * @return
      */
     private String sqlType2JavaType(String sqlType) {

          if (sqlType.equalsIgnoreCase("bit")) {
               return "boolean";
          } else if (sqlType.equalsIgnoreCase("varbinary")) {
               return "String";
          } else if (sqlType.equalsIgnoreCase("tinyint")) {
               return "byte";
          } else if (sqlType.equalsIgnoreCase("smallint")) {
               return "short";
          } else if (sqlType.equalsIgnoreCase("int")) {
               return "Integer";
               //return "int";
          } else if (sqlType.equalsIgnoreCase("bigint")) {
               return "Long";
               //return "long";
          } else if (sqlType.equalsIgnoreCase("float")) {
               return "float";
          } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                  || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                  || sqlType.equalsIgnoreCase("smallmoney")) {
               return "double";
          } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                  || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                  || sqlType.equalsIgnoreCase("text")) {
               return "String";
          } else if (sqlType.equalsIgnoreCase("datetime")) {
               return "Date";
          } else if (sqlType.equalsIgnoreCase("image")) {
               return "Blod";
          }

          return null;
     }

     /**
      * 获取数据库的名称
      * @param url
      * @return
      */
     public String getDateBase(String url){
          return url.substring(url.lastIndexOf("/") + 1, url.indexOf("?"));
     }


}
