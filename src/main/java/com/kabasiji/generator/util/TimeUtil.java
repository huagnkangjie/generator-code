package com.kabasiji.generator.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 时间工具
 *
 * @author huang_kangjie
 * @date 2020/3/20 0020 17:46
 */
public class TimeUtil {

     /**
      * 获取当前时间 yyyy-MM-dd HH:mm:ss
      *
      * @return yyyy-MM-dd HH:mm:ss
      */
     public static String getTime() {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          return sdf.format(new Date());
     }

     /**
      * 获取 x 天 之前的时间
      * @param days 多少天
      * @return yyyy-MM-dd HH:mm:ss
      */
     public static String getTimeBefore(int days){
          LocalDateTime now = LocalDateTime.now();
          now = now.minus(days, ChronoUnit.DAYS);
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date date = Date.from( now.atZone( ZoneId.systemDefault()).toInstant());
          return sdf.format(date);
     }

     /**
      * 获取当前时间 date
      *
      * @return yyyy-MM-dd HH:mm:ss
      */
     public static String getTime(Date date) {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          return sdf.format(date);
     }

     /**
      * 获取当前时间 date
      *
      * @return yyyy-MM-dd
      */
     public static String getDateStr(Date date) {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          return sdf.format(date);
     }

     /**
      * 根据字符串获取日期
      * @param time yyyy-MM-dd HH:mm:ss
      * @return Date
      */
     public static Date getDate(String time) {
         try {
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              return sdf.parse(time);
         } catch (Exception e) {
              return new Date();
         }
     }

     /**
      * 根据字符串获取日期
      * @param time yyyy-MM-dd
      * @return Date
      */
     public static Date getDateShort(String time) {
         try {
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              return sdf.parse(time);
         } catch (Exception e) {
              return new Date();
         }
     }

     /**
      * 根据字符串获取日期
      * @return today yyyy-MM-dd
      */
     public static String getToday() {
         try {
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              return sdf.format(new Date());
         } catch (Exception e) {
              return "";
         }
     }


     /**
      * 获取月份
      *
      * @return 月份
      */
     public static int getMonth() {
          Calendar cal = Calendar.getInstance();
          return cal.get(Calendar.MONTH) + 1;
     }

     /**
      * 获取年份
      *
      * @return 年
      */
     public static int getYear() {
          Calendar cal = Calendar.getInstance();
          return cal.get(Calendar.YEAR);
     }

     /**
      * 获取年份
      *
      * @return 年
      */
     public static int getDay() {
          Calendar cal = Calendar.getInstance();
          return cal.get(Calendar.DAY_OF_MONTH);
     }




     /**
      * 比较两个时间的大小
      * 如果后者结束时间大于开始时间返回成功
      * 时间格式 yyyy-MM-dd HH:mm:ss
      *
      * @param startTime 开始时间 yyyy-MM-dd HH:mm:ss
      * @param endTime   结束时间 yyyy-MM-dd HH:mm:ss
      * @return true 结束时间大于开始时间返回成功
      */
     public static boolean compareTo(String startTime, String endTime) {
          try {
               DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               Date start = format.parse(startTime);
               Date end = format.parse(endTime);
               int compare = start.compareTo(end);
               //-1 大于 0 等于 1小于
               return compare <= 0;
          } catch (Exception e) {
               e.printStackTrace();
          }
          return false;
     }

     /**
      * 比较两个时间的大小 - 后者 大于等于 前者
      * 如果后者结束时间大于开始时间返回成功
      * 时间格式 yyyy-MM-dd
      *
      * @param startTime 开始时间 yyyy-MM-dd
      * @param endTime   结束时间 yyyy-MM-dd
      * @return true 结束时间 大于等于 开始时间返回成功
      */
     public static boolean compareDateGte(String startTime, String endTime) {
          try {
               DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               Date start = format.parse(startTime);
               Date end = format.parse(endTime);
               int compare = start.compareTo(end);
               //-1 大于 0 等于 1小于
               return compare <= 0;
          } catch (Exception e) {
               e.printStackTrace();
          }
          return false;
     }

     /**
      * 比较两个时间的大小 - 后者 大于 前者
      * 如果后者结束时间大于开始时间返回成功
      * 时间格式 yyyy-MM-dd
      *
      * @param startTime 开始时间 yyyy-MM-dd
      * @param endTime   结束时间 yyyy-MM-dd
      * @return true 结束时间 大于 开始时间返回成功
      */
     public static boolean compareDateGt(String startTime, String endTime) {
          try {
               DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               Date start = format.parse(startTime);
               Date end = format.parse(endTime);
               int compare = start.compareTo(end);
               //-1 大于 0 等于 1小于
               return compare < 0;
          } catch (Exception e) {
               e.printStackTrace();
          }
          return false;
     }

