package com.design.cms.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.utils.ValidateUtils;
import com.design.cms.common.utils.ValidationResult;

public class Evn {
	
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static HttpServletResponse getResponse(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	public static void setError(DesignException e){
		setError(e.getMessage());
	}

	public static void setError(String error) {
		getRequest().setAttribute("error", error);
	}

	public static void validateEntiry(Object obj) {
		ValidationResult result = ValidateUtils.validateEntiry(obj);
		if(result.isHasErrors()){
			throw new DesignException(result.getErrorMsg().toString());
		}
	}
}
