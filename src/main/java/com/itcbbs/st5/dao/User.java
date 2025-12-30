package com.itcbbs.st5.dao;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	private String name;
	private String email;
	private String mobile;
	private String username;
	private String password;
	private int active;
	private int level;
	private Date creationdate;
	private Date lastupdated;
	  
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", username="
				+ username + ", userpassword=" + password + ", active=" + active + ", level=" + level
				+ ", creationdate=" + creationdate + ", lastupdated=" + lastupdated + "]";
	}
	
	
}
