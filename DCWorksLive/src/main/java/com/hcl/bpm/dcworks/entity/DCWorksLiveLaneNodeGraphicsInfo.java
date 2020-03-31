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
import javax.persistence.Table;

@Entity
@Table(name = "DCWORKSLIVE_LANE_NODE_GRAPHICS_INFO")
public class DCWorksLiveLaneNodeGraphicsInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DCWORKSLIVE_LANE_NODE_GRAPHICS_ID", updatable = false, nullable = false)
	private Long dcWorksLaneNodeGraphicsId;
	
	@Column(name = "HEIGHT")
	private String height;
	
	@Column(name = "WIDTH")
	private String width;

	@Column(name = "COORDINATE")
	private String coordinate;
	
//	@Column(name = "DCWORKSLIVE_LANE_ID")
//	private Long dcWorksLaneId;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DCWORKSLIVE_LANE_ID",insertable = true,nullable = false,updatable = false)
	private DCWorksLivePoolLane laneDetail;

	public Long getDcWorksLaneNodeGraphicsId() {
		return dcWorksLaneNodeGraphicsId;
	}

	public void setDcWorksLaneNodeGraphicsId(Long dcWorksLaneNodeGraphicsId) {
		this.dcWorksLaneNodeGraphicsId = dcWorksLaneNodeGraphicsId;
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

	public void setCoordinate(String coordinat) {
		this.coordinate = coordinat;
	}

	public DCWorksLivePoolLane getLaneDetail() {
		return laneDetail;
	}

	public void setLaneDetail(DCWorksLivePoolLane laneDetail) {
		this.laneDetail = laneDetail;
	}

//	public Long getDcWorksLaneId() {
//		return dcWorksLaneId;
//	}
//
//	public void setDcWorksLaneId(Long dcWorksLaneId) {
//		this.dcWorksLaneId = dcWorksLaneId;
//	}
	
	

	

}
