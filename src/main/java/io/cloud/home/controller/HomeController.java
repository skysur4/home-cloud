package io.cloud.home.controller;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/v1")
public class HomeController{
	@Autowired
	Tracer tracer;
	
	@Autowired
	HttpServletResponse response;
	
	@GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
	public String check() {
		String traceId = tracer.currentSpan().context().traceId();
		response.addHeader("traceId", traceId);
		
		return traceId;
	}
	
	@GetMapping(value = "/fault")
	public Object fault(){
		String traceId = tracer.currentSpan().context().traceId();
		response.addHeader("traceId", traceId);
		throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
	}
}