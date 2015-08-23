package com.lcweb.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lcweb.base.util.RandImgCreater;
import com.lcweb.base.util.StringUtil;
import com.lcweb.base.util.ValidateCode;

public class CodeImageAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//表明生成的响应是图片     
        response.setContentType("image/jpeg"); 
        
        //有前端登录和后台登录都需要验证码 为了区分加一个参数
        String frontUserCode = request.getParameter("frontUserCode");
        /*ValidateCode image = new ValidateCode(5);

		try {
			ImageIO.write(image.creatImage(), "JPEG", response.getOutputStream());
			// 将认证码存入SESSION
			request.getSession().setAttribute("rand", image.getRandCode());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        //加强型验证码的输出
        RandImgCreater image = new RandImgCreater(response);
        String imageCode = image.createRandImage();
        //将认证码存入SESSION
        
        frontUserCode = StringUtil.getNullString(frontUserCode);
        if(frontUserCode.length() != 0){
        	if("yes".equals(frontUserCode))
        		//前端验证码
        		request.getSession().setAttribute("frontUserRand", imageCode);
        }else{
        	request.getSession().setAttribute("rand", imageCode);
        }
		return null;
	}
}
