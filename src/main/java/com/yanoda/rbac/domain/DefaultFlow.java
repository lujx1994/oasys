package com.yanoda.rbac.domain;

public class DefaultFlow {
	
	private int id;
	private String name;
	private String participate;
	private int workform_id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParticipate() {
		return participate;
	}
	public void setParticipate(String participate) {
		this.participate = participate;
	}
	public int getWorkform_id() {
		return workform_id;
	}
	public void setWorkform_id(int workform_id) {
		this.workform_id = workform_id;
	}

}
