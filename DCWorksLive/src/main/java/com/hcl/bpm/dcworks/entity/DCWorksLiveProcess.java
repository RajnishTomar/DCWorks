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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DCWORKSLIVE_PROCESS")
public class DCWorksLiveProcess implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DCWORKSLIVE_PROCESS_ID", updatable = false, nullable = false)
	private Long dcWorksProcessId;
	
	@Column(name = "PROCESS_ID")
	private String processId;
	
//	@Column(name = "DCWORKSLIVE_POOL_ID")
//	private Long dcWorksPoolId;
	
	@OneToOne
	@JoinColumn(name="DCWORKSLIVE_POOL_ID",insertable = true,updatable = false)
	private DCWorksLivePool pool;
	
	@OneToMany(mappedBy="processDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DCWorksLiveActivity> activityDetails;
	
	@OneToMany(mappedBy="processDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DCWorksLiveTransition> transitionDetails;
	

	@Column(name = "PROCESS_NAME")
	private String processName;
	
	@Column(name = "PROCESS_TYPE")
	private String processType;

	@Column(name = "STATUS")
	private String status;
	
	
	public Long getDcWorksProcessId() {
		return dcWorksProcessId;
	}

	public void setDcWorksProcessId(Long dcWorksProcessId) {
		this.dcWorksProcessId = dcWorksProcessId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public Set<DCWorksLiveActivity> getActivityDetails() {
		return activityDetails;
	}

	public void setActivityDetails(Set<DCWorksLiveActivity> activityDetails) {
		this.activityDetails = activityDetails;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<DCWorksLiveTransition> getTransitionDetails() {
		return transitionDetails;
	}

	public void setTransitionDetails(Set<DCWorksLiveTransition> transitionDetails) {
		this.transitionDetails = transitionDetails;
	}

	

	public DCWorksLivePool getPool() {
		return pool;
	}

	public void setPool(DCWorksLivePool pool) {
		this.pool = pool;
	}

//	public Long getDcWorksPoolId() {
//		return dcWorksPoolId;
//	}
//
//	public void setDcWorksPoolId(Long dcWorksPoolId) {
//		this.dcWorksPoolId = dcWorksPoolId;
//	}

	
	
}
