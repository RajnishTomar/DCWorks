package com.hcl.bpm.dcworks.response;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 /** The meta. */
    private Meta meta;
    
    /** The data. */
    private T data;
    
	public BaseResponse() {
		super();
	}

	public BaseResponse(Meta meta, T data) {
		super();
		this.meta = meta;
		this.data = data;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BaseResponse [meta=" + meta + ", data=" + data + "]";
	}

	
    
	 
}
