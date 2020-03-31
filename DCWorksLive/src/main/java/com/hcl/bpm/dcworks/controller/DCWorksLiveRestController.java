package com.hcl.bpm.dcworks.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.bpm.dcworks.constants.DCWorksLiveConstant;
import com.hcl.bpm.dcworks.request.DCWorksLiveSaveXMLRequest;
import com.hcl.bpm.dcworks.response.BaseResponse;
import com.hcl.bpm.dcworks.response.DCWorksLivePoolDetailsResponse;
import com.hcl.bpm.dcworks.response.DCWorksLiveProcessDetailsResponse;
import com.hcl.bpm.dcworks.response.GenericResponse;
import com.hcl.bpm.dcworks.service.DCWorksLiveService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(DCWorksLiveConstant.BASEURL)
public class DCWorksLiveRestController {

	@Autowired
	DCWorksLiveService dcWorksLiveService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Mvc");
		model.addObject("message", "This is default page!");
		model.setViewName("index");
		return model;

	}
	
	@ApiOperation(value = "Ping DCWorksLive service", response = DCWorksLiveRestController.class)
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public ResponseEntity<String> getCurrentTimeStamp() {
		return new ResponseEntity<>("DCWorksLive service application recieved ping on " + new Date().toString(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Ping DCWorksLive service", response = DCWorksLiveRestController.class)
	@RequestMapping(value = "/fetchPool/dcWorksPoolId/{dcWorksPoolId}", method = RequestMethod.GET)
	 public ResponseEntity<BaseResponse<DCWorksLivePoolDetailsResponse>> fetchPool(@PathVariable(value = "dcWorksPoolId") String dcWorksPoolId) {
		BaseResponse<DCWorksLivePoolDetailsResponse> response = dcWorksLiveService.fetchPool(dcWorksPoolId);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Ping DCWorksLive service", response = DCWorksLiveRestController.class)
	@RequestMapping(value = "/fetchProcess/dcWorksProcessId/{dcWorksProcessId}", method = RequestMethod.GET)
	 public ResponseEntity<BaseResponse<DCWorksLiveProcessDetailsResponse>> fetchProcess(@PathVariable(value = "dcWorksProcessId") String dcWorksProcessId) {
		BaseResponse<DCWorksLiveProcessDetailsResponse> response = dcWorksLiveService.fetchProcess(dcWorksProcessId);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "save xml file data DCWorksLive service",response = DCWorksLiveRestController.class)
	@RequestMapping(value = "/save/fileName/{fileName}", method = RequestMethod.POST)
	 public ResponseEntity<BaseResponse<GenericResponse>> save(@PathVariable(value = "fileName") String fileName, @RequestBody DCWorksLiveSaveXMLRequest request) {

		BaseResponse<GenericResponse> response = dcWorksLiveService.saveFile(request.getFilePath(),fileName);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
}
