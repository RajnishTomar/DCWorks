package com.hcl.bpm.dcworks.response;

import java.io.Serializable;
import java.util.Set;

public class DCWorksLiveLaneDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long dcWorksLaneId;
	private String laneId;
	private String laneName;
	
	Set<DCWorksLiveLaneNodeGraphicsInfoDetails> graphicsNodeData;
	
	public Long getDcWorksLaneId() {
		return dcWorksLaneId;
	}
	public void setDcWorksLaneId(Long dcWorksLaneId) {
		this.dcWorksLaneId = dcWorksLaneId;
	}
	public String getLaneId() {
		return laneId;
	}
	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}
	public String getLaneName() {
		return laneName;
	}
	public void setLaneName(String laneName) {
		this.laneName = laneName;
	}
	
	public Set<DCWorksLiveLaneNodeGraphicsInfoDetails> getGraphicsNodeData() {
		return graphicsNodeData;
	}
	public void setGraphicsNodeData(Set<DCWorksLiveLaneNodeGraphicsInfoDetails> graphicsNodeData) {
		this.graphicsNodeData = graphicsNodeData;
	}
	@Override
	public String toString() {
		return "DCWorksLiveLaneDetails [dcWorksLaneId=" + dcWorksLaneId + ", laneId=" + laneId + ", laneName="
				+ laneName + ", graphicsNodeData=" + graphicsNodeData + "]";
	}
	
	
	
}
