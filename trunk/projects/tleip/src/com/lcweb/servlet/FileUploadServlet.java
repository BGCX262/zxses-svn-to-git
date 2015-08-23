package com.lcweb.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lcweb.base.util.ImageUtils;
import com.lcweb.commons.GlobalConst;

public class FileUploadServlet extends HttpServlet {

	private static final String SLASH = "/";
	private static final long serialVersionUID = -4456505354515970555L;
	 private static String baseDir;// 上传文件的相对目录
	 private static String realBaseDir;// 上传文件的绝对目录
	private static Hashtable<String, Object> allowedExtensions;// 允许的上传文件扩展名
	private static Hashtable<String, Object> deniedExtensions;// 阻止的上传文件扩展名
	private static SimpleDateFormat fileFormatter;// 文件命名格式:yyyyMMddHHmmssSSS

	/**
	 * Servlet初始化方法
	 */
	public void init() throws ServletException {
		// 格式化目录和文件命名方式
		fileFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 baseDir = GlobalConst.UPLOAD_PATH;
		 realBaseDir = getServletContext().getRealPath(baseDir);
		 
		File baseFile = new File(realBaseDir);
		if (!baseFile.exists()) {
			baseFile.mkdirs();
		}
		// 实例化允许的扩展名和阻止的扩展名
		allowedExtensions = new Hashtable<String, Object>(3);
		deniedExtensions = new Hashtable<String, Object>(3);
		// 从web.xml中读取配置信息
		allowedExtensions.put("File", stringToArrayList(getInitParameter("AllowedExtensionsFile")));
		deniedExtensions.put("File", stringToArrayList(getInitParameter("DeniedExtensionsFile")));
		allowedExtensions.put("Image", stringToArrayList(getInitParameter("AllowedExtensionsImage")));
		deniedExtensions.put("Image", stringToArrayList(getInitParameter("DeniedExtensionsImage")));
		allowedExtensions.put("Flash", stringToArrayList(getInitParameter("AllowedExtensionsFlash")));
		deniedExtensions.put("Flash", stringToArrayList(getInitParameter("DeniedExtensionsFlash")));
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// 文件名和文件真实路径
		@SuppressWarnings("unused")
		String fileUrl = "";
			// 使用Apache Common组件中的fileupload进行文件上传
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List items = upload.parseRequest(request);
				Map fields = new HashMap();
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						fields.put(item.getFieldName(), item.getString());
					} else {
						fields.put(item.getFieldName(), item);
					}
				}
				// 从请求参数中获取上传文件的类型：File/Image/Flash
				String imgAddress = "";
				String faceImg = "";
				String fileType = "";
				String announceType = fields.get("announceType").toString();
				if ("0".equals(announceType)) {
					imgAddress = "imgNewsAddress0";
					faceImg = "faceImg0";
					fileType = "Image";
				}
				if ("1".equals(announceType)) {
					imgAddress = "imgNewsAddress1";
					faceImg = "faceImg1";
					fileType = "Image";
				}
				if ("2".equals(announceType)) {
					imgAddress = "file_annex_url";
					fileType = "File";
				}
				FileItem uploadFile = (FileItem) fields.get("fname");
				String fileName = uploadFile.getName();
				String ext = getExtension(fileName);

				if (fileName == null || "".equals(fileName)) {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('文件不能为空!');");
					out.println("history.back();");
					out.println("</script>");
				}

				fileName = fileName.replace('\\', '/');
				fileName = fileName.replace(" ", "");
				String[] pathParts = fileName.split(SLASH);
				fileName = pathParts[pathParts.length - 1];
				fileName = fileFormatter.format(new Date()) + "." + ext;

				// 设定上传文件路径
//				StringBuffer directionPath = new StringBuffer();
//				directionPath.append(realBaseDir);
				// 获得web应用的上传路径
//				String currentPath = realBaseDir;
				String currentDirPath = realBaseDir;
				
				// 判断文件夹是否存在，不存在则创建
				File currentDirFolder = new File(currentDirPath);
				if (!currentDirFolder.exists()) {
					currentDirFolder.mkdirs();
				}
				File file = new File(currentDirPath, fileName);
