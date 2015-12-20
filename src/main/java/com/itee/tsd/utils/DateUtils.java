package com.itee.tsd.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

public class DateUtils {
	public static Date yyyyMMddToDate(String str){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date date = f.parse(str);
			return date;
		} catch(Throwable e){
			return null;
		}
	}
	
	public static Date yyyyMMddToDateHHmiss(String str){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			Date date = f.parse(str);
			return date;
		} catch(Throwable e){
			return null;
		}
	}
	
	public static String dateToyyyyMMddByDate(Date date){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try{
			String str = f.format(date);
			return str;
		} catch(Throwable e){
			return "";
		}
	}

	public static String dateToyyyyMMddHHmiss(Timestamp endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			String str = f.format(new Date(endDate.getTime()));
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static String dateToyyyyMMddHHmi(Timestamp endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			String str = f.format(new Date(endDate.getTime()));
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static String dateToyyyyMMdd(Timestamp endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try{
			String str = f.format(new Date(endDate.getTime()));
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static String dateToCyyyyMMddHHmi(Timestamp endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		try{
			String str = f.format(new Date(endDate.getTime()));
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static String dateToCyyyyMMdd(Timestamp endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日");
		try{
			String str = f.format(new Date(endDate.getTime()));
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static String dateToCMMdd(Timestamp endDate) {
		SimpleDateFormat f = new SimpleDateFormat("MM月dd日");
		try{
			String str = f.format(new Date(endDate.getTime()));
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static String dateToCyyyyMMddByDate(Date date){
		SimpleDateFormat f = new SimpleDateFormat("MM月dd日");
		try{
			String str = f.format(date);
			return str;
		} catch(Throwable e){
			return "";
		}
	}

	public static Timestamp yyyyMMddHHmmssToTimestamp(String startDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			Date d = f.parse(startDate);
			return new Timestamp(d.getTime());
		} catch(Throwable e){
			return null;
		}
	}
	
	public static Timestamp yyyyMMddHHmmToTimestamp(String startDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			Date d = f.parse(startDate);
			return new Timestamp(d.getTime());
		} catch(Throwable e){
			return null;
		}
	}
	
	public static Timestamp yyyyMMddToTimestamp(String startDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date d = f.parse(startDate);
			return new Timestamp(d.getTime());
		} catch(Throwable e){
			return null;
		}
	}

	public static Date getEndDate() {
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		return cal.getTime();
	}
	
	public static String dateToyyyyMMddHHmissWithSeparator(Timestamp endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		try{
			String str = f.format(new Date(endDate.getTime()));
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static Date getFirstDayOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return cal.getTime();
	}
	
	public static Date getLastDayOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
	}

	public static Date yyyyMMToDate(String startDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		try{
			Date d = f.parse(startDate);
			return new Timestamp(d.getTime());
		} catch(Throwable e){
			return null;
		}
	}
	
	public static String dateToyyyyMM(Date date){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		try{
			String str = f.format(date);
			return str;
		} catch(Throwable e){
			return "";
		}
	}
	
	public static boolean inRange(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
		String timeStr = dateFormat.format(now);
		if(timeStr.compareTo("080000") > 0 && timeStr.compareTo("220000") < 0){
			return true;
		} else {
			return false;
		}
	}
	
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return day <= 0 ? 7 : day;
	}

	public static Date getFirstDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setToFirstDay(calendar);
		return calendar.getTime();
	}
	
    private static final int FIRST_DAY = Calendar.MONDAY;
    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }

	public static Date addDay(Date startDate, int i) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, i);
		return calendar.getTime();
	}
	public static Date getFirstDayOfMonth(Date date, int increament) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, increament);
		calendar.set(Calendar.DAY_OF_MONTH, 1);  
		return calendar.getTime();
	}
	
	public static Integer getHour(Timestamp timestamp){
		Calendar time = Calendar.getInstance(); 
		time.setTimeInMillis(timestamp.getTime());
		return time.get(Calendar.HOUR_OF_DAY);
	}

	public static String dateToMMDD(Date date) {
		if(date == null)return "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
		String timeStr = dateFormat.format(date);
		return timeStr;
	}
	
	public static String dateToHHmi(Date date) {
		if(date == null)return "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String timeStr = dateFormat.format(date);
		return timeStr;
	}

	public static String dateMMDDHHmi(Timestamp createTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
		String timeStr = dateFormat.format(createTime);
		return timeStr;
	}
	
	public static String dateMMDDHHmiStr(Timestamp createTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH分mm秒");
		try{
			String timeStr = dateFormat.format(createTime);
		return timeStr;
		} catch(Throwable e){
			return "";
		}
	}

	public static Date yyyyMMddHHmmToDate(String createTime) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		try{
			Date d = f.parse(createTime);
			return d;
		} catch(Throwable e){
			return null;
		}
	}
	
	
	public static String getMonthStr(Calendar cal) {
		int month = cal.get(Calendar.MONTH) + 1;
		switch (month) {
		case 1:
			return "一月";
		case 2:
			return "二月";
		case 3:
			return "三月";
		case 4:
			return "四月";
		case 5:
			return "五月";
		case 6:
			return "六月";
		case 7:
			return "七月";
		case 8:
			return "八月";
		case 9:
			return "九月";
		case 10:
			return "十月";
		case 11:
			return "十一月";
		case 12:
			return "十二月";
		default:
			return "";
		}
	}
	/*
	 * Add or minus date
	 * @part: 1:year; 2:month; 3:week; 5:day; 10:hour; 12: minute; 13:second
	 * @number: positive means add; negative means minus
	 */
	public static Date addOrMinusDate(Date sourceDate, int part, int number) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(sourceDate);
		cal.add(part, number);
		Date disDate = cal.getTime();
		return disDate;
	}
}
