package com.hcl.bpm.dcworks.response;

import java.io.Serializable;

public class DCWorksLiveLaneNodeGraphicsInfoDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long dcWorksLaneNodeGraphicsId;
	private String height;
	private String width;
	private String coordinate;
	
	
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
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	
	@Override
	public String toString() {
		return "DCWorksLiveLaneNodeGraphicsInfoDetails [nodeGraphicsId=" + dcWorksLaneNodeGraphicsId + ", height=" + height
				+ ", width=" + width + ", coordinate=" + coordinate + "]";
	}
	
}
