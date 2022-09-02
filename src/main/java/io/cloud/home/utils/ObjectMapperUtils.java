package io.cloud.home.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.google.common.base.CaseFormat;

import io.cloud.home.model.LowerSnakeModel;
import io.cloud.home.model.UpperSnakeModel;

public class ObjectMapperUtils {
	
	public static UpperSnakeModel convert2UpperSnake(Map<String, Object> camel){
		ObjectMapper objectMapper = new ObjectMapper();	//단일 용도이므로 로컬에서 선언
		Map<String, Object> uppersnake = new HashMap<String, Object>();
		
		camel.forEach((key, value) -> {
			uppersnake.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, key), value);
		});
		
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_SNAKE_CASE);
		return objectMapper.convertValue(uppersnake, UpperSnakeModel.class);
	}
	
	public static LowerSnakeModel convert2LowerSnake(Map<String, Object> camel){
		ObjectMapper objectMapper = new ObjectMapper();	//단일 용도이므로 로컬에서 선언
		Map<String, Object> lowersnake = new HashMap<String, Object>();
		
		camel.forEach((key, value) -> {
			lowersnake.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key), value);
		});
		
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		return objectMapper.convertValue(lowersnake, LowerSnakeModel.class);
	}
}