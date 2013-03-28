package main.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class EncodeFilter implements Filter{

	@Override
	public void destroy() {  }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest){
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			if ("POST".equals(httpServletRequest.getMethod())){
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
			}
		}
		
        chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException { }

}
