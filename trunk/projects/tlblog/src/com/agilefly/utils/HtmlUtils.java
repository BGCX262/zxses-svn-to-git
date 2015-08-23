package com.agilefly.utils;

/**
 * @author boleyn_renlei
 * @date Jun 9, 2012 3:47:19 PM
 * 
 */
public class HtmlUtils {
	/**
	 * 去掉包含html标记文本中的html标记，并截取指定大小的内容,过滤富文本框内容,高亮显示处理
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		//处理高亮显示文本
		String str = input.replaceAll("<span class='highlight'>([^<>]+)</span>", "_@$1@_");
		// 去掉所有html元素,
		str = str.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		//截取内容
		int len = str.length();
		if (len > length) {
			str = str.substring(0, length);
			str += "......";
		}
		//恢复高亮显示文本
		str = str.replaceAll("_@([^<>@]+)@_", "<span class='highlight'>$1</span>");
		
		return str;
	}

	/**
	 * 去掉包含html标记文本中的html标记,转换整个html页面为文本
	 * @param input
	 * @param length
	 * @return
	 */
	public static String getNoHtml(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return null;
		}
		// 去掉script代码,
		input = input.replaceAll("<script[^>]*>[\\d\\D]*?</script>", "");
		// 去掉style代码,
		input = input.replaceAll("<style[^>]*>[\\d\\D]*?</style>", "");
		// 去掉所有html元素,
		input = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
		;
		//去掉空格换行,
		input = input.replaceAll(" ", "");
		input = input.replaceAll("\n", "");
		input = input.replaceAll("\t", "");
		input = input.replaceAll("\r", "");
		int len = input.length();
		if (length == 0 || len <= length) {
			return input;
		} else {
			input = input.substring(0, length);
		}
		return input;
	}
}
