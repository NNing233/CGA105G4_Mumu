package com.accessfunc.model;

public class AccessFuncVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer accessFunctionId;
	private String accessFunctionName;
	
	public Integer getAccessFunctionId() {
		return accessFunctionId;
	}
	public void setAccessFunctionId(Integer accessFunctionId) {
		this.accessFunctionId = accessFunctionId;
	}
	public String getAccessFunctionName() {
		return accessFunctionName;
	}
	public void setAccessFunctionName(String accessFunctionName) {
		this.accessFunctionName = accessFunctionName;
	}

}
