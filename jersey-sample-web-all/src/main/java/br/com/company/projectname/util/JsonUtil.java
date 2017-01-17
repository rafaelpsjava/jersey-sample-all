package br.com.company.projectname.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	private static JsonUtil jsonUtil;

	private ObjectMapper defaultObjectMapper = new ObjectMapper();

	private JsonUtil() {
		final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.defaultObjectMapper.setDateFormat(dateFormat);
	}

	public static JsonUtil getInstance() {
		if (jsonUtil == null) {
			synchronized (JsonUtil.class) {
				if (jsonUtil == null) {
					jsonUtil = new JsonUtil();
				}
			}
		}
		return jsonUtil;
	}

	public static JsonUtil getNewInstance() {
		return new JsonUtil();
	}

	public String toJson(Object object) throws JsonGenerationException, JsonMappingException, IOException {
		return defaultObjectMapper.writeValueAsString(object);
	}

	public <T> T toObject(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		return defaultObjectMapper.readValue(json, clazz);
	}

}
