package com.kh.spring.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ControllerAdvice(basePackages = "com.kh.spring")
public class ExceptionAdvice {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//예외가 발생했음으로 응답상태코드를 500으로 지정, default 200
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HandlableException.class)
	public String handlableExceptionProcess(HandlableException e, Model model) {
		model.addAttribute("msg", e.error.MESSAGE);
		model.addAttribute("url", e.error.URL);
		return "error/result";
	}
	
	
}
