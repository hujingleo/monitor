package com.monitor.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	
	public static Date getFirstDayOfMonth(Date date){
		if(date==null)
			date =new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	public static Date getEndDayOfMonth(Date date){
		if(date==null)
			date =new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.add(Calendar.MONDAY, 1);
		cal.add(Calendar.DATE, -1);
		
		return cal.getTime();
	}
	public static Date getStartDate(Date date){
		if(date==null)
			date=new Date(0,0,1);	//从1900年1月1日开始
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	
	public static Date getEndDate(Date date){
		if(date==null)
			date=new Date();	//以当前时间做为结束时间
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		return cal.getTime();
	}
	
	public static String getCurrentDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now =new Date();
		return sdf.format(now);
	}
	public static String getCurrentDate(String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date now =new Date();
		return sdf.format(now);
	}
	
	
	public static String getYesterday(){
		return getYesterday("yyyy-MM-dd HH:mm:ss");
	}
	public static String getYesterday(String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		
		return sdf.format(cal.getTime());
	}
	public static String getFormatDate(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		
		return sdf.format(date);
	}
	/**
	 * 获得年月日字符串，如141030
	 * @return
	 */
	public static String getYearMonthDay(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
		Date now =new Date();
		return sdf.format(now);
	}
	
	public static Date parseDate(String date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(date);
	}
	public static Date parseDate(String date,String format) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(date);
	}
	
	/**
	 * 返回该月的最大日期字符串
	 * @param yearMonth 一个月份（2016-10）
	 * @return
	 */
	public static String getMaxYearMonthDay(String yearMonth){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		   String dateStr = null;
		   Date date = new Date();
		   try {
		    date = format.parse(yearMonth);	    
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);	    
		    calendar.set(calendar.getTime().getYear(), calendar.getTime().getMonth(),1);
		    		    
		     int maxDay = calendar.getActualMaximum(Calendar.DATE);
		    
		     dateStr = yearMonth + "-" + maxDay;
		     
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
		  return dateStr;
	}
	
	/**
	 * 获得指定日期的后一天
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }
	
	/**
	 * 获得指定日期的前一天
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
                .getTime());
        return dayBefore;
    }
}
