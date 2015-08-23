package com.agilefly.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author boleyn_renlei
 * @date Jun 19, 2012 2:06:06 AM
 * 时间处理工具类
 */
public class DateUtils {
	public static SimpleDateFormat sdf;
	private static final String defaultFormat = "yyyy-MM-dd";//"yyyy/MM/dd hh:mm:ss";

	private DateUtils() {
	}

	/**
	 * 按照默认格式格式化日期
	 * @param dtDate
	 * @return
	 * @throws Exception
	 */
	public static String format(Date dtDate) throws Exception {
		if (dtDate == null) {
			return "";
		}
		sdf = new SimpleDateFormat(defaultFormat);
		return sdf.format(dtDate);
	}

	/**
	 * 按照指定的格式格式化日期
	 * @param dtDate
	 * @param fmt
	 * @return
	 * @throws Exception
	 */
	public static String format(Date dtDate, String fmt) throws Exception {
		if (dtDate == null) {
			return "";
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat(fmt);
		return sdf1.format(dtDate);
	}

	/**
	 * 按照默认格式取得当期日期
	 * @return
	 */
	public static String getNowTime() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(defaultFormat);
		return dateFormat.format(now);
	}
	
	/**
	 * 按照指定格式取得当期日期
	 * @param fmt
	 * @return
	 */
	public static String getNowTime(String fmt) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(fmt);
		return dateFormat.format(now);
	}
	
	/**
	 * 取得当前时间 格式:5/19/2012@2:4:4:609AM
	 * @return
	 */
	public static String getTodayDate() {
		Calendar C = new GregorianCalendar();
		return C.get(Calendar.MONTH) + "/" + C.get(Calendar.DAY_OF_MONTH) + "/" + C.get(Calendar.YEAR) + "@"
				+ C.get(Calendar.HOUR) + ":" + C.get(Calendar.MINUTE) + ":" + C.get(Calendar.SECOND) + ":"
				+ C.get(Calendar.MILLISECOND) + (Calendar.AM == C.get(Calendar.AM_PM) ? "AM" : "PM");
	}

	/**
	 * 取得当前年、月、日
	 * @param input
	 * @return
	 */
	public static String getSpecialDate(String input) {
		Calendar calendar=Calendar.getInstance();   //创建一个日历对象
		calendar.setTime(new Date());             //用当前时间初始化日历时间
		String output="";
		if("week".equals(input)){
			output = String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH)-1);
		}
		if("month".equals(input)){
			output = String.valueOf(calendar.get(Calendar.MONTH)+1);
		}
		if("year".equals(input)){
			output = String.valueOf(calendar.get(Calendar.YEAR));
		}
		return  output;
	}
	
	/**
	 * 获得中文时间 格式：2012年6月19日 星期二
	 * @return
	 */
	public static String getChinaDate() {
		Calendar calendar=Calendar.getInstance();   //创建一个日历对象
        calendar.setTime(new Date());             //用当前时间初始化日历时间
        String year = String.valueOf(calendar.get(Calendar.YEAR)),
               month = String.valueOf(calendar.get(Calendar.MONTH)+1),
               day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
               week = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)-1);
        String[] weeks = new String[]{"日","一","二","三","四","五","六"};
        for (int i=0;i<weeks.length;i++){
        	if(week.equals(String.valueOf(i))){
        		week=weeks[i];
        	}
        }
        return new StringBuffer().append(year).append("年").append(month).append("月").append(day).append("日 ").append("星期").append(week).toString();
	}

	public static java.util.Date getUtilDate(String strDate) {
		try {
			if ((strDate == null) || (strDate.trim().length() == 0)) {
				return new java.util.Date();
			}
			SimpleDateFormat dtFormatter = new SimpleDateFormat(defaultFormat);
			ParsePosition pos = new ParsePosition(0);
			return dtFormatter.parse(strDate, pos);
		} catch (Exception e) {
			return null;
		}
	}

	public static java.sql.Date getSqlDate(String strDate) {
		return new java.sql.Date(getUtilDate(strDate).getTime());
	}

	/**
	 * This function is called to add days to a date
	 * @param StartDate date to be add
	 * @param NoOfDays no of day to add
	 *
	 */
	public static Date addDate(Date StartDate, int NoOfDays) throws Exception {
		Date dtReturn = null;
		try {

			GregorianCalendar gcalender = new GregorianCalendar();
			gcalender.setTime(StartDate);
			gcalender.add(gcalender.DATE, NoOfDays);
			dtReturn = gcalender.getTime();
		} catch (Exception e) {
			throw e;
		}
		return dtReturn;
	}

	/**
	 * This function is called to add days to a date
	 * @param StartDate date to be add
	 * @param FreqTyp "M" - Month, "W" - week, "D" - Day, "Y" - Year
	 * @param FreqPerd integer to add
	 *
	 */
	public static Date addDate(Date StartDate, String strFreqTyp, int intFreqPerd) throws Exception {
		Date dtReturn = null;
		try {
			if (strFreqTyp.equalsIgnoreCase("M")) {
				return addMonth(StartDate, intFreqPerd);
			} else if (strFreqTyp.equalsIgnoreCase("W")) {
				return addWeek(StartDate, intFreqPerd);
			} else if (strFreqTyp.equalsIgnoreCase("Y")) {
				return addYear(StartDate, intFreqPerd);
			} else if (strFreqTyp.equalsIgnoreCase("D")) {
				return addDate(StartDate, intFreqPerd);
			}
		} catch (Exception e) {
			throw e;
		}
		return dtReturn;
	}

	/**
	 * This function is called to add days to a date
	 * @param dtInput date to be add
	 * @param intMonth no of month to add
	 *
	 */
	public static Date addMonth(Date dtInput, int intMonth) throws Exception {
		if (dtInput == null) {
			return null;
		}
		boolean blnLastDayMth = isLstDayOfMth(dtInput);
		java.util.Date dtReturn = null;
		try {
			GregorianCalendar gcalender = new GregorianCalendar();
			gcalender.setTime(dtInput);
			gcalender.add(gcalender.MONTH, intMonth);
			dtReturn = gcalender.getTime();
			if (blnLastDayMth) {
				dtReturn = getLstDayOfMth(gcalender.getTime());
			} else {
				dtReturn = gcalender.getTime();
			}
		} catch (Exception e) {
		}
		return dtReturn;
	}

	/**
	 * This function is called to add days to a date
	 * @param StartDate date to be add
	 * @param NoOfDays no of day to add
	 *
	 */
	public static Date addWeek(Date StartDate, int NoOfWeeks) throws Exception {
		Date dtReturn = null;
		try {

			GregorianCalendar gcalender = new GregorianCalendar();
			gcalender.setTime(StartDate);
			gcalender.add(gcalender.DATE, NoOfWeeks * 7);
			dtReturn = gcalender.getTime();
		} catch (Exception e) {
			throw e;
		}
		return dtReturn;
	}

	/**
	 * This function is called to add days to a date
	 * @param StartDate date to be add
	 * @param NoOfDays no of day to add
	 *
	 */
	public static Date addYear(Date dtInput, int intYear) throws Exception {
		if (dtInput == null) {
			return null;
		}
		boolean blnLastDayMth = isLstDayOfMth(dtInput);
		java.util.Date dtReturn = null;
		try {
			GregorianCalendar gcalender = new GregorianCalendar();
			gcalender.setTime(dtInput);
			gcalender.add(gcalender.YEAR, intYear);
			if (blnLastDayMth) {
				dtReturn = getLstDayOfMth(gcalender.getTime());
			} else {
				dtReturn = gcalender.getTime();
			}
		} catch (Exception e) {
		}
		return dtReturn;
	}

	/**
	 * This function is called  get days from begin date to end date
	 * @param StartDate date to be add
	 * @param NoOfDays no of day to add
	 *
	 */
	public static int calNoOfDay(Date StartDate, Date EndDate) throws Exception {
		int intReturn = -1;
		try {
			intReturn = DateDiff("D", StartDate, EndDate);
			//int intStart = toJulian(StartDate.getYear ()+1900, StartDate.getMonth (), StartDate.getDate ());
			//int intEnd = toJulian(EndDate.getYear ()+1900, EndDate.getMonth (), EndDate.getDate ());
			//intReturn =  intEnd - intStart;

		} catch (Exception e) {
			throw e;
		}
		return intReturn;
	}

	/**
	   @return The Julian day number that begins at noon of
	   this day
	   Positive year signifies A.D., negative year B.C.
	   Remember that the year after 1 B.C. was 1 A.D.

	   A convenient reference point is that May 23, 1968 noon
	   is Julian day 2440000.

	   Julian day 0 is a Monday.

	   This algorithm is from Press et al., Numerical Recipes
	   in C, 2nd ed., Cambridge University Press 1992
	 */
	private static int toJulian(int year, int month, int day) throws Exception {
		int jul = -1;
		try {
			int jy = year;
			if (year < 0) {
				jy++;
			}
			int jm = month;
			if (month > 2) {
				jm++;
			} else {
				jy--;
				jm += 13;
			}
			jul = (int) (java.lang.Math.floor(365.25 * jy) + java.lang.Math.floor(30.6001 * jm) + day + 1720995.0);

			int IGREG = 15 + 31 * (10 + 12 * 1582);
			// Gregorian Calendar adopted Oct. 15, 1582

			if (day + 31 * (month + 12 * year) >= IGREG)
			// change over to Gregorian calendar
			{
				int ja = (int) (0.01 * jy);
				jul += 2 - ja + (int) (0.25 * ja);
			}
		} catch (Exception e) {
			throw e;
		}
		return jul;
	}

	/**
	 * This function is called to check a date range
	 * @param dateMin minimum range
	 * @param dateChk date to be checked
	 * @param dateMax maximum range
	 * dateMin < dateChk < dateMax is true
	 */
	public static boolean chkRange(Date dateChk, Date dateMin, Date dateMax) throws Exception {
		try {
			if (dateChk.before(dateMin) || dateChk.after(dateMax)) {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	/**
	 * This function is called to check a date range
	 * @param dateChk date to be checked
	 * @param dateMax maximum range
	 *
	 * @return false if dateChk > dateMax
	 */
	public static boolean chkLEMaxVal(Date dateChk, Date dateMax) throws Exception {
		try {
			if (dateChk.after(dateMax)) {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	/**
	 * This function is called to check a date range
	 * @param dateChk date to be checked
	 * @param dateMax maximum range
	 *
	 * @return false if dateChk >= dateMax
	 */
	public static boolean chkLTMaxVal(Date dateChk, Date dateMax) throws Exception {
		try {
			if (dateChk.after(dateMax) || dateChk.equals(dateMax)) {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	/**
	 * This function is called to check a date range
	 * @param dateMin minimum range
	 * @param dateChk date to be checked
	 *
	 * @return false if dateChk < dateMax
	 */
	public static boolean chkGEMinVal(Date dateChk, Date dateMin) throws Exception {
		try {
			if (dateChk.before(dateMin)) {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	/**
	 * This function is called to check a date range
	 * @param dateMin minimum range
	 * @param dateChk date to be checked
	 *
	 * @return false if dateChk <= dateMax
	 */
	public static boolean chkGTMinVal(Date dateChk, Date dateMin) throws Exception {
		try {
			if (dateChk.before(dateMin) || dateChk.equals(dateMin)) {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	/**
	 * This function is called to check a date range
	 * @param dateMin minimum range
	 * @param dateChk date to be checked
	 * @param dateMax maximum range
	 *
	 * @return true within the range, false out of the range
	 *
	 */
	public static boolean isInRange(Date dateChk, Date dateMin, Date dateMax) throws Exception {
		boolean blnReturn = false;
		try {
			String strMin = sdf.format(dateMin);
			String strMax = sdf.format(dateMax);
			String strChk = sdf.format(dateChk);
			if (dateChk.after(dateMax) || dateChk.before(dateMin)) {
				blnReturn = false;
			} else {
				blnReturn = true;
			}
		} catch (Exception e) {
			throw e;
		}
		return blnReturn;
	}

	/**
	 * This function is called to check date equal
	 * @param dateEql date to be equal
	 * @param dateChk date to be checked
	 *
	 * @return false if two date not equal
	 *
	 */
	public static boolean chkEQVal(Date dateChk, Date dateEql) throws Exception {
		if (!dateChk.equals(dateEql)) {
			return false;
		}
		return true;
	}

	//get first day in the year
	public static Date getFstDayOfYr(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		int intFstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, intFstDay);
		return cal.getTime();
	}

	//get last day in the year
	public static Date getLstDayOfYr(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		int intLstDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, intLstDay);
		return cal.getTime();
	}

	/**
	 * get all days int the year
	 * @param intYear
	 * @return
	 */
	public static int calDayPerYear(int intYear) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, intYear);
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
	}

	//whether is first day in the month
	public static boolean isFstDayOfMth(Date dt) {
		return dt.equals(getFstDayOfMth(dt));
	}

	//get first day in the month
	public static Date getFstDayOfMth(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int intFstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, intFstDay);
		return cal.getTime();
	}

	//whether is last day in the month
	public static boolean isLstDayOfMth(Date dt) {
		return dt.equals(getLstDayOfMth(dt));
	}

	//get last day in the month
	public static Date getLstDayOfMth(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int intLstDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, intLstDay);
		return cal.getTime();
	}

	/**
	 * 获得指定日期这一年的天数
	 * @param dt
	 * @return
	 */
	public static int getYearTtlDay(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获得指定日期当前月最大天数
	 * @param dt
	 * @return
	 */
	public static int getMaxDayOfMonth(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int DateDiff(String Mode, Date StartDate, Date EndDate) {
		if (EndDate.getTime() - StartDate.getTime() == 0) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();

		cal.setTime(StartDate);
		int StDtYr = cal.get(cal.YEAR);
		int StDtMth = cal.get(cal.MONTH);
		int StDtDay = cal.get(cal.DATE);

		cal.setTime(EndDate);

		int EdDtYr = cal.get(cal.YEAR);
		int EdDtMth = cal.get(cal.MONTH);
		int EdDtDay = cal.get(cal.DATE);

		int YrDiff = EdDtYr - StDtYr;
		int MthDiff = EdDtMth - StDtMth;
		int DayDiff = EdDtDay - StDtDay;

		if (Mode.equalsIgnoreCase("Y")) {
			if (YrDiff <= 0) {
				return 0;
			} else {
				if (MthDiff < 0) {
					return YrDiff - 1;
				} else if (MthDiff > 0) {
					return YrDiff;
				} else if (DayDiff < 0) {
					return YrDiff - 1;
				} else {
					return YrDiff;
				}
			}
		} else if (Mode.equalsIgnoreCase("M")) {
			int retVal = DateDiff("Y", StartDate, EndDate) * 12;

			if (YrDiff == 0) {
				if (MthDiff != 0) {
					if (DayDiff < 0) {
						return --MthDiff;
					}
					return MthDiff;
				} else {
					return 0;
				}
			}
			if (MthDiff == 0) {
				if (DayDiff >= 0) {
					return retVal;
				} else {
					return retVal += (12 - StDtMth) + EdDtMth - 1;
				}
			} else if (MthDiff < 0) {
				retVal = retVal + (12 - StDtMth) + EdDtMth;
			} else {
				retVal = retVal + MthDiff;
			}

			if (DayDiff < 0) {
				retVal = retVal - 1;
			}

			return retVal;
		} else if (Mode.equalsIgnoreCase("D")) {
			//return  Integer.parseInt( String.valueOf( ( EndDate.getTime() - StartDate.getTime() ) / ( 24 * 60 * 60 * 1000 ) ) ) + 1;
			return Integer.parseInt(String.valueOf((EndDate.getTime() - StartDate.getTime()) / (24 * 60 * 60 * 1000)));
		} else {
			return -1;
		}
	}

	/**
	 * This function is called to add days to a date
	 * @param dtInput date to be add
	 * @param intYear no of year to add
	 *
	 */
	public static java.util.Date AddYear(java.util.Date dtInput, int intYear) {
		if (dtInput == null) {
			return null;
		}
		boolean blnLastDayMth = isLstDayOfMth(dtInput);
		java.util.Date dtReturn = null;
		try {
			GregorianCalendar gcalender = new GregorianCalendar();
			gcalender.setTime(dtInput);
			gcalender.add(gcalender.YEAR, intYear);
			if (blnLastDayMth) {
				dtReturn = getLstDayOfMth(gcalender.getTime());
			} else {
				dtReturn = gcalender.getTime();
			}
		} catch (Exception e) {
		}
		return dtReturn;
	}
	
	/*public static void main(String args[])
	{
		
		System.out.println(DateUtils.getYearTtlDay(new java.util.Date()));
		
	}*/
}
