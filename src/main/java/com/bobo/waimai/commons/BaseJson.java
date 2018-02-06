package com.bobo.waimai.commons;

import java.io.Serializable;

public class BaseJson implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int  code;//错误码
	private Object data;//返回的内容
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	public BaseJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BaseJson(int code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}
	/**
	 * 创建一个返回成功的对象
	 * @param data
	 * @return
	 */
	public static BaseJson setOK(Object data) {
		BaseJson baseJson=new BaseJson();
		baseJson.setCode(GlobalFianlVar.SUCCESS);
		baseJson.setData(data);
		return baseJson;
	}
	
	public static BaseJson setError(int code,Object data) {
		BaseJson baseJson=new BaseJson(code,data);
		return baseJson;
	}
	@Override
	public String toString() {
		return "BaseJson [code=" + code + ", data=" + data + "-----class==="+data.getClass() +"]";
		//BaseJson [code=1, data=BaseJson [code=1, data=这是内部的内容]]
	}
	
}
