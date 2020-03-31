package com.hcl.bpm.dcworks.response;

public class Meta {

	/** The code. */
	private String code;
	
	/** The description. */
	private String description;
	
	/** The status. */
	private int status;
	
	
	
	public Meta() {
		super();
	}
	
	public Meta(String code, String description, int status) {
		super();
		this.code = code;
		this.description = description;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Meta [code=" + code + ", description=" + description + ", status=" + status + "]";
	}
	
	
	
}
