 
package com.lcweb.struts.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.lcweb.commons.CheckRight;
import com.lcweb.service.login.LoginService;
import com.lcweb.struts.form.CommonsForm;

 
public class CommonsAction extends DispatchAction {
	private CheckRight checkRight;
	private LoginService loginServiceDao;

	/*
	 *  
	*/
	public ActionForward fileUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getSession().getAttribute("logininfo")==null){
			return mapping.findForward("index");
		}
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8" ); 
		CommonsForm commonsForm = (CommonsForm)form;
		String path = servlet.getServletContext().getRealPath("/uploadfiles/") +"/"+ request.getParameter("dir");
        Hashtable files = commonsForm.getMultipartRequestHandler().getFileElements();  
        
        for (Enumeration e = files.keys(); e.hasMoreElements();) {
             String key = (String) e.nextElement();
             FormFile   file = (FormFile) files.get(key);
              
	      InputStream streamIn = file.getInputStream();    
	         int ok=file.getFileSize();   
	         String strFee = null;   
	             
	          File uploadFile = new File(path);   
	          if (!uploadFile.exists() || uploadFile == null) {   
	              uploadFile.mkdirs();   
	          }   
	          String filepath = uploadFile.getPath() + "\\" + file.getFileName();   
	          OutputStream streamOut = new FileOutputStream(filepath);   
	          int bytesRead = 0;   
	          byte[] buffer = new byte[8192];   
	          while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {   
	              streamOut.write(buffer, 0, bytesRead);   
	          }   
	          streamOut.close();   
	          streamIn.close();   
	          file.destroy();       
        }    
        response.getWriter().write(this.loginServiceDao.getUploadFiles(null,request.getParameter("dir"),request,true));
        return null;  
		
	}
	
	
	
	
	 public ActionForward downFile(ActionMapping mapping, ActionForm form,   
             HttpServletRequest request,   
             HttpServletResponse response) throws   Exception {   
	 if(request.getSession().getAttribute("logininfo")==null){
			return mapping.findForward("index");
		}
	    
		String dir =   new String(request.getParameter("dir").getBytes("iso8859-1"),"UTF-8")  ;  
		String file  = new String(request.getParameter("file").getBytes("iso8859-1"),"UTF-8")  ;
		BufferedInputStream bis = null;   
		BufferedOutputStream bos = null;   
		OutputStream fos = null;   
		InputStream fis = null;   
		
		String path = servlet.getServletContext().getRealPath("/uploadfiles/");
		String  filepath=path+"/"+dir+"/"+file;   
		File uploadFile = new File(filepath);   
		fis = new FileInputStream(uploadFile);   
		bis = new BufferedInputStream(fis);   
		fos = response.getOutputStream();   
		bos = new BufferedOutputStream(fos);   
		response.setHeader("Content-disposition",   
		      "attachment;filename=" +   
		      URLEncoder.encode(file, "utf-8"));    
		int bytesRead = 0;    
		byte[] buffer = new byte[8192];    
		while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {    
		bos.write(buffer, 0, bytesRead);    
		}    
		bos.flush();    
		fis.close();    
		bis.close();    
		fos.close();    
		bos.close();    
		return null;    
	 }    

	 public ActionForward deleteFile(ActionMapping mapping, ActionForm form,   
             HttpServletRequest request,   
             HttpServletResponse response) throws   Exception {   
		  if(request.getSession().getAttribute("logininfo")==null){
				return mapping.findForward("index");
			}
		    
		    String dir =   new String(request.getParameter("dir").getBytes("iso8859-1"),"UTF-8")  ;  
			String filename  = new String(request.getParameter("file").getBytes("iso8859-1"),"UTF-8")  ;
			    
			String path = servlet.getServletContext().getRealPath("/uploadfiles/");
			String  filepath=path+"\\"+dir+"\\"+filename;   
			File file = new File(filepath);  
			if(file.exists()){
				file.delete();
				response.getWriter().write(this.loginServiceDao.getUploadFiles(null,dir,request,true));
			}else{
				response.getWriter().write("<font color=\"red\">Ҫɾ����ļ�������</font>");
			}
			
			return null;    
	 }




	public CheckRight getCheckRight() {
		return checkRight;
	}




	public void setCheckRight(CheckRight checkRight) {
		this.checkRight = checkRight;
	}




	public LoginService getLoginServiceDao() {
		return loginServiceDao;
	}




	public void setLoginServiceDao(LoginService loginServiceDao) {
		this.loginServiceDao = loginServiceDao;
	}    
		
}