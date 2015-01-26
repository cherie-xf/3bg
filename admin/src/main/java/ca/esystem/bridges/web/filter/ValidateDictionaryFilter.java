package ca.esystem.bridges.web.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(filterName="ValidateDictionaryFilter",urlPatterns="*.html")
public class ValidateDictionaryFilter implements Filter {

	private static final String host = "http://localhost";
	private static final String contextPath = "/admin";
	
    public ValidateDictionaryFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
        System.out.println("validate dictionary");
		if(request.getSession().getServletContext().getAttribute("ticketStatusList")==null){
        	loadDictionary();
        }
    	chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private void loadDictionary(){
		try{
			URL url = new URL(host+contextPath+"/refreshDictionary.json");//TODO: host and contextPath need to be read from properties file.
			System.out.println("open url = "+url.toString());
			URLConnection conn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				System.out.println(inputLine);
			}
			br.close();			
		}catch(Exception e){
			
		}		
	}
}
