package com.hcl.bpm.dcworks.response;

import java.util.Set;

public class DCWorksLiveProcessDetailsResponse {

	private Process process;
	
	public static class Process {
		
		private Long dcWorksProcessId;
		private String processId;
		private String processName;
		private String processType;
		private String status;
		private Set<DCWorksLiveActivityDetails> activityData;
		private Set<DCWorksLiveTransitionDetails> transitionData;
		
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
		public Set<DCWorksLiveActivityDetails> getActivityData() {
			return activityData;
		}
		public void setActivityData(Set<DCWorksLiveActivityDetails> activityData) {
			this.activityData = activityData;
		}
		public Set<DCWorksLiveTransitionDetails> getTransitionData() {
			return transitionData;
		}
		public void setTransitionData(Set<DCWorksLiveTransitionDetails> transitionData) {
			this.transitionData = transitionData;
		}
		@Override
		public String toString() {
			return "Process [dcWorksProcessId=" + dcWorksProcessId + ", processId=" + processId + ", processName="
					+ processName + ", processType=" + processType + ", status=" + status + ", activityData="
					+ activityData + ", transitionData=" + transitionData + "]";
		}
		
		
				
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	@Override
	public String toString() {
		return "DCWorksLiveProcessDetailsResponse [process=" + process + "]";
	}
	
	
	
}
