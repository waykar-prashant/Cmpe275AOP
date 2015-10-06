package edu.sjsu.cmpe275.lab1.services;

import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.lab1.interfaces.ISecretService;
import edu.sjsu.cmpe275.lab1.model.Secret;

@Component("secretservice")
public class SecretService implements ISecretService {

	@Override
	public UUID storeSecret(String userId, Secret secret) {
		if(secret != null)
			return secret.getSecretId();
		else return null;
	}

	public Secret readSecret(String userId, UUID secretId) {
		return null;
	}

	@Override
	public void shareSecret(String userId, UUID secretId, String targetUserId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unshareSecret(String userId, UUID secretId, String targetUserId) {
		// TODO Auto-generated method stub
		
	}
}
