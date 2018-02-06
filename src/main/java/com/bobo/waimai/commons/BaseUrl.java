package com.bobo.waimai.commons;

import java.io.Serializable;

public class BaseUrl implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int  error;
	private Object url;//返回的内容

	public void setError(int error) {
		this.error = error;
	}

	public int getError() {
		return error;
	}

	public void setUrl(Object url) {
		this.url = url;
	}

	public Object getUrl() {
		return url;
	}

	public BaseUrl() {
		super();
	}

	public BaseUrl(int error, Object url) {
		this.error = error;
		this.url = url;
	}

	@Override
	public String toString() {
		return "BaseUrl{" +
				"error=" + error +
				", url=" + url +
				'}';
	}
}
