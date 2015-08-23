package com.zx.core.util;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public final class DateUtil {
    static Logger logger = Logger.getLogger(DateUtil.class.getName());
    private static DateUtil instance = null;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
    public static synchronized DateUtil getInstance() {
        if (instance == null)
            instance = new DateUtil();
        return instance;
    }

    private Calendar cal = Calendar.getInstance();
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**   
     * @return 2011-11-28 11:39:16.89
     * @Description: 获得系统时间   
     */
    public Timestamp getSystemTime() {
        Date currentDate = new Date();
        return new Timestamp(currentDate.getTime());
    }

    /**   
     * @return 2011-11-28 11:39:16
     * @Description: 获得系统时间   
     */
    public String getSystemTimeStr() {
        return sf.format(new Date());
    }
    
    /**   
     * @return 2011-11-28
     * @Description: 获取系统日期 
     */
    public String getSystemDateStr() {
        return sdFormat.format(new Date());
    }
    
    /**
     * 将指定时间转换成字符串
     * @param date
     * @param parrten
     * @return
     */
    public String dateTimeToStr(Date date, String pattern) {
	if (pattern == null || pattern.equals(""))
	    pattern = "yyyy-MM-dd HH:mm:ss";
	SimpleDateFormat format = new SimpleDateFormat(pattern);
	return format.format(date);
    }
    
    /**
     * 将指定时间转换成字符串
     * @param date
     * @return
     */
    public String dateTimeToStr(Date date) {
	return dateTimeToStr(date,"");
    }
    
    /**
     * 将日期字符串转换成日期
     * 
     * @param dateTime
     * @param parttern
     */
    public static Date strToDateTime(String dateTime, String pattern) {
	if (pattern == null || pattern.equals(""))
	    pattern = "yyyy-MM-dd HH:mm:ss";
	SimpleDateFormat format = new SimpleDateFormat(pattern);
	Date date = format.parse(dateTime, new ParsePosition(0));		
	return date;
    }
    
    /**
     * 将日期字符串转换成日期
     * 
     * @param dateTime
     */
    public static Date strToDateTime(String dateTime) {
	return strToDateTime(dateTime,"");
    }

    /**   
     * @param date1 
     * @param date2 
     * @return  
     * @Description: 日期相减,得出相隔天数
     */
    public int getDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        int days = 0;

        cal = Calendar.getInstance();

        cal = Calendar.getInstance();
        cal.setTime(date2);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 24);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        date2 = cal.getTime();

        cal.setTime(date1);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);

        if (date1.before(date2)) {
            while (date2.after(cal.getTime())) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                days++;
            }
        } else {
            while (date2.before(cal.getTime())) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
                days--;
            }
        }
        return days;
    }

    
    /**   
     * @param startDate
     * @param endDate
     * @return   
     * @Description: 字符串日期相减
     */
    public int getIntervalDays(String startDate, String endDate) {
        int intervalDays = 0;
        String startDateTemp = startDate.substring(0, 4)
                + startDate.substring(5, 7) + startDate.substring(8);

        String endDateTemp = endDate.substring(0, 4) + endDate.substring(5, 7)
                + endDate.substring(8);

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");

        Date date1 = new Date();
        Date date2 = new Date();

        try {
            date1 = formatDate.parse(startDateTemp);
            date2 = formatDate.parse(endDateTemp);
        } catch (Exception e) {
            logger.error(e);
        }

        try {
            long len = date2.getTime() - date1.getTime();
            intervalDays = (int) (len / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
            logger.error(e);
        }
        return intervalDays;
    }

    public int getHour(Date date) {
        if (date != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(Calendar.HOUR_OF_DAY);
        } else {
            return 0;
        }
    }

    public String getYear(Date date) {
        if (date != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return String.valueOf(c.get(Calendar.YEAR));
        } else {
            return "1970";
        }
    }

    public String getMonth(Date date) {
        if (date != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            if (c.get(Calendar.MONTH) < 10) {
                return "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
            } else {
                return String.valueOf(c.get(Calendar.MONTH) + 1);
            }
        } else {
            return "00";
        }
    }

    public Date getDateHotMonthBegin(Date date) {
        String dateStr = "";
        if (date.getDate() > 15) {
            dateStr = String.valueOf(date.getYear() + 1900) + "-"
                    + String.valueOf(date.getMonth() + 1) + "-01 00:00:00";
        } else {
            dateStr = String.valueOf(date.getYear() + 1900) + "-"
                    + String.valueOf(date.getMonth() - 1) + "-01 00:00:00";
        }
        return strToDateTime(dateStr);
    }

    public Date getDateHotMonthEnd(Date date) {
        if (date.getDate() > 15) {
            return date;
        } else {
            String dateStr = String.valueOf(date.getYear() + 1900) + "-"
                    + String.valueOf(date.getMonth() + 1) + "-"
                    + date.getDate() + " 00:00:00";
            return strToDateTime(dateStr);
        }

    }

    public Date getDateHotWeekBegin(Date date) {
        String dateStr = "";
        if (date.getDay() > 3) {
            dateStr = String.valueOf(date.getYear() + 1900) + "-"
                    + String.valueOf(date.getMonth() + 1) + "-"
                    + String.valueOf(date.getDate() - (date.getDay()-1))
                    + " 00:00:00";
        } else {
            dateStr = String.valueOf(date.getYear() + 1900) + "-"
                    + String.valueOf(date.getMonth() + 1) + "-"
                    + String.valueOf(date.getDate() - (6 + date.getDay()))
                    + " 00:00:00";
        }
        return strToDateTime(dateStr);
    }

    public Date getDateHotWeekEnd(Date date) {
        if (date.getDay() > 3) {
            return date;
        } else {
            String dateStr = String.valueOf(date.getYear() + 1900) + "-"
                    + String.valueOf(date.getMonth() + 1) + "-"
                    + date.getDate() + " 00:00:00";
            return strToDateTime(dateStr);
        }
    }

    public Date getDateHotDayBegin(Date date) {
        String dateStr="";
        if (date.getHours() > 12) {
            dateStr = String.valueOf(date.getYear() + 1900) + "-"
            + String.valueOf(date.getMonth() + 1) + "-"
            + date.getDate() + " 00:00:00";
        } else {
            dateStr = String.valueOf(date.getYear() + 1900) + "-"
                    + String.valueOf(date.getMonth() + 1) + "-"
                    + String.valueOf(date.getDate()-1) + " 00:00:00";
        }
        return strToDateTime(dateStr);
    }

    public Date getDateHotDayEnd(Date date) {
        if (date.getHours() > 12) {
            return date;
        } else {
            String dateStr = String.valueOf(date.getYear() + 1900) + "-"
                    + String.valueOf(date.getMonth() + 1) + "-"
                    + String.valueOf(date.getDate()-1) + " 24:00:00";
            return strToDateTime(dateStr);
        }
    }
    
    /**
     * 根据年月计算当月的天数
     * @param year
     * @param month
     * @return
     */
    public Integer getDaysByMonth(int year, int month) {   
        int days = 0;   
        Date date;
        try {
            date = format.parse(year+"年"+month+"月");
              Calendar calendar = new GregorianCalendar();
              calendar.setTime(date);
              days=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }  
    public static void main(String[]  arg){
        DateUtil dateutil = new DateUtil();
        System.out.println(dateutil.getDaysByMonth(2011,4));
    }
    
}
