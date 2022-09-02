package io.cloud.home.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

public class RegexUtils {
	
	public static <T> T convertCamel2Snake(Map<String, Object> camel, Class<T> clss, boolean changeNumbers){
		ObjectMapper objectMapper = new ObjectMapper();	//미리 선언된 objectMapper Bean에 영향을 주지 않기 위해 로컬로 따로 선언
		Map<String, Object> snake = new HashMap<String, Object>();
		
		String groupPattern = "[A-Z]";
		if(changeNumbers) { //abcDef1 -> abc_def_1이 되어야 할 경우 true 사용. 기본값 true 
			groupPattern = groupPattern + "|[0-9]";
		}
		Pattern pattern = Pattern.compile(groupPattern);
		
		camel.forEach((key, value) -> {
			Matcher matcher = pattern.matcher(key);
			String newKey = matcher.replaceAll("_$0");
			snake.put(newKey.toLowerCase(), value);
		});
		
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		return objectMapper.convertValue(snake, clss);
	}
	
	public static <T> T convertCamel2Snake(Map<String, Object> camel, Class<T> clss){
		return convertCamel2Snake(camel, clss, true);
	}
}