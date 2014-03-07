package com.jtang.model;

public class Product {
	
	private String name;
	private int id;
	private int count;
	private int unit;
	private String msg;
	private String storageId;
	
	public String getStorageId() {
		return storageId;
	}
	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
