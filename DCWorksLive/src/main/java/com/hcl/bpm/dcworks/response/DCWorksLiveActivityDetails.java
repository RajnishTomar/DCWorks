package com.hcl.bpm.dcworks.response;

import java.io.Serializable;
import java.util.Set;

public class DCWorksLiveActivityDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long dcWorksActivityId;
	private String activityId;
	private String activityName;
	private String activityType;
	private Set<DCWorksLiveNodeGraphicsInfoDetails> graphicsNodeData;
	
	
	public Long getDcWorksActivityId() {
		return dcWorksActivityId;
	}
	public void setDcWorksActivityId(Long dcWorksActivityId) {
		this.dcWorksActivityId = dcWorksActivityId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public Set<DCWorksLiveNodeGraphicsInfoDetails> getGraphicsNodeData() {
		return graphicsNodeData;
	}
	public void setGraphicsNodeData(Set<DCWorksLiveNodeGraphicsInfoDetails> graphicsNodeData) {
		this.graphicsNodeData = graphicsNodeData;
	}
	@Override
	public String toString() {
		return "DCWorksLiveActivityDetails [dcWorksActivityId=" + dcWorksActivityId + ", activityId=" + activityId
				+ ", activityName=" + activityName + ", activityType=" + activityType + ", graphicsNodeData="
				+ graphicsNodeData + "]";
	}
	
	
}
