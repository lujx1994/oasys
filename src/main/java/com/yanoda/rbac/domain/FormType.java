package com.yanoda.rbac.domain;

import java.util.List;

public class FormType {
	private int id;
	private String name;
	
	private List<Form> formList;
	
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
	public List<Form> getFormList() {
		return formList;
	}
	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}

}
