package com.agilefly.utils;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class HtmlUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testHtmlToText() {
		System.out.println(HtmlUtils.splitAndFilterString("<html>sd;flkj&lt;</html><span class='highlight'>s你好</span>",5));
		System.out.println(HtmlUtils.splitAndFilterString("<html>sd;flkj&lt;</html><span class='highlight'>人名</span><span class='test'>s你好</span>",100));
	}
}
