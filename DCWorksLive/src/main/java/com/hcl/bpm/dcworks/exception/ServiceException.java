package com.hcl.bpm.dcworks.exception;

import org.springframework.http.HttpStatus;

import com.hcl.bpm.dcworks.constants.DCWorksLiveConstant;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 8095179179694212963L;
	
	/**
	 * This property carries the exception message from the original exception
	 * or its own message being set at the time of instantiation.
	 */
	private final String exceptionMessage;
	
	private int status=DCWorksLiveConstant.FAILURE;

	/**
	 * This property carries the detailed user message which is meant for the
	 * end user.
	 */
	private final String userMessgage;


	/**
	 *  This is the status code used to specify the scenario in case of occurrence of exception.
	 */
	private HttpStatus httpStatus;

	/**
	 * This is inner original exception occurred wrapped in the {@code ServiceException}
	 */
	private String code;
	

	private String serviceId=DCWorksLiveConstant.DCWorksLive;
	
	
	public ServiceException(Exception innerException, String userMessage) {
		super(innerException);
		this.exceptionMessage = innerException.getMessage();
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		this.code = DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorCode();
		if (userMessage != null && !userMessage.isEmpty()) {
			this.userMessgage = userMessage;
		} else {
			this.userMessgage = DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorMessage();
		}
	}
	
	public ServiceException(DCWorksLiveErrorCode errorCode) {
		this(errorCode.getErrorCode(),errorCode.getErrorMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ServiceException(DCWorksLiveErrorCode errorCode,HttpStatus httpStatus) {
		this(errorCode.getErrorCode(),errorCode.getErrorMessage(),httpStatus);
	}
	
	public ServiceException(DCWorksLiveErrorCode errorCode,HttpStatus httpStatus,String serviceId) {
		this(errorCode.getErrorCode(),errorCode.getErrorMessage(),httpStatus,serviceId);
	}
	
	public ServiceException(Exception innerException, String userMessage, String code) {
		super(innerException);
		this.exceptionMessage = innerException.getMessage();
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if (userMessage != null && !userMessage.isEmpty()) {
			this.userMessgage = userMessage;
		} else {
			this.userMessgage = DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorMessage();
		}
		if (code != null && !code.isEmpty()) {
			this.code = code;
		} else {
			this.code = DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorCode();
		}
	}
	
	
	public ServiceException(Exception innerException, String userMessage, String code,String serviceId) {
		this(innerException,userMessage,code);
		if(serviceId != null && !serviceId.isEmpty()) {
			this.serviceId=serviceId;
		}
	}
	
	public ServiceException(String code, String userMessage, HttpStatus httpStatus) {
		this.exceptionMessage = userMessage;
		if (userMessage != null && !userMessage.isEmpty()) {
			this.userMessgage = userMessage;
		} else {
			this.userMessgage = DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorMessage();
		}
		if (code != null && !code.isEmpty()) {
			this.code = code;
		} else {
			this.code =  DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorCode();
		}
		if (httpStatus != null) {
			this.httpStatus = httpStatus;
		} else {
			this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		this.status=DCWorksLiveConstant.FAILURE;
	}
	
	public ServiceException(String code,String userMessage) {
		this(code, userMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ServiceException(String code, int status,String userMessage, HttpStatus httpStatus,String serviceId) {
		this(code,status, userMessage, httpStatus);
		if(serviceId != null && !serviceId.isEmpty()){
			this.serviceId=serviceId;
		}
	}
	
	public ServiceException(DCWorksLiveErrorCode netcErrorCode, int status) {
		this(netcErrorCode.getErrorCode(), status,netcErrorCode.getErrorMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ServiceException(String code, int status,String userMessage, HttpStatus httpStatus) {
		this(code, userMessage, httpStatus);
		this.status=status;
	}
	

	public ServiceException(String code, String userMessage, HttpStatus httpStatus,String serviceId) {
		this(code, userMessage, httpStatus);
		if(serviceId != null && !serviceId.isEmpty()) {
			this.serviceId=serviceId;
		}
	}
	
	public ServiceException(Errors error, HttpStatus httpStatus) {
		this.exceptionMessage = error.getErrorMessage();
		if (error.getErrorMessage() != null && !error.getErrorCode().isEmpty()) {
			this.userMessgage = error.getErrorMessage();
		} else {
			this.userMessgage =  DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorMessage();
		}
		if (error.getErrorCode() != null && !error.getErrorCode().isEmpty()) {
			this.code = error.getErrorCode();
		} else {
			this.code =DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorCode();
		}
		if (httpStatus != null) {
			this.httpStatus = httpStatus;
		} else {
			this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	 /**
     * Instantiates a new service exception.
     *
     * @param innerException the inner exception
     */
	public ServiceException(Exception innerException) {
		super(innerException);
		this.exceptionMessage = innerException.getMessage();
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		this.userMessgage = DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorMessage();
		this.code =DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorCode();
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getServiceId() {
		return serviceId;
	}


	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


	public String getExceptionMessage() {
		return exceptionMessage;
	}


	public String getUserMessgage() {
		return userMessgage;
	}
	
	
	
}
