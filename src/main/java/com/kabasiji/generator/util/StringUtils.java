package com.kabasiji.generator.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huang_kangjie
 * @create 2018-09-04 10:25
 **/
public class StringUtils {

     public static boolean isEmpty(Object str) {
          return str == null || "".equals(str);
     }

     public static String getDate(){
          Date currentTime = new Date();
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
          String dateString = formatter.format(currentTime);
          return dateString;
     }

     public static String lowcaseFirst(String str){
          return str.substring(0, 1).toLowerCase() + str.substring(1);
     }

}
