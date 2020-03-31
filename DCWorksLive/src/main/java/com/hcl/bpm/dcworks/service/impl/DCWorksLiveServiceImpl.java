package com.hcl.bpm.dcworks.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hcl.bpm.dcworks.constants.DCWorksLiveConstant;
import com.hcl.bpm.dcworks.entity.DCWorksLiveActivity;
import com.hcl.bpm.dcworks.entity.DCWorksLiveActivityNodeGraphicsInfo;
import com.hcl.bpm.dcworks.entity.DCWorksLiveLaneNodeGraphicsInfo;
import com.hcl.bpm.dcworks.entity.DCWorksLivePool;
import com.hcl.bpm.dcworks.entity.DCWorksLivePoolLane;
import com.hcl.bpm.dcworks.entity.DCWorksLiveProcess;
import com.hcl.bpm.dcworks.entity.DCWorksLiveTransition;
import com.hcl.bpm.dcworks.exception.DCWorksLiveErrorCode;
import com.hcl.bpm.dcworks.exception.ServiceException;
import com.hcl.bpm.dcworks.repository.DCWorksLivePoolRepository;
import com.hcl.bpm.dcworks.repository.DCWorksLiveProcessRepository;
import com.hcl.bpm.dcworks.repository.DCWorksLiveTransitionRepository;
import com.hcl.bpm.dcworks.response.BaseResponse;
import com.hcl.bpm.dcworks.response.DCWorksLiveActivityDetails;
import com.hcl.bpm.dcworks.response.DCWorksLiveLaneDetails;
import com.hcl.bpm.dcworks.response.DCWorksLiveLaneNodeGraphicsInfoDetails;
import com.hcl.bpm.dcworks.response.DCWorksLiveNodeGraphicsInfoDetails;
import com.hcl.bpm.dcworks.response.DCWorksLivePoolDetailsResponse;
import com.hcl.bpm.dcworks.response.DCWorksLivePoolDetailsResponse.DCWorksLivePoolDetails;
import com.hcl.bpm.dcworks.response.DCWorksLiveProcessDetailsResponse;
import com.hcl.bpm.dcworks.response.DCWorksLiveProcessDetailsResponse.Process;
import com.hcl.bpm.dcworks.response.DCWorksLiveTransitionDetails;
import com.hcl.bpm.dcworks.response.GenericResponse;
import com.hcl.bpm.dcworks.response.Meta;
import com.hcl.bpm.dcworks.service.DCWorksLiveService;
import com.hcl.bpm.dcworks.util.DCWorksUtil;

@Service("DCWorksLiveService")
public class DCWorksLiveServiceImpl implements DCWorksLiveService {

	private static final Logger logger = Logger.getLogger(DCWorksLiveServiceImpl.class);
	
	@Autowired
	DCWorksLivePoolRepository poolRepository;
	
	@Autowired
	DCWorksLiveProcessRepository processRepository;
	
