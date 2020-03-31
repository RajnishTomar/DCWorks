package com.hcl.bpm.dcworks.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DCWORKSLIVE_TRANSITION")
public class DCWorksLiveTransition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DCWORKSLIVE_TRANSITION_ID", updatable = false, nullable = false)
	private Long dcWorksTransitionId;
	
	@Column(name = "TRANSITION_ID")
	private String transitionId;
	
	@Column(name = "COORDINATES")
	private String cordinates;
	
//	@Column(name = "DCWORKSLIVE_ACTIVITY_FROM_ID")
//	private Long dcWorksFromActivityId;
//	
//	@Column(name = "DCWORKSLIVE_ACTIVITY_TO_ID")
//	private Long dcWorksToActivityId;
//	
//	@Column(name = "DCWORKSLIVE_PROCESS_ID")
//	private Long dcWorksProcessId;
	
	@ManyToOne
	@JoinColumn(name="DCWORKSLIVE_ACTIVITY_FROM_ID",insertable = true,updatable = false)
	private DCWorksLiveActivity fromActivity;
	
	@ManyToOne
	@JoinColumn(name="DCWORKSLIVE_ACTIVITY_TO_ID",insertable = true,updatable = false)
	private DCWorksLiveActivity toActivity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DCWORKSLIVE_PROCESS_ID",insertable = true,nullable = false,updatable = false)
	private DCWorksLiveProcess processDetail;

	
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

	public String getCordinates() {
		return cordinates;
	}

	public void setCordinates(String cordinates) {
		this.cordinates = cordinates;
	}

	public DCWorksLiveActivity getFromActivity() {
		return fromActivity;
	}

	public void setFromActivity(DCWorksLiveActivity fromActivity) {
		this.fromActivity = fromActivity;
	}

	public DCWorksLiveActivity getToActivity() {
		return toActivity;
	}

	public void setToActivity(DCWorksLiveActivity toActivity) {
		this.toActivity = toActivity;
	}

	public DCWorksLiveProcess getProcessDetail() {
		return processDetail;
	}

	public void setProcessDetail(DCWorksLiveProcess processDetail) {
		this.processDetail = processDetail;
	}

//	public Long getDcWorksFromActivityId() {
//		return dcWorksFromActivityId;
//	}
//
//	public void setDcWorksFromActivityId(Long dcWorksFromActivityId) {
//		this.dcWorksFromActivityId = dcWorksFromActivityId;
//	}
//
//	public Long getDcWorksToActivityId() {
//		return dcWorksToActivityId;
//	}
//
//	public void setDcWorksToActivityId(Long dcWorksToActivityId) {
//		this.dcWorksToActivityId = dcWorksToActivityId;
//	}
//
//	public Long getDcWorksProcessId() {
//		return dcWorksProcessId;
//	}
//
//	public void setDcWorksProcessId(Long dcWorksProcessId) {
//		this.dcWorksProcessId = dcWorksProcessId;
//	}
	
	
	
	
	
}
