package com.hcl.bpm.dcworks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.bpm.dcworks.constants.DCWorksLiveConstant;
import com.hcl.bpm.dcworks.response.ErrorResponse;
import com.hcl.bpm.dcworks.response.Meta;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(Exception exception) {
		Meta meta=new Meta(DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorCode(),DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorMessage(),DCWorksLiveConstant.FAILURE);
		ErrorResponse errorResponse = new ErrorResponse(meta);
		return errorResponse;
	}	
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorResponse> handleException(ServiceException serviceException) {
		Meta meta = new Meta(serviceException.getCode(),serviceException.getUserMessgage(), DCWorksLiveConstant.FAILURE);
		ErrorResponse response =	new ErrorResponse(meta);
		return new ResponseEntity<>(response, serviceException.getHttpStatus());
	}
}
