package com.velocityTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.lcweb.bean.pojo.FrontUser;

public class VelocityTest {
	public static void main(String[] args) {
		try {
			Velocity.init("src/velocity.properties");
			VelocityContext context = new VelocityContext();
			List<FrontUser> users = new ArrayList<FrontUser>();
			FrontUser user1 = new FrontUser();
			user1.setUserAccount("test01");
			user1.setUserName("不学");
			users.add(user1);
			
			FrontUser user2 = new FrontUser();
			user2.setUserAccount("test02");
			user2.setUserName("不学02");
			users.add(user2);
			
			FrontUser user3 = new FrontUser();
			user3.setUserAccount("test03");
			user3.setUserName("不学03");
			users.add(user3);
			
			FrontUser user4 = new FrontUser();
			user4.setUserAccount("test04");
			user4.setUserName(null);
			users.add(user4);
			
			for (FrontUser user : users) {
				System.out.println(user.getUserAccount() + ":" + user.getUserName());
			}
			
			//context.put("title", "velocity 测试");
			context.put("title", null);
			context.put("users", users);
			
			Template template = Velocity.getTemplate("helloworld.vm");
			
			File saveFile = new File("D:\\workspace\\tleip\\test.html");
			FileOutputStream outstream = new FileOutputStream(saveFile);
			OutputStreamWriter writer = new OutputStreamWriter(outstream, "UTF-8");
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			
			template.merge(context, bufferWriter);
			bufferWriter.flush();
			outstream.close();
			bufferWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
