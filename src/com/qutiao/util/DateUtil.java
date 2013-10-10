package com.qutiao.util;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;






public final class DateUtil {

	private static DateFormat dateFormatFour = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    
    public static String formatTimeStamp(Date date) {

        return dateFormatFour.format(date);
    }
    
    public static String getCurrentTime() {
    	Date date=new Date(); 
		String time=dateFormatFour.format(date); 
		return time;
    }
    public static String formatTodayTimeStamp() {
        return dateFormatFour.format(new Date());
    }
	public static String dateToString(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
    /**
	 * 得到字符串中的年份
	 * @param str
	 */
	  public static String getYear(String str){   
		Calendar ca = Calendar.getInstance();   
		   try {
			ca.setTime(stringToDate(str));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		  // SimpleDateFormat simpledate = new SimpleDateFormat("yyyyMM");   
		  // String date = simpledate.format(ca.getTime());   
		   String year   =String.valueOf(ca.get(Calendar.YEAR));   
		 //  String month = String.valueOf(ca.get(Calendar.MONTH)+1);   
		  // String day    = String.valueOf(ca.get(Calendar.DAY_OF_MONTH));   
		  // System.out.println(date+"||"+year+"||"+month+"||"+day);   
		   return year;
		} 
	/**
	 * 得到字符串中的年份和日期
	 * @param args
	 */
	  public static String getYearMonth(String str){ 
		Calendar ca = Calendar.getInstance();   
		   try {
			ca.setTime(stringToDate(str));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		   SimpleDateFormat simpledate = new SimpleDateFormat("yyyyMM");   
		   String date = simpledate.format(ca.getTime());   
		   return date;
	}
	  
		/**
		 * 得到当月的第一天
		 * param str
		 * @return
		 */
		public static String getFirstDate(String str){ 
			 Calendar   cal   =   Calendar.getInstance(); 
			 try {
					cal.setTime(stringToDate(str));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 int year = cal.get(Calendar.YEAR);
			 int month = cal.get(Calendar.MONTH) + 1;
			 cal.set(cal.YEAR,   year); 
			 cal.set(cal.MONTH,   month); 
			 int firstDate=cal.getActualMinimum(cal.DAY_OF_MONTH); 
			 String minDate=year+"-"+month+"-"+firstDate;
			 return minDate;
			
		}
		
		/**
		 * 得到当月的最后一天  
		 * param str  
		 * @return
		 */
		public static String getLastDate(String str){ 
			 Calendar   cal   =   Calendar.getInstance(); 
			 try {
				cal.setTime(stringToDate(str));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 int year = cal.get(Calendar.YEAR);
			 int month = cal.get(Calendar.MONTH) + 1;
			 cal.set(cal.YEAR,   year); 
			 cal.add(Calendar.DATE, 1); 

			 int firstDate=cal.getActualMaximum(Calendar.DATE);   
			 String maxDate=year+"-"+month+"-"+firstDate;
			 return maxDate;
			
		}
		
		public static Date stringToDate(String str) {
			return stringToDate(str, "yyyy-MM-dd");
		}
		
		public static Date stringToDate(String str, String format) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				return sdf.parse(str);
			} catch (ParseException e) {
			throw new RuntimeException(e);
			}
		}
		
}
