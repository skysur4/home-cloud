package io.cloud.home.controller;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloud.home.model.LowerSnakeModel;
import io.cloud.home.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/model")
public class ModelTestController{
    @Autowired
    ObjectMapper objectMapper;

	@GetMapping(value = "/lower")
	public String check() throws JsonProcessingException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Map<String, Object> camels = new HashMap<String, Object>();

		//test data
		camels.put("carId", "1234");
		camels.put("carModel", "2019G70FR2000CC");
		camels.put("carName", "G70");
		camels.put("carType", "Sports Sedan");
		camels.put("carTypeTestValue1", "Sports Sedan Fast and Furious");

		String camelcase = objectMapper.writeValueAsString(camels);
		log.info("camels: {}", camelcase);

		LowerSnakeModel lowerSnakeModel = RegexUtils.convertCamelMap2SnakeModel(camels, LowerSnakeModel.class);
		log.info("lower snake model: {}", lowerSnakeModel.toString());

		return camelcase;
	}
}