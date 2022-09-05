package io.cloud.home.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.google.common.base.CaseFormat;

public class ObjectMapperUtils {
	
	public static <T> T convert2Snake(Map<String, Object> camel, Class<T> c){
		ObjectMapper objectMapper = new ObjectMapper();	//단일 용도이므로 로컬에서 선언
		Map<String, Object> lowersnake = new HashMap<String, Object>();
		
		camel.forEach((key, value) -> {
			lowersnake.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key), value);
		});
		
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		return objectMapper.convertValue(lowersnake, c);
	}
}