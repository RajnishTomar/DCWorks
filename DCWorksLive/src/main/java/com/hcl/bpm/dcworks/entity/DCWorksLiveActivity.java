package com.hcl.bpm.dcworks.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DCWORKSLIVE_ACTIVITY")
public class DCWorksLiveActivity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DCWORKSLIVE_ACTIVITY_ID", updatable = false, nullable = false)
	private Long dcWorksActivityId;
	
	@Column(name = "ACTIVITY_ID")
	private String activityId;
	
//	@Column(name = "DCWORKSLIVE_PROCESS_ID")
//	private Long dcWorksProcessId;
//	
//	@Column(name = "DCWORKSLIVE_LANE_ID")
//	private Long dcWorksLaneId;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DCWORKSLIVE_PROCESS_ID",insertable = true,nullable = false,updatable = false)
	private DCWorksLiveProcess processDetail;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DCWORKSLIVE_LANE_ID",insertable = true,nullable = false,updatable = false)
	private DCWorksLivePoolLane laneDetail;
	
	
	@OneToMany(mappedBy="activityDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DCWorksLiveActivityNodeGraphicsInfo> nodeGraphicsInfo;
	
	@OneToMany(mappedBy="fromActivity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DCWorksLiveTransition> transitionsOriginated;
	
	@OneToMany(mappedBy="toActivity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DCWorksLiveTransition> transitionsEnd;
	
	@Column(name = "ACTIVITY_NAME")
	private String activityName;
	
	@Column(name = "ACTIVITY_TYPE")
	private String activityType;


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

	public DCWorksLiveProcess getProcessDetail() {
		return processDetail;
	}

	public void setProcessDetail(DCWorksLiveProcess processDetail) {
		this.processDetail = processDetail;
	}

	public Set<DCWorksLiveActivityNodeGraphicsInfo> getNodeGraphicsInfo() {
		return nodeGraphicsInfo;
	}

	public void setNodeGraphicsInfo(Set<DCWorksLiveActivityNodeGraphicsInfo> nodeGraphicsInfo) {
		this.nodeGraphicsInfo = nodeGraphicsInfo;
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

	public Set<DCWorksLiveTransition> getTransitionsOriginated() {
		return transitionsOriginated;
	}

	public void setTransitionsOriginated(Set<DCWorksLiveTransition> transitionsOriginated) {
		this.transitionsOriginated = transitionsOriginated;
	}

	public Set<DCWorksLiveTransition> getTransitionsEnd() {
		return transitionsEnd;
	}

	public void setTransitionsEnd(Set<DCWorksLiveTransition> transitionsEnd) {
		this.transitionsEnd = transitionsEnd;
	}

//	public Long getDcWorksProcessId() {
//		return dcWorksProcessId;
//	}
//
//	public void setDcWorksProcessId(Long dcWorksProcessId) {
//		this.dcWorksProcessId = dcWorksProcessId;
//	}
//
//	public Long getDcWorksLaneId() {
//		return dcWorksLaneId;
//	}
//
//	public void setDcWorksLaneId(Long dcWorksLaneId) {
//		this.dcWorksLaneId = dcWorksLaneId;
//	}

	public DCWorksLivePoolLane getLaneDetail() {
		return laneDetail;
	}

	public void setLaneDetail(DCWorksLivePoolLane laneDetail) {
		this.laneDetail = laneDetail;
	}	
	
	
	
}
