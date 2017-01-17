package br.com.company.projectname.jersey.ws.infra.filter.compression;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import br.com.domain.sample.annotation.Compress;

/**
 * GZIPWriterInterceptor
 * 
 * @author Rafael
 *
 */
public class GZIPWriterInterceptor implements WriterInterceptor {

	/**
	 * aroundWriteTo
	 * 
	 * @param context
	 *            {@link WriterInterceptorContext}
	 * 
	 */
	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		GZIPOutputStream gzipOutputStream = null;
		try {
			boolean isToCompress = isToCompress(context);

			final OutputStream outputStream = context.getOutputStream();
			if (isToCompress) {
				MultivaluedMap<String, Object> headers = context.getHeaders();
				headers.add("Content-Encoding", "gzip");

				if (outputStream != null) {
					gzipOutputStream = new GZIPOutputStream(outputStream);
					context.setOutputStream(gzipOutputStream);
				}
			}

			context.proceed();

		} catch (Throwable throwable) {
		} finally {
			try {
				if (gzipOutputStream != null) {
					gzipOutputStream.close();
				}
			} catch (Throwable throwable) {
			}
		}
	}

	/**
	 * isToCompress
	 * 
	 * if response string is greater than 8096, or if the requested method has:
	 * {@link Compress} annotation
	 * 
	 * @param context
	 *            {@link WriterInterceptorContext}
	 * @return boolean
	 */
	private boolean isToCompress(WriterInterceptorContext context) {
		Object entity = context.getEntity();
		boolean isToCompress = false;
		if (entity != null) {
			if (entity instanceof String) {
				isToCompress = ((String) entity).length() > 8096;
			}
		}
		if (isToCompress) {
			return true;
		}
		Annotation[] annotations = context.getAnnotations();
		if (annotations != null) {
			for (Annotation annotation : annotations) {
				if (annotation != null && annotation.toString().contains(Compress.class.getName())) {
					return true;
				}
			}
		}
		return false;
	}
}