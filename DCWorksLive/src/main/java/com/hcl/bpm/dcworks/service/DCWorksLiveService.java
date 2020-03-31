package com.hcl.bpm.dcworks.service;

import com.hcl.bpm.dcworks.response.BaseResponse;
import com.hcl.bpm.dcworks.response.DCWorksLivePoolDetailsResponse;
import com.hcl.bpm.dcworks.response.DCWorksLiveProcessDetailsResponse;
import com.hcl.bpm.dcworks.response.GenericResponse;

public interface DCWorksLiveService {

	public BaseResponse<GenericResponse> saveFile(String filePath,String fileName);
	public BaseResponse<DCWorksLivePoolDetailsResponse> fetchPool(String dcWorksPoolId);
	public BaseResponse<DCWorksLiveProcessDetailsResponse> fetchProcess(String dcWorksProcessId);
}
