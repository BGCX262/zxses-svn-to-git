package com.zx.core.util;

import java.util.StringTokenizer;

import org.apache.commons.lang.xwork.StringUtils;

public class StringUtil {
	private static StringUtil instance = null;

	public static synchronized StringUtil getInstance() {
		if (instance == null)
			instance = new StringUtil();
		return instance;
	}

	public String[] split(String s, String reg) {
		StringTokenizer commaToker = new StringTokenizer(s, reg);
		String[] arr = new String[commaToker.countTokens()];
		int i = 0;
		while (commaToker.hasMoreElements()) {
			arr[i++] = commaToker.nextToken();
		}
		return arr;
	}

	public String transString(String str) {
		String[] s = { "'", "&", "[", "]" };
		for (int i = 0; i < s.length; i++) {
			str = replaceStr(str, s[i], s[i] + s[i]);
		}
		return str;
	}

	/**
	 * @param source
	 * @param oldString
	 * @param newString
	 * @return
	 * @Description: 字符串替换
	 */
	public String replaceStr(String source, String oldString, String newString) {

		StringBuffer output = new StringBuffer();
		int lengthOfSource = source.length(); // 源字符串长度
		int lengthOfOld = oldString.length(); // 老字符串长度
		int posStart = 0; // 开始搜索位置
		int pos; // 搜索到老字符串的位置

		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengthOfOld;
		}
		if (posStart < lengthOfSource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	/**
	 * 判断字符是不是数字
	 * 
	 * @isNum
	 * @Description:
	 * @boolean
	 * @Author: zhoupk
	 * @Time: Dec 19, 2011
	 */
	public boolean isNum(String msg) {
		if (java.lang.Character.isDigit(msg.charAt(0))) {
			return true;
		}
		return false;
	}

	/***************************************************************************
	 * 拆分号码段
	 * 
	 * @param str
	 * @return
	 */
	public int[] getStrNumIndex(String str) {
		int[] returnIndex = new int[3];
		String[] strArr = new String[] { "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9" };
		returnIndex[0] = StringUtils.indexOfAny(str, strArr);
		returnIndex[1] = StringUtils.lastIndexOfAny(str, strArr);
		return returnIndex;
	}

	public static void main(String[] args) {
		String str = "343434365345645";
		String[] returnStr = new String[3];
		String[] strArr = new String[] { "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9" };
		int index1 = StringUtils.indexOfAny(str, strArr);
		int index2 = StringUtils.lastIndexOfAny(str, strArr);
		returnStr[0] = str.substring(0, index1);
		returnStr[1] = str.substring(index1, index2 + 1);
		returnStr[2] = str.substring(index2 + 1);
		for (String s : returnStr) {
			System.out.println(s);
		}
	}
}
