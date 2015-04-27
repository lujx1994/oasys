package com.yanoda.rbac.domain;


public class Workflow {
	
	private int id;
	private String role_flow;
	private String user_flow;
	private String custom;
	private int create_user_id;
	private byte[] content;
	private int status;
	private String create_time;
	private String name;
	

	public String getCustom() {
		return custom;
	}
	public void setCustom(String custom) {
		this.custom = custom;
	}
	public int getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(int create_user_id) {
		this.create_user_id = create_user_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole_flow() {
		return role_flow;
	}
	public void setRole_flow(String role_flow) {
		this.role_flow = role_flow;
	}
	public String getUser_flow() {
		return user_flow;
	}
	public void setUser_flow(String user_flow) {
		this.user_flow = user_flow;
	}
	
	

}
