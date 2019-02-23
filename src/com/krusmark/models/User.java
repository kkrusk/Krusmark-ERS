package com.krusmark.models;

public class User {
	private int Id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private int role;
	
	public User(int ers_users_id, String ers_username, String ers_password, String user_first_name, String user_last_name, String user_email, int user_role_id) {
		Id = ers_users_id;
		username = ers_username;
		password = ers_password;
		firstname = user_first_name;
		lastname = user_last_name;
		email = user_email;
		role = user_role_id;
	}

	public int getId() {
		return Id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
