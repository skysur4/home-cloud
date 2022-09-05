package io.cloud.home.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

public class RegexUtils {

	public static Map<String, Object> convertCamel2Snake(Map<String, Object> camelMap, boolean changeNumbers){
		Map<String, Object> snakeMap = new HashMap<String, Object>();

		String groupPattern = "[A-Z]";
		if(changeNumbers) { //abcDef1 -> abc_def_1이 되어야 할 경우 true 사용. 기본값 true
			groupPattern = groupPattern + "|[0-9]";
		}
		Pattern pattern = Pattern.compile(groupPattern);

		camelMap.forEach((key, value) -> {
			Matcher matcher = pattern.matcher(key);
			String newKey = matcher.replaceAll("_$0");
			snakeMap.put(newKey.toLowerCase(), value);
		});

		return snakeMap;
	}

	public static <T> T convertCamelMap2SnakeModel(Map<String, Object> camelMap, Class<T> targetClass, boolean changeNumbers) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ConfigurableConversionService conversionService = new DefaultConversionService();
		Map<String, Object> snakeMap = convertCamel2Snake(camelMap, changeNumbers);
		
		T target = targetClass.getConstructor().newInstance();
		Field[] fields = targetClass.getDeclaredFields();
		
		System.out.println("### Start copying map to " + targetClass.getName() + "###");
		snakeMap.forEach((key, value) -> {
			for(Field f : fields) {
				f.setAccessible(true);
				String fName = f.getName(); 
				if(key.equals(fName)) {
					try {
	                    Object targetValue = conversionService.convert(value, f.getType());
						f.set(target, targetValue);
					} catch (IllegalArgumentException | IllegalAccessException | ClassCastException e) {
						System.out.println("!!! Fail to copy property: " + key + " [" + e.getMessage() + "]");
					}
				}
			}
		});

		return target;
	}

	public static <T> T convertCamelMap2SnakeModel(Map<String, Object> camel, Class<T> clss) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return convertCamelMap2SnakeModel(camel, clss, true);
	}
}