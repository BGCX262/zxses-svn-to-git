package com.agilefly.utils;

import java.util.*;
import java.io.*;
import java.text.*;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class StringUtil {
	private static BASE64Encoder incoder = new BASE64Encoder();
	private static BASE64Decoder decoder = new BASE64Decoder();
	public static String ENCODING = "gb2312";

	public static String toBASE64Encoder(Object obj) {
		return toBASE64Encoder(getNullString(obj));
	}

	public static String toBASE64Encoder(String str) {
		try {
			return incoder.encodeBuffer(str.getBytes(ENCODING));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String toBASE64Decoder(Object obj) {
		return toBASE64Decoder(getNullString(obj));
	}

	public static String toBASE64Decoder(String str) {
		try {
			return new String(decoder.decodeBuffer(str), ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getNullString(String strTemp) {
		return (strTemp == null ? "" : strTemp.trim());
	}

	public static String getNullString(Object objTemp) {
		return (objTemp == null ? "" : objTemp.toString().trim());
	}

	public static int getNullInt(String strTemp) {
		try {
			return (getNullString(strTemp).length() == 0 ? 0 : Integer.parseInt(strTemp.trim()));
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getNullInt(Object objTemp) {
		try {
			return (objTemp == null ? 0 : Integer.parseInt(objTemp.toString().trim()));
		} catch (Exception e) {
			return 0;
		}
	}

	public static long getNullLong(String strTemp) {
		try {
			return (getNullString(strTemp).length() == 0 ? 0 : Long.parseLong(strTemp.trim()));
		} catch (Exception e) {
			return 0;
		}
	}

	public static long getNullLong(Object objTemp) {
		try {
			return (objTemp == null ? 0 : Long.parseLong(objTemp.toString().trim()));
		} catch (Exception e) {
			return 0;
		}
	}

	public static double getNullDouble(String strTemp) {
		try {
			return (getNullString(strTemp).length() == 0 ? 0 : Double.parseDouble(strTemp.trim()));
		} catch (Exception e) {
			return 0;
		}
	}

	public static double getNullDouble(Object objTemp) {
		try {
			return (objTemp == null ? 0 : Double.parseDouble(objTemp.toString().trim()));
		} catch (Exception e) {
			return 0;
		}
	}

	public static boolean getNullBoolean(String strTemp) {
		if (getNullString(strTemp).length() > 0
				&& (strTemp.toString().equalsIgnoreCase("True") || strTemp.toString().equalsIgnoreCase("1"))) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean getNullBoolean(Object objTemp) {
		return (objTemp == null ? false : ((Boolean) objTemp).booleanValue());
	}

	public static String GBK(Object tmp) {
		return GBK(getNullString(tmp));
	}

	public static String GBK(String tmp) {
		try {
			tmp = getNullString(tmp);
			byte[] b = tmp.getBytes("8859_1");
			String cvt = new String(b, "GB2312");
			return cvt;
		} catch (Exception e) {
			return "IS error?!";
		}
	}

	public static String KBG(Object tmp) {
		return KBG(getNullString(tmp));
	}

	public static String KBG(String tmp) {
		try {
			byte[] b = tmp.getBytes("GB2312");
			String cvt = new String(b, "8859_1");
			return cvt;
		} catch (Exception e) {
			return "IS error?!";
		}
	}
	
	public static String UTF8(Object tmp) {
		return UTF8(getNullString(tmp));
	}

	public static String UTF8(String tmp) {
		try {
			tmp = getNullString(tmp);
			byte[] b = tmp.getBytes("8859_1");
			String cvt = new String(b, "UTF-8");
			return cvt;
		} catch (Exception e) {
			return "IS error?!";
		}
	}

	/**
	 * 将字符串转换成Vector
	 */
	public static Vector ConverStringToVector(String strSource) {
		Vector v = new Vector();
		StringTokenizer st = new StringTokenizer(strSource, ",|");

		while (st.hasMoreTokens()) {
			v.addElement(st.nextToken());
		}

		return v;
	}// ConverStringToVector

	/**
	 * 将Vector转换成字符串
	 */
	public static String ConvertVectorToString(Vector v, String delim) {
		String sReturn = "";
		try {
			for (int i = 0; i < v.size(); i++) {
				sReturn += (String) v.elementAt(i) + delim;
			}

			sReturn = sReturn.substring(0, sReturn.length() - delim.length());
		} catch (Exception e) {
			sReturn = "";
		}

		return sReturn;
	}// ConvertVectorToString

	/**
	 * 用字符串替换字符串
	 */
	public static String replaceString(String strSource, String strFind, String strReplace) {
		int pos;
		String strTemp = strSource;
		StringBuffer sb = new StringBuffer();

		if (strTemp != null && strFind != null && strReplace != null) {
			while ((pos = strTemp.indexOf(strFind)) != -1) {
				sb.append(strTemp.substring(0, pos));
				sb.append(strReplace);
				strTemp = strTemp.substring(pos + strFind.length());
			}

			sb.append(strTemp);
			return new String(sb);
		} else {
			return strSource;
		}
	}// replaceString

	public static String replaceChar(String strSource, char chFind, String strReplace) {
		String strFind = "" + chFind;
		return replaceString(strSource, strFind, strReplace);
	}// replaceString

	public static boolean createFilePath(String path) {
		try {
			File file = new File(path);
			// System.out.println(file.exists());
			System.out.println(file.canWrite());
			if (!file.exists()) {
				System.out.println(file.mkdirs());
			}
			// System.out.println(file.toURI());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名. 纵横软件制作中心雨亦奇2003.08.01
	 * 
	 * @param s
	 *            原文件名
	 * @return 重新编码后的文件名
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param args
	 */
	public static Object getValueForServletRequest(javax.servlet.http.HttpServletRequest request, String str) {
		Object temp = request.getParameter(str);
		if (temp == null || temp.toString().trim().equalsIgnoreCase("")) {
			temp = request.getAttribute(str);
		}
		return temp;
	}

	public static String getStringValueForServletRequest(javax.servlet.http.HttpServletRequest request, String str) {
		return getNullString(getValueForServletRequest(request, str));
	}

	public static int getIntValueForServletRequest(javax.servlet.http.HttpServletRequest request, String str) {
		return getNullInt(getValueForServletRequest(request, str));
	}

	public static long getLongValueForServletRequest(javax.servlet.http.HttpServletRequest request, String str) {
		return getNullLong(getValueForServletRequest(request, str));
	}

	public static void main(String[] args) {
		try {
			/*
			 * com.job.bean.SysDictionary sys = new
			 * com.job.bean.SysDictionary();
			 * System.out.print(sys.getDictNameList().getSelectList()); String s =
			 * "1-W-008-0001-00001"; System.out.print(s.substring(13,18));
			 * HashMap httemp1 = new HashMap(); HashMap httemp2 = new HashMap();
			 * for (int i= 1 ;i<6;i++){ httemp1.put(String.valueOf(i),new
			 * HashMap()); httemp2.put(String.valueOf(i+3),"B:"+(i+3)); }
			 * Iterator iter = httemp1.keySet().iterator(); String key = "";
			 * while(iter.hasNext()){ key = iter.next().toString();
			 * System.out.println(httemp1.get(key)+"||"+httemp2.get(key)); if
			 * (httemp2.containsKey(key)){ httemp2.remove(key); } } iter =
			 * httemp2.keySet().iterator(); while(iter.hasNext()){ key =
			 * iter.next().toString();
			 * System.out.println(httemp1.get(key)+"||"+httemp2.get(key)); }
			 */
			/*
			 * ArrayList alist = new ArrayList(); for(int i=1;i<9;i++){
			 * alist.add(i+""); } ArrayList tempList = new ArrayList();
			 * tempList.addAll(alist); for(int i=0;i<tempList.size();i++){
			 * System.out.println(tempList.get(i)); }
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}