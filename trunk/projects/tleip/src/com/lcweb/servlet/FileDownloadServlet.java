package com.lcweb.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class FileDownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6122337939868762369L;

	/**
	 * Constructor of the object.
	 */
	public FileDownloadServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException{
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			response.setContentType("text/html");
			BufferedInputStream bufferInputStream = null;
			BufferedOutputStream bufferOutputStream = null;
			OutputStream outputStream = null;
			InputStream inputStream = null;
			String filepath = request.getParameter("filePath");
			String fileName = filepath.substring(StringUtils.lastIndexOf(filepath, "/")+1);
			filepath = getServletContext().getRealPath(filepath);
			File outputFile = new File(filepath);
			inputStream = new FileInputStream(outputFile);
			bufferInputStream = new BufferedInputStream(inputStream);
			outputStream = response.getOutputStream();
			bufferOutputStream = new BufferedOutputStream(outputStream);
			response.setHeader("Content-disposition", "attachment;filename="
					+ URLEncoder.encode(fileName, "UTF-8"));
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = bufferInputStream.read(buffer, 0, 8192)) != -1) {
				bufferOutputStream.write(buffer, 0, bytesRead);
			}
			bufferOutputStream.flush();
			bufferInputStream.close();
			bufferOutputStream.close();
		}catch( IOException ioexception){
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
