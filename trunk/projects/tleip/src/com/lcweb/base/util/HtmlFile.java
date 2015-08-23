package com.lcweb.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.output.FileWriterWithEncoding;

import com.lcweb.bean.pojo.HtmlFileParameter;
import com.lcweb.bean.pojo.NewsContentManage;
import com.lcweb.bean.pojo.NewsItemSmall;
import com.lcweb.commons.GlobalConst;

public class HtmlFile {

	private static HtmlFile hf = new HtmlFile();

	private HtmlFile() {
	}

	public static HtmlFile getHtmlFileInstance() {
		return hf;
	}

	public static String getHtmlFileName(NewsContentManage newsContent) {
		String fileanme = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String newsDate = format.format(newsContent.getNewsDate());
		fileanme += "_" + newsDate;
		fileanme += ".html";
		return fileanme;
	}
	
	/**
	 * 根据yxdm选择新闻生成名
	 */
	public static String getHtmlFileNameForDatabase(NewsContentManage newsContent,String yxdm) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String firstDirectory = format.format(newsContent.getNewsDate());
		StringBuffer fileanme = new StringBuffer();
		fileanme.append(firstDirectory);
		fileanme.append("_");
		fileanme.append(Long.toString((new Date()).getTime()));
		fileanme.append(".html");
		if(yxdm.equalsIgnoreCase(GlobalConst.SchoolCode)){
			return "client/htmlfiles/" + firstDirectory + "/" + fileanme;
		}
//		if(yxdm.equalsIgnoreCase(GlobalConst.NET_INSTITUTE)){
//			return "client/htmlfiles/netinstitue/" + firstDirectory + "/" + fileanme;
//		}
//		if(yxdm.equalsIgnoreCase(GlobalConst.INSTRUCTOR_BLOG)){
//			return "client/htmlfiles/instructor/" + firstDirectory + "/" + fileanme;
//		}
		else {
			return null;
		}
	}
	public static String getOutputPath(NewsContentManage newsContent, String prePath) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		String firstDirectory = format.format(newsContent.getNewsDate());
		StringBuffer outputPath = new StringBuffer();
		outputPath.append(prePath);
		outputPath.append("/");
		outputPath.append(firstDirectory);
		createFilePath(outputPath.toString());

		return outputPath.toString();
	}

	// check the output report path , if no exist and create it
	public static boolean createFilePath(String outFilePath) {
		outFilePath = StringUtil.getNullString(outFilePath);
		try {
			File outPathDir = new File(outFilePath);
			if (!outPathDir.exists()) {
				outPathDir.mkdirs();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// delete the other pdf file
	public static boolean delAllFile(String path) {
		try {
			if ((path == null) || (path.length() == 0)) {
				return false;
			}
			File file = new File(path);
			File files[] = file.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	/* 
	 * 生成首页静态模板
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void createIndexHtmfile(HtmlFileParameter indexFileParameter) throws Exception {
		String fileName = "index.html";
		String outputPath = indexFileParameter.getOutputPath();
		StringBuffer content = new StringBuffer();
		String basePath = indexFileParameter.getBasePath();
		if (outputPath == null) {
			return;
		}
		// Start read the template file
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(indexFileParameter
				.getTemplateFilePath()), GlobalConst.ENCODE_UTF8));
		String line = null;
		Map<String, Object> params = indexFileParameter.getAction();
		
//		String html_rollimage = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_ROLLIMAGE)));
		String html_indexbanner = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_INDEXBANNER)));
		String html_login = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_LOGIN)));
		String html_tabs = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_TABS)));
		String html_tabspic = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_TABSPIC)));
		String html_friendlink = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_FRIENDLINK)));
		String html_skystars = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_SKYSTARS)));
		String html_headmasters = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_HEADMASTERS)));
		String html_lib = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_LIB)));
		String html_menu = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_MENU)));
		String html_foot_info = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_FOOT_INFO)));
//		String html_contact = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_CONTACT)));
		String html_indexcontact = getDynamicPageContent(StringUtil.getNullString(params.get(GlobalConst.HTML_INDEXCONTACT)));
		
		while ((line = reader.readLine()) != null) {
			if (line.contains("${basePath}")) {
				line = StringUtil.replaceString(line, "${basePath}", basePath);
			}
//			if (line.contains("${html_rollimage}")) {
//				line = StringUtil.replaceString(line, "${html_rollimage}", html_rollimage);
//			}
			if (line.contains("${html_indexbanner}")) {
				line = StringUtil.replaceString(line, "${html_indexbanner}", html_indexbanner);
			}
			if (line.contains("${html_login}")) {
				line = StringUtil.replaceString(line, "${html_login}", html_login);
			}
			if (line.contains("${html_tabs}")) {
				line = StringUtil.replaceString(line, "${html_tabs}", html_tabs);
			}
			if (line.contains("${html_tabspic}")) {
				line = StringUtil.replaceString(line, "${html_tabspic}", html_tabspic);
			}
			if (line.contains("${html_friendlink}")) {
				line = StringUtil.replaceString(line, "${html_friendlink}", html_friendlink);
			}
			if (line.contains("${html_skystars}")) {
				line = StringUtil.replaceString(line, "${html_skystars}", html_skystars);
			}
			if (line.contains("${html_headmasters}")) {
				line = StringUtil.replaceString(line, "${html_headmasters}", html_headmasters);
			}
			if (line.contains("${html_lib}")) {
				line = StringUtil.replaceString(line, "${html_lib}", html_lib);
			}
//			if (line.contains("${html_contact}")) {
//				line = StringUtil.replaceString(line, "${html_contact}", html_contact);
//			}
			if (line.contains("${html_indexcontact}")) {
				line = StringUtil.replaceString(line, "${html_indexcontact}", html_indexcontact);
			}
			if (line.contains("${html_menu}")) {
				line = StringUtil.replaceString(line, "${html_menu}", html_menu);
			}
			if (line.contains("${html_foot_info}")) {
				line = StringUtil.replaceString(line, "${html_foot_info}", html_foot_info);
			}
			content.append(line);
		}
		reader.close();
		// Start write the html file
		FileWriterWithEncoding out = new FileWriterWithEncoding(new File(outputPath + "/" + fileName),GlobalConst.ENCODE_UTF8);
		out.write(content.toString());
		out.close();
	}

	
	
	/**
	 * 新闻静态模板
	 * @param fileParameter
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void createHtmlFileByTemplate(HtmlFileParameter fileParameter) throws Exception {
		NewsContentManage newsContent = fileParameter.getNewsContent();
		String fileName = newsContent.getHtmlFileName();
		StringBuffer content = new StringBuffer();
		if ((fileName == null) || fileName.trim().length() == 0) {
			return;
		}
		String outputpath = fileParameter.getOutputPath();
		outputpath += fileName.substring(fileName.indexOf("/"), fileName.lastIndexOf("/") + 1);
		File outPathFolder = new File(outputpath);
		if (!outPathFolder.exists()) {
			outPathFolder.mkdirs();
		}
		fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
		File file = new File(outputpath + "/" + fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioexception) {
				System.out.println("文件创建异常: " + ioexception.getMessage());
			}
		}
		// Start read the template file
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileParameter
				.getTemplateFilePath()), GlobalConst.ENCODE_UTF8));
		String line = null;
		SimpleDateFormat format = new SimpleDateFormat(GlobalConst.TIME_PATTERN);

		Map<String, Object> paraMap = fileParameter.getAction();
		String html_menu = getDynamicPageContent(StringUtil.getNullString(paraMap.get(GlobalConst.HTML_MENU)));
		String html_left = getDynamicPageContent(StringUtil.getNullString(paraMap.get(GlobalConst.HTML_LEFT)));
		String html_foot_info = getDynamicPageContent(StringUtil.getNullString(paraMap.get(GlobalConst.HTML_FOOT_INFO)));
		
		
		String basePath = fileParameter.getBasePath();
		String title = newsContent.getNewsTitle();
		String newsItemBigName = null;
		StringBuffer location = new StringBuffer();
		List list = new ArrayList();
		list.addAll(newsContent.getNewsItemSmalls());
		if (list.size() > 0) {
			NewsItemSmall newsItemSmall = (NewsItemSmall) list.get(0);
			newsItemBigName = newsItemSmall.getNewsItemBig().getClassName();
			location.append("当前位置：<a href=\"" + basePath + "\">首页</a> >>");
			location.append(newsItemBigName);
		} 
		while ((line = reader.readLine()) != null) {
			if (line.contains("${id}")) {
				line = StringUtil.replaceString(line, "${id}", newsContent.getNewsId().toString());
			}
			if (line.contains("${newsTitle}")) {
				line = StringUtil.replaceString(line, "${newsTitle}", newsContent.getNewsTitle());
			}
			if (line.contains("${newsWriter}")) {
				line = StringUtil.replaceString(line, "${newsWriter}", newsContent.getNewsWriter());
			}
			if (line.contains("${visitCount}")) {
				line = StringUtil.replaceString(line, "${visitCount}", newsContent.getVisitCount().toString());
			}
			if (line.contains("${content}")) {
				line = StringUtil.replaceString(line, "${content}", newsContent.getNewsContent());
			}
			if (line.contains("${newsDate}")) {
				line = StringUtil.replaceString(line, "${newsDate}", format.format(newsContent.getNewsDate()));
			}
			if (line.contains("${newsSource}")) {
				line = StringUtil.replaceString(line, "${newsSource}", newsContent.getNewsSource());
			}
			if (line.contains("${basePath}")) {
				line = StringUtil.replaceString(line, "${basePath}", basePath);
			}
			if (line.contains("${charset}")) {
				line = StringUtil.replaceString(line, "${charset}", GlobalConst.ENCODE_UTF8);
			}
			if (line.contains("${location}")) {
				line = StringUtil.replaceString(line, "${location}", location.toString());
			}
			if (line.contains("${title}")) {
				line = StringUtil.replaceString(line, "${title}", title);
			}
						
			//模块标签
			if (line.contains("${html_menu}")) {
				line = StringUtil.replaceString(line, "${html_menu}", html_menu);
			}
			if (line.contains("${html_left}")) {
				line = StringUtil.replaceString(line, "${html_left}", html_left);
			}
			if (line.contains("${html_foot_info}")) {
				line = StringUtil.replaceString(line, "${html_foot_info}", html_foot_info);
			}
			content.append(line);
		}
		reader.close();
		FileWriterWithEncoding out = new FileWriterWithEncoding(new File(outputpath + "/" + fileName),GlobalConst.ENCODE_UTF8);
		out.write(content.toString());
		out.close();
	}
	
	/**
	 * <p>
	 * this method is get the dynamicPageContent ny action path or servlet path
	 * </p>
	 * 
	 * @param action
	 *            "http://localhost:8080/lcweb/view/newsClient.do?method=queryMenuContent"
	 * @return the page content of String
	 */
	public static String getDynamicPageContent(String action) {
		URL url = null;
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferReader = null;
		InputStream inputStream = null;
		try {
			url = new URL(action); // pass the action to get the Url
			inputStream = url.openStream(); // Open this url Stream;
			bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String lineContent = null;
			while ((lineContent = bufferReader.readLine()) != null) {
				buffer.append(lineContent);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(bufferReader, inputStream);
		}
		return buffer.toString();
	}

	public static boolean deleteHtmlFile(String fileName) throws Exception {
		boolean isDeleteSuccess = false;
		File file = new File(fileName);
		if (file.exists()) {
			isDeleteSuccess = file.delete();
		}
		return isDeleteSuccess;
	}
	
	public static boolean RD(String FolderPath){
		//删除目录
		File f = new File(FolderPath);//定义文件路径        
		if(f.exists() && f.isDirectory()){//判断是文件还是目录
			
		    if(f.listFiles().length==0)
		    {//若目录下没有文件则直接删除
		    	try{
		       		f.delete();
					return true;
				}
				catch(Exception e){
					return false;
				}
		    }
		    else
		    {//若有则把文件放进数组，并判断是否有下级目录
		        File delFile[]=f.listFiles();
		        int i =f.listFiles().length;
		        for(int j=0;j<i;j++)
		        {
		            if(delFile[j].isDirectory())
		            {
		            	RD(delFile[j].getAbsolutePath());//递归调用del方法并取得子目录路径
		            }
		            delFile[j].delete();//删除文件
		        }
		    }
		    RD(FolderPath);//递归调用
		 
		}

		return true;
	}

	private static void closeStream(BufferedReader bufferReader, InputStream inputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
				if (bufferReader != null) {
					bufferReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}