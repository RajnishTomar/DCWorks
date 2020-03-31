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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DCWORKSLIVE_POOL")
public class DCWorksLivePool implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DCWORKSLIVE_POOL_ID", updatable = false, nullable = false)
	private Long dcWorksPoolId;
	
	@Column(name = "POOL_ID")
	private String poolId;
	
	@OneToOne(mappedBy="pool", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private DCWorksLiveProcess processDetails;
	
	@OneToMany(mappedBy="pool", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DCWorksLivePoolLane> laneDetails;
	

	@Column(name = "POOL_NAME")
	private String poolName;
	
	@Column(name = "ORIENTATION")
	private String orientation;
	
	@Column(name = "BOUNDARY_VISIBLE")
	private String boundayVisible;

	public Long getDcWorksPoolId() {
		return dcWorksPoolId;
	}

	public void setDcWorksPoolId(Long dcWorksPoolId) {
		this.dcWorksPoolId = dcWorksPoolId;
	}

	public String getPoolId() {
		return poolId;
	}

	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}

	public DCWorksLiveProcess getProcessDetails() {
		return processDetails;
	}

	public void setProcessDetails(DCWorksLiveProcess processDetails) {
		this.processDetails = processDetails;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getBoundayVisible() {
		return boundayVisible;
	}

	public void setBoundayVisible(String boundayVisible) {
		this.boundayVisible = boundayVisible;
	}

	public Set<DCWorksLivePoolLane> getLaneDetails() {
		return laneDetails;
	}

	public void setLaneDetails(Set<DCWorksLivePoolLane> laneDetails) {
		this.laneDetails = laneDetails;
	}
	
	
	
}
