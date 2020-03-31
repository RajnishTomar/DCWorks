package com.hcl.bpm.dcworks.response;

import java.io.Serializable;
import java.util.Set;

import com.hcl.bpm.dcworks.response.DCWorksLiveProcessDetailsResponse.Process;


public class DCWorksLivePoolDetailsResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	DCWorksLivePoolDetails poolData;
	
	static public class DCWorksLivePoolDetails {

		private Long dcWorksPoolId;
		
		private String poolId;

		private String poolName;
		
		private String orientation;
		
		private String boundayVisible;
		
		Set<DCWorksLiveLaneDetails> laneData;
		
		Process processData;

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
		
		public Process getProcessData() {
			return processData;
		}

		public void setProcessData(Process processData) {
			this.processData = processData;
		}

		public Set<DCWorksLiveLaneDetails> getLaneData() {
			return laneData;
		}

		public void setLaneData(Set<DCWorksLiveLaneDetails> laneData) {
			this.laneData = laneData;
		}

		@Override
		public String toString() {
			return "DCWorksLivePoolDetails [dcWorksPoolId=" + dcWorksPoolId + ", poolId=" + poolId + ", poolName="
					+ poolName + ", orientation=" + orientation + ", boundayVisible=" + boundayVisible + ", laneData="
					+ laneData + ", processData=" + processData + "]";
		}

		
	}

	public DCWorksLivePoolDetails getPoolData() {
		return poolData;
	}

	public void setPoolData(DCWorksLivePoolDetails poolData) {
		this.poolData = poolData;
	}

	@Override
	public String toString() {
		return "DCWorksLivePoolDetailsResponse [poolData=" + poolData + "]";
	}
	
	
	
}
