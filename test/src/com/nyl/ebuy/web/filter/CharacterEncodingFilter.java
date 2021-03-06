package com.nyl.ebuy.web.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CharacterEncodingFilter implements Filter{
	
	private String encoding = null;
	@SuppressWarnings("unused")
	private FilterConfig fg = null;
		
	public void destroy() {
		this.encoding = null;
		this.fg = null;

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain fc) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse)res;
		response.setContentType("text/html;charset="+this.encoding);
		HttpServletRequest request = (HttpServletRequest)req;
		HttpRequestWrapper hrw  = new HttpRequestWrapper(request);
		fc.doFilter(hrw, res);
		/*if("POST".equalsIgnoreCase(request.getMethod()))
			request.setCharacterEncoding(encoding);
		else 
			request  = new HttpRequestWrapper(request);		
		fc.doFilter(request, res);*/
	}

	public void init(FilterConfig fg) throws ServletException {			
		this.fg = fg;
		this.encoding = fg.getInitParameter("encoding");
		if(this.encoding == null || "".equals(encoding)) this.encoding = "UTF-8";
	}
	
	public class HttpRequestWrapper extends HttpServletRequestWrapper{
		public 	HttpRequestWrapper(HttpServletRequest request){
			super(request);
		}
		public String getParameter(String name){
			String value = null;
			value = this.encoding(((HttpServletRequest)this.getRequest()).getParameter(name));
			
			return value;
		}
		public String[] getParameterValues(String name){
			String[] values = ((HttpServletRequest)this.getRequest()).getParameterValues(name);
			
			if(values != null){
				int length = values.length;
				for(int i = 0; i < length; i++){
					values[i] = this.encoding(values[i]);
				}
			}
			return values;
		}
		public String encoding(String value){
			if(value != null){
				try {
					value = new String(value.getBytes("ISO-8859-1"),CharacterEncodingFilter.this.encoding);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return value;
		}
	}
}
