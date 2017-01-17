package br.com.web.sample.jersey.ws.infra.filter.compression;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

/**
 * GZIPReaderInterceptor
 * 
 * @author Rafael
 *
 */
public class GZIPReaderInterceptor implements ReaderInterceptor {

	/**
	 * aroundReadFrom
	 * 
	 * @param context
	 *            {@link ReaderInterceptorContext}
	 */
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		GZIPInputStream gzipInputStream = null;
		try {

			InputStream originalInputStream = context.getInputStream();
			// if is not null and is a instance of GZIPInputStream
			if (originalInputStream != null && originalInputStream instanceof GZIPInputStream) {
				gzipInputStream = new GZIPInputStream(originalInputStream);
				context.setInputStream(gzipInputStream);
			}

		} catch (Throwable throwable) {

			if (gzipInputStream != null) {
				gzipInputStream.close();
			}

		}

		return context.proceed();
	}

}