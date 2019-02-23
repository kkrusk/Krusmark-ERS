package com.krusmark.models;

import java.sql.Blob;
import java.sql.Date;

public class Reimbursement {
	private int id;
	private double amount;
	private Date submitted;
	private Date resolved;
	private String author;
	private String resolver;
	private String status;
	private String type;
	private String description;
	private Blob receipt;
	
	public Reimbursement() {}
	
	public Reimbursement(int id, double amount, Date submitted, Date resolved, String author, String resolver,
			String status, String type, String description, Blob receipt) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
		this.description = description;
		this.receipt = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public Date getResolved() {
		return resolved;
	}

	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", author=" + author + ", resolver=" + resolver + ", status=" + status + ", type=" + type
				+ ", description=" + description + ", receipt=" + receipt + "]";
	}
	

}
