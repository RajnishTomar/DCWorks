package com.hcl.bpm.dcworks.response;

import java.io.Serializable;

public class DCWorksLiveTransitionDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long dcWorksTransitionId;
	private String transitionId;
	private Long fromActivityId;
	private Long toActivityId;
	private String cordinates;
	public Long getDcWorksTransitionId() {
		return dcWorksTransitionId;
	}
	public void setDcWorksTransitionId(Long dcWorksTransitionId) {
		this.dcWorksTransitionId = dcWorksTransitionId;
	}
	public String getTransitionId() {
		return transitionId;
	}
	public void setTransitionId(String transitionId) {
		this.transitionId = transitionId;
	}
	public Long getFromActivityId() {
		return fromActivityId;
	}
	public void setFromActivityId(Long fromActivityId) {
		this.fromActivityId = fromActivityId;
	}
	public Long getToActivityId() {
		return toActivityId;
	}
	public void setToActivityId(Long toActivityId) {
		this.toActivityId = toActivityId;
	}
	public String getCordinates() {
		return cordinates;
	}
	public void setCordinates(String cordinates) {
		this.cordinates = cordinates;
	}
	@Override
	public String toString() {
		return "DCWorksLiveTransitionDetails [dcWorksTransitionId=" + dcWorksTransitionId + ", transitionId="
				+ transitionId + ", fromActivityId=" + fromActivityId + ", toActivityId=" + toActivityId
				+ ", cordinates=" + cordinates + "]";
	}

	

}
