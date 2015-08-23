package com.velocityTest;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.lcweb.base.util.EmailSender;
import com.lcweb.commons.CipherUtil;

public class SendEmailTest {
	public static void main(String[] args) throws ResourceNotFoundException, ParseErrorException, Exception {
		Velocity.init("src/velocity.properties");
		//EmailSender.send(new String[]{"1124715454@qq.com"}, "邮件测试", "<b>boleyn</b>",null , "text/html");
		
		Template template = Velocity.getTemplate("mailContent.vm");
		VelocityContext context = new VelocityContext();
		context.put("username", "tlschool");
		String validateCode = CipherUtil.generatePassword("tlschool"+ "admin");
		context.put("validateCode", validateCode);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writer.flush();
		String mailContent = writer.toString();//得到邮件内容
		EmailSender.send(new String[]{"1124715454@qq.com"}, "塘朗小学--找回密码", mailContent, "text/html");
	}
}
