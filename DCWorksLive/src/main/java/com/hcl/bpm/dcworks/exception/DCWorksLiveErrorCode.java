package com.hcl.bpm.dcworks.exception;

public enum DCWorksLiveErrorCode implements Errors {

	SUCCESS_ERROR_CODE("DCWorksLive_000", "Success"),
	GENERIC_ERROR_CODE("DCWorksLive_001", "Sorry! We are experiencing some technical issues. Please try again later."),
	POOL_ID_INVALID_MSG("DCWorksLive_002","Invalid DCWorksPoolId,please provide valid DCWorksPoolId."),
	PROCESS_ID_INVALID_MSG("DCWorksLive_003","Invalid DCWorksProcessId,please provide valid DCWorksProcessId."),
	UNEXPECTED_ERR_MSG("DCWorksLive_004","Something went wrong here"),
	EXCEPTION_RAISED_MSG("DCWorksLive_005","Exception raised. Root Cause {}, Message {}");
	
	private String errorCode;
	private String errorMessage;

	DCWorksLiveErrorCode(String erroCode, String errorMessage) {
		this.errorCode = erroCode;
		this.errorMessage = errorMessage;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}
}