     /**
      * 比较两个时间的大小 - 后者 小于等于 前者
      * 如果后者结束时间大于开始时间返回成功
      * 时间格式 yyyy-MM-dd
      *
      * @param startTime 开始时间 yyyy-MM-dd
      * @param endTime   结束时间 yyyy-MM-dd
      * @return true 结束时间 小于等于 开始时间返回成功
      */
     public static boolean compareDateLte(String startTime, String endTime) {
          try {
               DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               Date start = format.parse(startTime);
               Date end = format.parse(endTime);
               int compare = start.compareTo(end);
               //-1 大于 0 等于 1小于
               return compare >= 0;
          } catch (Exception e) {
               e.printStackTrace();
          }
          return false;
     }

     /**
      * 比较两个时间的大小 - 后者 小于 前者
      * 如果后者结束时间大于开始时间返回成功
      * 时间格式 yyyy-MM-dd
      *
      * @param startTime 开始时间 yyyy-MM-dd
      * @param endTime   结束时间 yyyy-MM-dd
      * @return true 结束时间 小于 开始时间返回成功
      */
     public static boolean compareDateLt(String startTime, String endTime) {
          try {
               DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               Date start = format.parse(startTime);
               Date end = format.parse(endTime);
               int compare = start.compareTo(end);
               //-1 大于 0 等于 1小于
               return compare > 0;
          } catch (Exception e) {
               e.printStackTrace();
          }
          return false;
     }

     /**
      * @param start 开始时间 yyyy-MM
      * @param end   结束时间 yyyy-MM
      * @return 返回所有的月
      */
     public static List<String> getAllMonths(String start, String end) {
          List<String> list = new ArrayList<>();
          String splitSign = "-";
          start = start.substring(0, 7);
          end = end.substring(0, 7);
          //判断YYYY-MM时间格式的正则表达式
          String regex = "\\d{4}" + splitSign + "(([0][1-9])|([1][012]))";
          if (!start.matches(regex) || !end.matches(regex)) {
               return list;
          }
          if (start.compareTo(end) > 0) {
               //start大于end日期时，互换
               String temp = start;
               start = end;
               end = temp;
          }
          //从最小月份开始
          String temp = start;
          while (temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0) {
               //首先加上最小月份,接着计算下一个月份
               list.add(temp);
               String[] arr = temp.split(splitSign);
               int year = Integer.parseInt(arr[0]);
               int month = Integer.parseInt(arr[1]) + 1;
               if (month > 12) {
                    month = 1;
                    year++;
               }
               //补0操作
               if (month < 10) {
                    temp = year + splitSign + "0" + month;
               } else {
                    temp = year + splitSign + month;
               }
          }
          return list;
     }

     /**
      * 获取两个时间之间的天
      *
      * @param start 开始时间格式 yyyy-MM-dd
      * @param ends  结束时间格式 yyyy-MM-dd
      * @return 所有的时间
      */
     public static List<String> getAllDays(String start, String ends) {
          List<String> result = new ArrayList<>();
          Calendar tempStart = Calendar.getInstance();
          SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
          Date begin;
          Date end;
          try {
               begin = fmt.parse(start);
               end = fmt.parse(ends);
          } catch (ParseException e) {
               e.printStackTrace();
               return result;
          }
          tempStart.setTime(begin);
          while (begin.getTime() <= end.getTime()) {
               result.add(fmt.format(tempStart.getTime()));
               tempStart.add(Calendar.DAY_OF_YEAR, 1);
               begin = tempStart.getTime();
          }
          return result;
     }

     /**
      * 获取相同月数的天
      * @param days 天的集合
      * @param like 某一天
      * @return 相同的天
      */
     public static List<String> getAllDaysLike(List<String> days, String like) {
          List<String> rs = new ArrayList<>();
          days.forEach( day -> {
               if(day.startsWith(like)) {
                    rs.add(day);
               }
          });
          return rs;
     }

     /**
      * 获取月的天
      *
      * @param year  某年
      * @param month 某月
      * @return 所有的天
      */
     public static List<String> getMonthDay(String year, String month) {
          List<String> result = new ArrayList<>();
          Calendar tempStart = Calendar.getInstance();
          SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
          Date begin;
          Date end;
          try {
               if (null == year || "".equals(year) || null == month || "".equals(month)) {
                    return result;
               }
               begin = fmt.parse(year + "-" + month + "-" + "01");
               end = fmt.parse(year + "-" + (Integer.parseInt(month) + 1) + "-" + "01");
          } catch (ParseException e) {
               e.printStackTrace();
               return result;
          }
          tempStart.setTime(begin);
          while (begin.getTime() < end.getTime()) {
               result.add(fmt.format(tempStart.getTime()));
               tempStart.add(Calendar.DAY_OF_YEAR, 1);
               begin = tempStart.getTime();
          }
          return result;

     }
     /*
      *
      *
      */

