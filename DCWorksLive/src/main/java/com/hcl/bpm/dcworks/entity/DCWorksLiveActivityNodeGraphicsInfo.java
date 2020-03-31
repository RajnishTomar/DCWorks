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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DCWORKSLIVE_NODE_GRAPHICS_INFO")
public class DCWorksLiveActivityNodeGraphicsInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DCWORKSLIVE_NODE_GRAPHICS_ID", updatable = false, nullable = false)
	private Long nodeGraphicsId;
	
	@Column(name = "LANE_ID")
	private String laneId;
	
//	@Column(name = "DCWORKSLIVE_ACTIVITY_ID")
//	private Long dcWorksActivityId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DCWORKSLIVE_ACTIVITY_ID",insertable = true,nullable = false,updatable = false)
	private DCWorksLiveActivity activityDetail;
	
	@Column(name = "HEIGHT")
	private String height;
	
	@Column(name = "WIDTH")
	private String width;

	@Column(name = "COORDINATE")
	private String coordinate;
	
	@Column(name = "BORDER_COLOR")
	private String borderColor;
	
	@Column(name = "FILL_COLOR")
	private String fillColor;
	
	
	public Long getNodeGraphicsId() {
		return nodeGraphicsId;
	}

	public void setNodeGraphicsId(Long nodeGraphicsId) {
		this.nodeGraphicsId = nodeGraphicsId;
	}

	public String getLaneId() {
		return laneId;
	}

	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}

	public DCWorksLiveActivity getActivityDetail() {
		return activityDetail;
	}

	public void setActivityDetail(DCWorksLiveActivity activityDetail) {
		this.activityDetail = activityDetail;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

//	public Long getDcWorksActivityId() {
//		return dcWorksActivityId;
//	}
//
//	public void setDcWorksActivityId(Long dcWorksActivityId) {
//		this.dcWorksActivityId = dcWorksActivityId;
//	}
	
	
}