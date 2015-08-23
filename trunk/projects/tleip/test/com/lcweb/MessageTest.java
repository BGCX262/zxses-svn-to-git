package com.lcweb;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.registry.infomodel.User;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lcweb.bean.pojo.FrontMessage;
import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.dao.base.BaseDao;


public class MessageTest {
	private BaseDao baseDao;
	
	@Before
	public void setEnv(){
		try {
			ApplicationContext ac = new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/applicationContext*.xml"); 
			baseDao = (BaseDao)ac.getBean("baseDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testSaveMessage() throws Exception
	{
		FrontMessage message = new FrontMessage();
		FrontUser user = (FrontUser)baseDao.queryObjectById(FrontUser.class, 1L);
		for(int i = 11; i< 20; i++){
			message.setFrontUser(user);
			message.setMessageContent("just test 哈哈哈" + i);
			message.setMessageDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			message.setFacePic("face1.gif");
			message.setHeadPic("pic1.gif");
			message.setApproveStatus((short)1);
		
			baseDao.saveObject(message);
		}
		System.out.println("test");
	}
}
