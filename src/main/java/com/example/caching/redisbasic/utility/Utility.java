package com.example.caching.redisbasic.utility;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Utility {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static <T> String objectToJSONAndPrettyPrint(T t) throws JsonProcessingException {
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		if(t instanceof String && isValidJson((String)t)){
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree((String)t));
		}
		return mapper.writeValueAsString(t);
	}

	public static boolean isValidJson(String json) {
		try {
			mapper.readTree(json);
		} catch (JacksonException e) {
			return false;
		}
		return true;
	}
}
