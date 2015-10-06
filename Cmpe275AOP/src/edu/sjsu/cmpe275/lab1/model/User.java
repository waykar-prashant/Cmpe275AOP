package edu.sjsu.cmpe275.lab1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

	private String userName;
	private String userId;
	
	private List<UUID> own = new ArrayList<>(); //secret id which the user owns, there can be multiple secret id's generated for the same user
	private List<UUID> sharedWithMe = new ArrayList<>(); //secret id's shared with me
	
	
	public User(String name) {
		this.userName = name;
		this.userId = name.toLowerCase();
	}

	public List<UUID> getOwn() {
		return own;
	}
	
	public void setOwn(List<UUID> own) {
		this.own = own;
	}
	
	public List<UUID> getSharedWithMe() {
		return sharedWithMe;
	}
	
	public void setSharedWithMe(List<UUID> sharedWithMe) {
		this.sharedWithMe = sharedWithMe;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
