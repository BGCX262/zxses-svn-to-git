package com.agilefly.service.blogarticle.impl;

import javax.annotation.Resource;

import org.compass.gps.CompassGps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agilefly.service.blogarticle.IBlogArticleReIndexService;

@Service
@Transactional
public class BlogArticleReIndexService implements IBlogArticleReIndexService {
	@Resource
	private CompassGps compassGps;
	
	public void reIndex() {
		compassGps.start();
		compassGps.index();
		compassGps.stop();
	}

}