	@Autowired
	DCWorksLiveTransitionRepository transitionRepository;
	
	
	public BaseResponse<DCWorksLivePoolDetailsResponse> fetchPool(String dcWorksPoolId) {
		BaseResponse<DCWorksLivePoolDetailsResponse> responseDTO = new BaseResponse<>();
		if(dcWorksPoolId != null && dcWorksPoolId.length()>0) {
			try {
				DCWorksLivePoolDetails pool = buildPoolResponse(dcWorksPoolId);
				DCWorksLivePoolDetailsResponse response = new DCWorksLivePoolDetailsResponse();
				response.setPoolData(pool);
				responseDTO.setData(response);
				responseDTO.setMeta(new Meta(DCWorksLiveConstant.SUCCESS_CODE, DCWorksLiveConstant.SUCCESS_MESSAGE,
						DCWorksLiveConstant.SUCCESS));
				logger.info("pool fetched successfully for DCWorksLivePoolId " + dcWorksPoolId + "  " + responseDTO);
				return responseDTO;
			}catch(NumberFormatException | NullPointerException e) {
				//logs exception
				logger.error("This is Error message", e);
				throw new ServiceException(DCWorksLiveErrorCode.POOL_ID_INVALID_MSG.getErrorCode(), DCWorksLiveErrorCode.POOL_ID_INVALID_MSG.getErrorMessage(),HttpStatus.OK);
			}catch(Exception e) {
				//logs exception
				logger.error("This is Error message", e);
				throw new ServiceException(DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorCode(), DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			throw new ServiceException(DCWorksLiveErrorCode.POOL_ID_INVALID_MSG.getErrorCode(), DCWorksLiveErrorCode.POOL_ID_INVALID_MSG.getErrorMessage(),HttpStatus.OK);
		}
		
	}
	
	private DCWorksLivePoolDetails buildPoolResponse(String dcWorksPoolId) throws Exception{
		try {
			Long poolId = Long.valueOf(dcWorksPoolId);
			DCWorksLivePool poolEntity = poolRepository.findOne(poolId);
			
			ModelMapper poolModelMapper = new ModelMapper();
			poolModelMapper.getConfiguration()
			  .setMatchingStrategy(MatchingStrategies.STRICT);
			DCWorksLivePoolDetails poolDetails = poolModelMapper.map(poolEntity, DCWorksLivePoolDetails.class);
			
			Set<DCWorksLiveLaneDetails>laneDataSet = buildLaneData(poolEntity);
			poolDetails.setLaneData(laneDataSet);
			
			
			DCWorksLiveProcess processEntity = processRepository.findProcess(dcWorksPoolId);
			Process process = buildProcessData(processEntity);
			poolDetails.setProcessData(process);

			
			return poolDetails;
		}catch(Exception e) {
			throw e;
		}
	}
	
	private Set<DCWorksLiveLaneDetails> buildLaneData(DCWorksLivePool poolEntity) {
        Set<DCWorksLiveLaneDetails> laneData = new HashSet<>();
		
		Set<DCWorksLivePoolLane> laneEntityData = poolEntity.getLaneDetails();
		for(DCWorksLivePoolLane laneObj : laneEntityData) {

			ModelMapper laneModelMapper = new ModelMapper();
			laneModelMapper.getConfiguration()
			  .setMatchingStrategy(MatchingStrategies.STRICT);
			DCWorksLiveLaneDetails laneDetails = laneModelMapper.map(laneObj, DCWorksLiveLaneDetails.class);
			
			Set<DCWorksLiveLaneNodeGraphicsInfoDetails> graphicsNodeData = new HashSet<>();
			Set<DCWorksLiveLaneNodeGraphicsInfo>nodeGraphicsEntityData = laneObj.getLaneNodeGraphicsInfo();
			for(DCWorksLiveLaneNodeGraphicsInfo graphicsNodeObj : nodeGraphicsEntityData) {
				ModelMapper graphicsNodeModelMapper = new ModelMapper();
				graphicsNodeModelMapper.getConfiguration()
				  .setMatchingStrategy(MatchingStrategies.STRICT);
				DCWorksLiveLaneNodeGraphicsInfoDetails graphicsNode = graphicsNodeModelMapper.map(graphicsNodeObj, DCWorksLiveLaneNodeGraphicsInfoDetails.class);
				graphicsNodeData.add(graphicsNode);
			}
			laneDetails.setGraphicsNodeData(graphicsNodeData);
			laneData.add(laneDetails);
		}
		return laneData;	
	}
	
	public BaseResponse<DCWorksLiveProcessDetailsResponse> fetchProcess(String dcWorksProcessId) {
		
		BaseResponse<DCWorksLiveProcessDetailsResponse> responseDTO = new BaseResponse<>();
		if(dcWorksProcessId != null && dcWorksProcessId.length()>0) {
			try {
				Long processId = Long.valueOf(dcWorksProcessId);
				DCWorksLiveProcess processEntity = processRepository.findOne(processId);
				Process process = buildProcessData(processEntity);
				DCWorksLiveProcessDetailsResponse response = new DCWorksLiveProcessDetailsResponse();
				response.setProcess(process);
				responseDTO.setData(response);
				responseDTO.setMeta(new Meta(DCWorksLiveConstant.SUCCESS_CODE, DCWorksLiveConstant.SUCCESS_MESSAGE,
						DCWorksLiveConstant.SUCCESS));
				logger.info("process fetched successfully for DCWorksLiveProcessId " + dcWorksProcessId + "  " + responseDTO);
				return responseDTO;
			}catch(NumberFormatException | NullPointerException e) {
				//logs exception
				logger.error("This is Error message", e);
				throw new ServiceException(DCWorksLiveErrorCode.PROCESS_ID_INVALID_MSG.getErrorCode(), DCWorksLiveErrorCode.PROCESS_ID_INVALID_MSG.getErrorMessage(),HttpStatus.OK);
			}catch(Exception e) {
				//logs exception
				logger.error("This is Error message", e);
				throw new ServiceException(DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorCode(), DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			throw new ServiceException(DCWorksLiveErrorCode.PROCESS_ID_INVALID_MSG.getErrorCode(), DCWorksLiveErrorCode.PROCESS_ID_INVALID_MSG.getErrorMessage(),HttpStatus.OK);
		}
		
	}
	
	private Process buildProcessData(DCWorksLiveProcess processEntity) throws Exception {
		
		try {
			
			ModelMapper processModelMapper = new ModelMapper();
			processModelMapper.getConfiguration()
			  .setMatchingStrategy(MatchingStrategies.STRICT);
			Process process = processModelMapper.map(processEntity, Process.class);
			
			Set<DCWorksLiveActivityDetails>activityData = buildActivityData(processEntity);
			process.setActivityData(activityData);
			
			Set<DCWorksLiveTransitionDetails>transitionData = buildTransitionData(processEntity);
			process.setTransitionData(transitionData);
			return process;
		}catch(Exception e) {
			throw e;
		}
	}
	
	private Set<DCWorksLiveActivityDetails> buildActivityData(DCWorksLiveProcess processEntity) {
		Set<DCWorksLiveActivityDetails> activiltyData = new HashSet<>();
		
		Set<DCWorksLiveActivity> activiltyEntityData = processEntity.getActivityDetails();
		for(DCWorksLiveActivity activityObj : activiltyEntityData) {

			ModelMapper activityModelMapper = new ModelMapper();
			activityModelMapper.getConfiguration()
			  .setMatchingStrategy(MatchingStrategies.STRICT);
			DCWorksLiveActivityDetails activity = activityModelMapper.map(activityObj, DCWorksLiveActivityDetails.class);
			
			Set<DCWorksLiveNodeGraphicsInfoDetails> graphicsNodeData = new HashSet<>();
			Set<DCWorksLiveActivityNodeGraphicsInfo>nodeGraphicsEntityData = activityObj.getNodeGraphicsInfo();
			for(DCWorksLiveActivityNodeGraphicsInfo graphicsNodeObj : nodeGraphicsEntityData) {
				ModelMapper graphicsNodeModelMapper = new ModelMapper();
				graphicsNodeModelMapper.getConfiguration()
				  .setMatchingStrategy(MatchingStrategies.STRICT);
				DCWorksLiveNodeGraphicsInfoDetails graphicsNode = graphicsNodeModelMapper.map(graphicsNodeObj, DCWorksLiveNodeGraphicsInfoDetails.class);
				graphicsNodeData.add(graphicsNode);
			}
			activity.setGraphicsNodeData(graphicsNodeData);
			activiltyData.add(activity);
		}
		return activiltyData;
	}
	
	private Set<DCWorksLiveTransitionDetails> buildTransitionData(DCWorksLiveProcess processEntity) {
		Set<DCWorksLiveTransitionDetails> transitionData = new HashSet<>();
		
		Set<DCWorksLiveTransition> transitionEntityData = processEntity.getTransitionDetails();
		for(DCWorksLiveTransition transitionObj : transitionEntityData) {
			ModelMapper transitionModelMapper = new ModelMapper();
			transitionModelMapper.getConfiguration()
			  .setMatchingStrategy(MatchingStrategies.STRICT);
			DCWorksLiveTransitionDetails transition = transitionModelMapper.map(transitionObj, DCWorksLiveTransitionDetails.class);
			transition.setFromActivityId(transitionObj.getFromActivity().getDcWorksActivityId());
			transition.setToActivityId(transitionObj.getToActivity().getDcWorksActivityId());
			transitionData.add(transition);
		}
		return transitionData;
	}
	
	@Transactional
	public BaseResponse<GenericResponse> saveFile(String filePath,String fileName) {
		BaseResponse<GenericResponse> responseDTO = new BaseResponse<>();
		try {
			Document doc = DCWorksUtil.getXMLFileDom(filePath,fileName);
			
			Set<DCWorksLivePool> pool = parsePool(doc);
			poolRepository.save(pool);
			
		} catch (Exception e) {
			//logs exception
			logger.error("This is Error message", e);
			throw new ServiceException(DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorCode(), DCWorksLiveErrorCode.GENERIC_ERROR_CODE.getErrorMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		GenericResponse response = new GenericResponse(DCWorksLiveConstant.PROCESS_FILE_SAVED_SUCCESS_MSG);
		responseDTO.setData(response);
		responseDTO.setMeta(new Meta(DCWorksLiveConstant.SUCCESS_CODE, DCWorksLiveConstant.SUCCESS_MESSAGE,
				DCWorksLiveConstant.SUCCESS));
		return responseDTO;
	}
	
	private Set<DCWorksLivePool> parsePool(Document doc) throws Exception {
		Set<DCWorksLivePool> poolObjectSet = new HashSet<>();
		NodeList poolList = doc.getElementsByTagName("Pool");
		for (int i =0;i<poolList.getLength();i++) {
			Node poolNode = poolList.item(i);
			if (poolNode.getNodeType() == Node.ELEMENT_NODE) {
				DCWorksLivePool poolObject = new  DCWorksLivePool();
				Element poolElement = (Element) poolNode;
				poolObject.setPoolId(poolElement.getAttribute("Id"));
				poolObject.setPoolName(poolElement.getAttribute("Name"));
				poolObject.setOrientation(poolElement.getAttribute("Orientation"));
				poolObject.setBoundayVisible(poolElement.getAttribute("BoundaryVisible"));
				
				Set<DCWorksLivePoolLane> lane = parseLane(doc,poolObject);
				poolObject.setLaneDetails(lane);
				
				Set<DCWorksLiveProcess> processSet = parseProcess(doc,poolObject);
				DCWorksLiveProcess process = processSet.stream()                        
		                .filter(x -> poolElement.getAttribute("Process").equals(x.getProcessId())) 
		                .findAny()                                      
		                .orElse(null);  
				if(process != null) {
					poolObject.setProcessDetails(process);
				}
				poolObjectSet.add(poolObject);
			}	
		}
		
		return poolObjectSet;
	}
	
	private Set<DCWorksLivePoolLane> parseLane(Document doc,DCWorksLivePool poolObject) throws Exception {
		Set<DCWorksLivePoolLane> laneSet = new HashSet<>();
		NodeList laneList = doc.getElementsByTagName("Lane");
		System.out.println(laneList.getLength());
		for (int temp = 0; temp < laneList.getLength(); temp++) {
			Node laneNode = laneList.item(temp);
			DCWorksLivePoolLane laneObject = new DCWorksLivePoolLane();
			laneObject.setPool(poolObject);
			if (laneNode.getNodeType() == Node.ELEMENT_NODE) {
				Element laneElement = (Element) laneNode;
				laneObject.setLaneId(laneElement.getAttribute("Id"));
				laneObject.setLaneName(laneElement.getAttribute("Name"));
				if (laneNode.hasChildNodes()) {
					laneObject.setLaneNodeGraphicsInfo(getLaneNodeGraphics(laneObject,laneElement));
				}
				laneSet.add(laneObject);
			}
		}
		return laneSet;
	}
	
	private Set<DCWorksLiveLaneNodeGraphicsInfo> getLaneNodeGraphics(DCWorksLivePoolLane laneObject,Element laneElement){
		Set<DCWorksLiveLaneNodeGraphicsInfo> laneGraphicsNodeInfoSet = new HashSet<DCWorksLiveLaneNodeGraphicsInfo>();
		Element graphicInfoElement = (Element) laneElement.getElementsByTagName("NodeGraphicsInfo")
				.item(0);
		DCWorksLiveLaneNodeGraphicsInfo graphicsInfo = new DCWorksLiveLaneNodeGraphicsInfo();
		graphicsInfo.setLaneDetail(laneObject);
		graphicsInfo.setHeight(graphicInfoElement.getAttribute("Height"));
		graphicsInfo.setWidth(graphicInfoElement.getAttribute("Width"));
		
		Element coordinatesElement = (Element) laneElement.getElementsByTagName("Coordinates").item(0);
		String xCoord = coordinatesElement.getAttribute("XCoordinate");
		String yCoord = coordinatesElement.getAttribute("YCoordinate");
		graphicsInfo.setCoordinate(xCoord + "," + yCoord);
		
		laneGraphicsNodeInfoSet.add(graphicsInfo);
		return laneGraphicsNodeInfoSet;
	}
	
	private Set<DCWorksLiveProcess> parseProcess(Document doc,DCWorksLivePool poolObject) throws Exception {
		
		Set<DCWorksLiveProcess> processSet = new HashSet<>();
		NodeList processList = doc.getElementsByTagName("WorkflowProcess");
		for(int i=0;i<processList.getLength();i++) {
			Node processNode = processList.item(i);
			Element processElement = (Element) processNode;
			DCWorksLiveProcess processObject = new DCWorksLiveProcess();
			processObject.setPool(poolObject);
			processObject.setProcessId(processElement.getAttribute("Id"));
			processObject.setProcessName(processElement.getAttribute("Name"));
			processObject.setProcessType(processElement.getAttribute("ProcessType"));
			processObject.setStatus(processElement.getAttribute("Status"));
			
			Set<DCWorksLiveActivity> processActivityList   = new HashSet<>();;
			NodeList activityList = doc.getElementsByTagName("Activity");
			System.out.println(activityList.getLength());
			for (int temp = 0; temp < activityList.getLength(); temp++) {
				Node activityNode = activityList.item(temp);
				DCWorksLiveActivity activityObject = new DCWorksLiveActivity();
				activityObject.setProcessDetail(processObject);
				if (activityNode.getNodeType() == Node.ELEMENT_NODE) {
					Element activityElement = (Element) activityNode;
					activityObject.setActivityId(activityElement.getAttribute("Id"));
					activityObject.setActivityName(activityElement.getAttribute("Name"));
					if (activityNode.hasChildNodes()) {
						String activityType = getActivityType(activityElement);
						if(activityType != null) {
							activityObject.setActivityType(activityType);
						}
						activityObject.setNodeGraphicsInfo(getNodeGraphics(activityObject,poolObject.getLaneDetails(),activityElement));
					}
					processActivityList.add(activityObject);
				}
			}
			processObject.setActivityDetails(processActivityList);
			
			Set<DCWorksLiveTransition> transitions = parseTransition(doc,processObject);
			processObject.setTransitionDetails(transitions);
			
			
			processSet.add(processObject);
		}
		
		return  processSet;
	}
	
	private Set<DCWorksLiveActivityNodeGraphicsInfo> getNodeGraphics(DCWorksLiveActivity activityObject, Set<DCWorksLivePoolLane> laneSet,Element activityElement){
		Element graphicInfoElement = (Element) activityElement.getElementsByTagName("NodeGraphicsInfo")
				.item(0);
		Set<DCWorksLiveActivityNodeGraphicsInfo> nodeGraphicsInfoSet = new HashSet<DCWorksLiveActivityNodeGraphicsInfo>();
		DCWorksLiveActivityNodeGraphicsInfo graphicsInfo = new DCWorksLiveActivityNodeGraphicsInfo();
		graphicsInfo.setActivityDetail(activityObject);
		graphicsInfo.setLaneId(graphicInfoElement.getAttribute("LaneId"));
		graphicsInfo.setHeight(graphicInfoElement.getAttribute("Height"));
		graphicsInfo.setWidth(graphicInfoElement.getAttribute("Width"));
		
		DCWorksLivePoolLane lane = laneSet.stream()                        
                .filter(x -> graphicInfoElement.getAttribute("LaneId").equals(x.getLaneId())) 
                .findAny()                                      
                .orElse(null);  
		if(lane != null) {
		   activityObject.setLaneDetail(lane);
		}
		
		Element coordinatesElement = (Element) activityElement.getElementsByTagName("Coordinates").item(0);
		String xCoord = coordinatesElement.getAttribute("XCoordinate");
		String yCoord = coordinatesElement.getAttribute("YCoordinate");
		graphicsInfo.setCoordinate(xCoord + "," + yCoord);
		
		String borderColor = coordinatesElement.getAttribute("BorderColor");
		if(borderColor != null) {
			graphicsInfo.setBorderColor(borderColor);
		}
		String fillColor = coordinatesElement.getAttribute("FillColor");
		if(fillColor != null) {
			graphicsInfo.setFillColor(fillColor);
		}
		nodeGraphicsInfoSet.add(graphicsInfo);
		return nodeGraphicsInfoSet;
	}
		
	private String getActivityType(Element activityElement) {
		Node eventNode = (Element) activityElement.getElementsByTagName("Event")
				.item(0);
		if(eventNode != null) {
			Element activityType = (Element) eventNode;
			return activityType.getNodeName();
		}

		Node taskNode = (Element) activityElement.getElementsByTagName("Implementation")
				.item(0);
		if(taskNode != null) {
			Element activityType = (Element) taskNode;
			return activityType.getNodeName();
		}
		
		Node routeNode = (Element) activityElement.getElementsByTagName("Route")
				.item(0);
		if(routeNode != null) {
			Element activityType = (Element) routeNode;
			return activityType.getNodeName();
		}
		
		return null;
	}
	
    private Set<DCWorksLiveTransition> parseTransition(Document doc,DCWorksLiveProcess process) throws Exception {
		
    	Set<DCWorksLiveActivity> activityList = process.getActivityDetails();
    	Set<DCWorksLiveTransition> transitionList = new HashSet<>();
		NodeList transitionNodeList = doc.getElementsByTagName("Transition");
		
		for (int temp = 0; temp < transitionNodeList.getLength(); temp++) {
			Node transitionNode = transitionNodeList.item(temp);
			Element transitionElement = (Element) transitionNode;
			DCWorksLiveTransition transitionObject = new DCWorksLiveTransition();
			transitionObject.setTransitionId(transitionElement.getAttribute("Id"));
			transitionObject.setProcessDetail(process);
			DCWorksLiveActivity fromActivity = activityList.stream()                        
	                .filter(x -> transitionElement.getAttribute("From").equals(x.getActivityId())) 
	                .findAny()                                      
	                .orElse(null);  
			if(fromActivity != null) {
				Set<DCWorksLiveTransition> transitionSet = fromActivity.getTransitionsOriginated();
				if(transitionSet == null) {
					transitionSet = new HashSet<DCWorksLiveTransition>();
				}
				transitionSet.add(transitionObject);
				transitionObject.setFromActivity(fromActivity);
			}
			
			DCWorksLiveActivity toActivity = activityList.stream()                        
	                .filter(x -> transitionElement.getAttribute("To").equals(x.getActivityId())) 
	                .findAny()                                      
	                .orElse(null);  
			if(toActivity != null) {
				Set<DCWorksLiveTransition> transitionSet = fromActivity.getTransitionsEnd();
				if(transitionSet == null) {
					transitionSet = new HashSet<DCWorksLiveTransition>();
				}
				transitionSet.add(transitionObject);
				transitionObject.setToActivity(toActivity);
			}
			
			NodeList coordinateList = transitionElement.getElementsByTagName("Coordinates");
			String pathCoordinates = "";
			for (int i = 0; i < coordinateList.getLength(); i++) {
				Node coordinateNode = coordinateList.item(i);
				if (coordinateNode.getNodeType() == Node.ELEMENT_NODE) {
					Element coordinateElement = (Element) coordinateNode;
					String xCoordinate = coordinateElement.getAttribute("XCoordinate");
					String yCoordinate = coordinateElement.getAttribute("YCoordinate");
					if(pathCoordinates.length()==0) {
						pathCoordinates = pathCoordinates + xCoordinate + "," + yCoordinate;
					}else {
						pathCoordinates = pathCoordinates + "," + xCoordinate + "," + yCoordinate;
					}
				}
			}
			transitionObject.setCordinates(pathCoordinates);
			transitionList.add(transitionObject);
		}
		return transitionList;
	}
}