//				String nameWithoutExt = getNameWithoutExtension(fileName);

				if (extIsAllowed(fileType, ext)) {
//					int counter = 1;
//					while (file.exists()) {
//						newName = nameWithoutExt + "_" + counter + "." + ext;
//						file = new File(currentDirPath, newName);
//						fileUrl = currentPath + newName;
//						counter++;
//					}
//					if (counter == 1) {
//						fileUrl = currentPath + fileName;
//					}
					uploadFile.write(file);
				 

					if ("jpg".equals(getExtension(fileName)) || "jpeg".equals(getExtension(fileName))) {
						Image image = ImageIO.read(file);
						int imageWidth = image.getWidth(null);
						int imageHeight = image.getHeight(null);
						int maxWidth = 1024;
						int maxHeight = 768;
						if (imageWidth > maxWidth || imageHeight > maxHeight) {
							BufferedImage resizeImage = ImageUtils.resizeImage(image, 0, maxWidth, maxHeight);
							ImageUtils.saveImage(resizeImage, file.getAbsolutePath(), 0);
						}
					}
					
					String webPath = request.getScheme() + "://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath();
					String imageShowPath = webPath +"/"+ baseDir;
					
					if ("Image".equalsIgnoreCase(fileType)) {
						out.println("<script type=\"text/javascript\">");
						out.println("window.parent.document.getElementById('" + imgAddress + "').value = '" + fileName
								+ "';");
						out.println("window.parent.document.getElementById('" + faceImg + "').src='" + imageShowPath + fileName + "';");
						out.println("</script>");
					}
					if ("File".equalsIgnoreCase(fileType)) {
						out.println("<script type=\"text/javascript\">");
						out.println("window.parent.document.getElementById('" + imgAddress
								+ "').options[0] = new Option('','');");
						out.println("window.parent.document.getElementById('" + imgAddress + "').options[0].text = '"
								+ fileName + "';");
						out.println("window.parent.document.getElementById('" + imgAddress + "').options[0].value = '"
								+ fileName + "';");
						out.println("</script>");
					}
				} else {
					out.println("<script language='javascript' type='text/javascript'>");
					out.println("alert('无效的文件类型!');");
					out.println("history.back();");
					out.println("</script>");
					out.flush();
					out.close();
				}
			} catch (Exception ex) {
			} finally {
				String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath();
				out.println("<script src=\"" + basePath + "/tools/artdialog/js/artDialog.js\">");
				out.println("</script>");
				out.println("<script src=\"" + basePath + "/tools/artdialog/js/iframeTools.js\">");
				out.println("</script>");
				out.println("<script type=\"text/javascript\" >");
				out.println("art.dialog.close();");
				out.println("</script>");
			}
		out.flush();
		out.close();
	}

	/**
	 * 获取文件名的方法
	 */
//	private static String getNameWithoutExtension(String fileName) {
//		return fileName.substring(0, fileName.lastIndexOf("."));
//	}

	/**
	 * 获取扩展名的方法
	 */
	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 字符串像ArrayList转化的方法
	 */
	@SuppressWarnings("unchecked")
	private ArrayList stringToArrayList(String str) {
		String[] strArr = str.split("\\|");
		ArrayList tmp = new ArrayList();
		if (str.length() > 0) {
			for (int i = 0; i < strArr.length; ++i) {
				tmp.add(strArr[i].toLowerCase());
			}
		}
		return tmp;
	}

	/**
	 * 判断扩展名是否允许的方法
	 */
	@SuppressWarnings("unchecked")
	private boolean extIsAllowed(String fileType, String ext) {
		ext = ext.toLowerCase();
		ArrayList allowList = (ArrayList) allowedExtensions.get(fileType);
		ArrayList denyList = (ArrayList) deniedExtensions.get(fileType);
		if (allowList.size() == 0) {
			if (denyList.contains(ext)) {
				return false;
			} else {
				return true;
			}
		}
		if (denyList.size() == 0) {
			if (allowList.contains(ext)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
