package com.hangdaoju.core;

import java.util.Map;

public class ResponseModel {
	private int ret = 200;
	private Map<String, Object> data;
	private String information;

	public int getRet() {
		return ret;
	}

	public ResponseModel setRet(int ret) {
		this.ret = ret;
		return this;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public ResponseModel setData(Map<String, Object> data) {
		this.data = data;
		return this;
	}

	public String getInformation() {
		return information;
	}

	public ResponseModel setInformation(String information) {
		this.information = information;
		return this;
	}
}
