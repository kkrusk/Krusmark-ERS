package com.krusmark.models;

public class UserStatistic {
	protected String name;
	protected int pending, approved, denied;
	
	public UserStatistic(String name, int pending, int approved, int denied) {
		super();
		this.name = name;
		this.pending = pending;
		this.approved = approved;
		this.denied = denied;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPending() {
		return pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getDenied() {
		return denied;
	}

	public void setDenied(int denied) {
		this.denied = denied;
	}
}
