package com.sqb.blog.api.cmpt;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.sqb.blog.api.utl.QueryStringEncodingHttpServletRequest;

public class QueryStringEncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		ServletRequest req = request;
		if (request instanceof HttpServletRequest) {
			req = new QueryStringEncodingHttpServletRequest((HttpServletRequest) request, Charset.forName("ISO-8859-1"),
					Charset.forName("UTF-8"));
		}
		chain.doFilter(req, response);
	}

	@Override
	public void destroy() {

	}

}
