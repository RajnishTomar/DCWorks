package com.hcl.bpm.dcworks.response;

import java.io.Serializable;

import com.hcl.bpm.dcworks.entity.DCWorksLiveActivity;

public class DCWorksLiveNodeGraphicsInfoDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long nodeGraphicsId;
	private String laneId;
	private String height;
	private String width;
	private String coordinate;
	private String borderColor;
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
	@Override
	public String toString() {
		return "DCWorksLiveNodeGraphicsInfoDetails [nodeGraphicsId=" + nodeGraphicsId + ", laneId=" + laneId
				+ ", height=" + height + ", width=" + width + ", coordinate=" + coordinate + ", borderColor="
				+ borderColor + ", fillColor=" + fillColor + "]";
	}
	
	
	
}
