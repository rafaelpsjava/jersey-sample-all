package br.com.web.sample.jersey.ws.infra.body;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ejb.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Singleton
@Provider
public class MessageWriter implements MessageBodyWriter<Message> {

	@Override
	public boolean isWriteable(final Class<?> clazz, final Type type, final Annotation[] annotation, final MediaType mediaType) {
		return clazz == Message.class;
	}

	@Override
	public long getSize(final Message message, final Class<?> clazz, final Type type, final Annotation[] annotation, final MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(final Message message, final Class<?> clazz, final Type type, final Annotation[] annotation, final MediaType mediaType,
			final MultivaluedMap<String, Object> arg5, final OutputStream ostream) throws IOException, WebApplicationException {
		ostream.write(message.toString().getBytes());
	}

}