     /**
      * 获取两个时间之间的天数
      *
      * @param start 开始时间时间格式 yyyy-MM-dd
      * @param ends  结束时间时间格式 yyyy-MM-dd
      * @return 总数
      */
     public static Integer getDaySum(String start, String ends) {
          int result = 0;
          Calendar tempStart = Calendar.getInstance();
          SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
          Date begin;
          Date end;
          try {
               begin = fmt.parse(start);
               end = fmt.parse(ends);
          } catch (ParseException e) {
               e.printStackTrace();
               e.printStackTrace();
               return 0;
          }
          tempStart.setTime(begin);
          while (begin.getTime() < end.getTime()) {
               result += 1;
               tempStart.add(Calendar.DAY_OF_YEAR, 1);
               begin = tempStart.getTime();
          }
          return result;

     }

     /**
      *
      * 计算两个日期相差的月份数
      *
      * @param date1 日期1
      * @param date2 日期2
      * @param pattern 日期1和日期2的日期格式 默认 yyyy-MM-dd HH:mm:ss
      * @return 相差的月份数
      * @throws ParseException 解析异常
      */
     public static int getMonthSpace(String date1, String date2, String pattern) throws ParseException{
          if(StringUtils.isEmpty(pattern)) {
               pattern = "yyyy-MM-dd HH:mm:ss";
          }
          SimpleDateFormat sdf=new SimpleDateFormat(pattern);
          Calendar c1=Calendar.getInstance();
          Calendar c2=Calendar.getInstance();
          c1.setTime(sdf.parse(date1));
          c2.setTime(sdf.parse(date2));
          int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
          //开始日期若小月结束日期
          if(year<0){
               year=-year;
               return year*12+c1.get(Calendar.MONTH)-c2.get(Calendar.MONTH);
          }
          return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
     }

     /**
      * 根据字符串时间获取时间戳
      * @param times yyyy-MM-dd HH:mm:ss
      * @return 精确到毫秒
      */
     public static long getTimestap(String times){
          return TimeUtil.getDate(times).getTime();
     }

     /**
      * 时间戳转时间 yyyy-MM-dd HH:mm:ss
      * @param timestamp 精确到毫秒
      * @return yyyy-MM-dd HH:mm:ss
      */
     public static String timestampToDateStr(Long timestamp) {
          Date date = new Date(timestamp);
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          return dateFormat.format(date);
     }

     /**
      * 获取当前时间 与 格林治时间的差
      * @return 小时
      */
     public static int getDiff(){
          String time = TimeZone.getDefault().getID();
          TimeZone myTimeZone =TimeZone.getTimeZone(time);
          return myTimeZone.getRawOffset()/(1000*60*60)+myTimeZone.getDSTSavings()/(1000*60*60);
     }


     /**
      * 增加小时
      * @param date 日期
      * @param hour 相差小时数
      * @return 日期
      */
     public static Date addDateHour(Date date, int hour) {
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          if (date == null) {
               return null;
          }
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          // 24小时制
          cal.add(Calendar.HOUR, hour);
          date = cal.getTime();
          return date;
     }

     /**
      * 根据时间戳获取时间
      * @param timestamp 时间戳 精确到毫秒
      * @return yyyy-MM-dd HH:mm:ss
      */
     public static String getDate(long timestamp){
          Date date = new Date(timestamp);
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          return dateFormat.format(date);
     }

     /**
      * 时间格式化 yyyyMMddHHmmss to yyyy-MM-dd HH:mm:ss
      * @param date 时间 200911120000
      * @return 返回 2009-11-12 00:00:
      */
     public static String fomart(String date){
          try {
               DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
               Date dDate = format.parse(date);
               DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               return format2.format(dDate);
          } catch (Exception e){
               e.printStackTrace();
          }
          return "";
     }


     /**
      * 替换时间中的 - ：
      * @param time 时间 yyyy-MM-dd HH:mm:ss
      * @return yyyyMMddHHmmss
      */
     public static String replaceAll(String time){
          return time.replace(":", "")
                  .replace(" ", "")
                  .replace("-", "");
     }

     /**
      * 返回一周的第几天
      * @param date 日期
      * @return 星期天  = 1； 星期1 = 2 。。。。
      */
     public static int dayOfWeek(Date date){
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(date);
          int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
          if(w < 0){
               w = 0;
          }
          return w;
     }

     public static String zero = "0";

     /**
      * 返回日的索引
      *
      * 如果是前9天则去掉0
      *
      * 日期格式为yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
      *
      * @param day 日期字符串
      * @return 索引
      */
     public static int dayOfMonth(String day){
          day = day.substring(8, 10);
          if(zero.equals(day.substring(0,1))) {
               day = day.substring(1);
          }
          return Integer.parseInt(day);
     }



}

