package br.com.company.projectname.servlet.filter.gzip;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * GZIPFilter
 * 
 * @author Rafael
 *
 */
@WebFilter(urlPatterns = "/*")
public class GZIPFilter implements Filter {

	/**
	 * doFilter
	 * 
	 * @param servletRequest
	 *            {@link ServletRequest}
	 * @param servletResponse
	 *            {@link ServletResponse}
	 * @param chain
	 *            {@link FilterChain}
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			String acceptEncoding = request.getHeader("accept-encoding");

			if (acceptEncoding != null && acceptEncoding.indexOf("gzip") != -1) {
				GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
				chain.doFilter(servletRequest, wrappedResponse);
				wrappedResponse.finishResponse();

				return;
			}

			chain.doFilter(servletRequest, servletResponse);
		}
	}

	/**
	 * destroy
	 */
	@Override
	public void destroy() {
	}

	/**
	 * init
	 * 
	 * @param filterConfig
	 *            {@link FilterConfig}
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}