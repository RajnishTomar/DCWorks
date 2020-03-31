package com.hcl.bpm.dcworks.response;

public class ErrorResponse {

	/** The meta. */
	private Meta meta;
	
	public ErrorResponse() {
		super();
	}

	public ErrorResponse(Meta meta) {
		super();
		this.meta = meta;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return "ErrorResponse [meta=" + meta + "]";
	}
	
	
	
}
