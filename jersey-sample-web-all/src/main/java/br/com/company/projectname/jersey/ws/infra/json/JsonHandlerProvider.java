package br.com.company.projectname.jersey.ws.infra.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * JsonHandlerProvider
 * 
 * @author Rafael
 *
 */
@Provider
public class JsonHandlerProvider implements ContextResolver<ObjectMapper> {
	private final ObjectMapper defaultObjectMapper = new ObjectMapper();

	public JsonHandlerProvider() {
		this.defaultObjectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);

		final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.defaultObjectMapper.setDateFormat(dateFormat);
	}

	/**
	 * getContext
	 * 
	 * @return {@link ObjectMapper}
	 */
	@Override
	public ObjectMapper getContext(Class<?> type) {
		return defaultObjectMapper;
	}

}