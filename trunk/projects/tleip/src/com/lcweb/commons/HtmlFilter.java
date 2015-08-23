package com.lcweb.commons;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;

public class HtmlFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		Map map = req.getParameterMap();
		Set set = map.entrySet();
		if (map != null) {
			for (Iterator it = set.iterator(); it.hasNext();) {
				Map.Entry entry = (Entry) it.next();
				if (entry.getValue() instanceof String[]) {
					String[] values = (String[]) entry.getValue();
					for (int i = 0; i < values.length; i++) {
						String temp = values[i];
						if (!ContainsChinese.isContainsChinese(temp)) {
							values[i] = StringEscapeUtils.escapeSql(values[i]);
							if (temp.equals(values[i])) {
								values[i] = StringEscapeUtils.escapeHtml(values[i]);
							}
						}

					}
					entry.setValue(values);
				}

			}
		}

		chain.doFilter(request, response);

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
