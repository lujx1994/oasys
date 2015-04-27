package com.yanoda.rbac.domain;

public class Delegate {
	private int id;
	private int user_id;
	private int delegate_id;
	private int enabled;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getDelegate_id() {
		return delegate_id;
	}
	public void setDelegate_id(int delegate_id) {
		this.delegate_id = delegate_id;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

}
