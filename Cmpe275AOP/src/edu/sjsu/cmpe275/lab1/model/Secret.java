package edu.sjsu.cmpe275.lab1.model;

import java.util.UUID;

public class Secret {
	private UUID secretId;
	
	public Secret() {
		//This generates a unique random UUID
		setSecretId(UUID.randomUUID());
	}


	public Secret(UUID secretId) {
		this.secretId = secretId;
	}


	public UUID getSecretId() {
		return secretId;
	}


	public void setSecretId(UUID secretId) {
		this.secretId = secretId;
	}

	// Add whatever you like
}
