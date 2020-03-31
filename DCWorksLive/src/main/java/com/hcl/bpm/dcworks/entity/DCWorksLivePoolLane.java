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
@Table(name = "DCWORKSLIVE_POOL_LANE")
public class DCWorksLivePoolLane implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DCWORKSLIVE_LANE_ID", updatable = false, nullable = false)
	private Long dcWorksLaneId;
	
	@Column(name = "LANE_ID")
	private String laneId;
	
//	@Column(name = "DCWORKSLIVE_POOL_ID")
//	private Long dcWorksPoolId;
	
	@ManyToOne
	@JoinColumn(name="DCWORKSLIVE_POOL_ID",insertable = true,updatable = false)
	private DCWorksLivePool pool;
	
	@OneToMany(mappedBy="laneDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DCWorksLiveLaneNodeGraphicsInfo> laneNodeGraphicsInfo;
	
	@OneToMany(mappedBy="laneDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DCWorksLiveActivity> activityDetails;
	
	@Column(name = "LANE_NAME")
	private String laneName;

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

	public DCWorksLivePool getPool() {
		return pool;
	}

	public void setPool(DCWorksLivePool pool) {
		this.pool = pool;
	}

	public String getLaneName() {
		return laneName;
	}

	public void setLaneName(String laneName) {
		this.laneName = laneName;
	}

	public Set<DCWorksLiveLaneNodeGraphicsInfo> getLaneNodeGraphicsInfo() {
		return laneNodeGraphicsInfo;
	}

	public void setLaneNodeGraphicsInfo(Set<DCWorksLiveLaneNodeGraphicsInfo> laneNodeGraphicsInfo) {
		this.laneNodeGraphicsInfo = laneNodeGraphicsInfo;
	}

	public Set<DCWorksLiveActivity> getActivityDetails() {
		return activityDetails;
	}

	public void setActivityDetails(Set<DCWorksLiveActivity> activityDetails) {
		this.activityDetails = activityDetails;
	}

//	public Long getDcWorksPoolId() {
//		return dcWorksPoolId;
//	}
//
//	public void setDcWorksPoolId(Long dcWorksPoolId) {
//		this.dcWorksPoolId = dcWorksPoolId;
//	}
	
	
	
	
}
