package edu.sjsu.cmpe275.lab1.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.rmi.CORBA.Util;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.lab1.UnauthorizedException;
import edu.sjsu.cmpe275.lab1.Utility;
import edu.sjsu.cmpe275.lab1.model.Secret;
import edu.sjsu.cmpe275.lab1.model.User;

@Aspect
@Component
public class AccessControlAspect {
	public static List<User> globalUserList = new ArrayList<>();

	public AccessControlAspect() {
		// Load Application Data
		User alice = new User("Alice");
		User bob = new User("Bob");
		User carl = new User("Carl");
		//Adding the users to the global users list
		globalUserList.add(alice);
		globalUserList.add(bob);
		globalUserList.add(carl);
	}

	@Pointcut("args(userId, secret)")
	public void storeLog(String userId, Secret secret) {
	}
	
	@After("storeLog(userId, secret)")
	public UUID storeSecretLog(String userId, Secret secret) {
		System.out.println("ASPECT: Store Log!!"+userId);
		//generate a new secret object and update the user owns list of UUIDs
		//get a User object with user id = userId
		if(userId != null && secret != null){
			User tempUser = Utility.getUserFromUserId(userId);
			if(tempUser != null){
				tempUser.getOwn().add(secret.getSecretId());
				return secret.getSecretId();
			}
		}
		return null;
	}
	
	@Pointcut("args(userId, secretId)")
	public void readLog(String userId, UUID secretId) {
	}

	@Before("readLog(userId, secretId)")
	public void readSecretLog(String userId, UUID secretId) {
		System.out.println("ASPECT: Read Log!!");
		//if the users owns the secret or someone has shared secret with him, then the user can read it
		//else throw exception
		if(userId != null && secretId != null){
			User tempUser = Utility.getUserFromUserId(userId);
			if(tempUser != null){
				if(tempUser.getOwn().contains(secretId) || tempUser.getSharedWithMe().contains(secretId)){
					System.out.println("Read successful:" + secretId);
				}else{
					System.out.println("Unauthorized access!!");
					throw new UnauthorizedException();
				}
			}
		}
	}
	
	@Pointcut("args(userId, secretId, targetUserId) && execution(* edu.sjsu.cmpe275.lab1.services.SecretService.shareSecret(..))")
	public void shareLog(String userId, UUID secretId, String targetUserId) {
	}

	@Before("shareLog(userId, secretId, targetUserId)")
	public void shareSecretLog(String userId, UUID secretId, String targetUserId) {
		System.out.println("ASPECT: Share Log!!");
		//check if the user owns the secret or someone has shared the secret with the user
		//else exception
		if(userId != null && secretId != null && targetUserId != null){
			User tempUser = Utility.getUserFromUserId(userId);
			if(tempUser != null){
				if(userId.equals(targetUserId)) //Self sharing has no effect
					return;
				if(tempUser.getOwn().contains(secretId) || tempUser.getSharedWithMe().contains(secretId)){
					System.out.println("share is successful:" + secretId);
					//add this secret to the target user id
					User targetUser = Utility.getUserFromUserId(targetUserId);
					if(targetUser != null){
						targetUser.getSharedWithMe().add(secretId);
					}
				}else{
					System.out.println("Unauthorized access!!");
					throw new UnauthorizedException();
				}
			}
		}else{
			System.out.println("Contains null!!");
		}
	}
	
	@Pointcut("args(userId, secretId, targetUserId) && execution(* edu.sjsu.cmpe275.lab1.services.SecretService.unshareSecret(..))")
	public void unshareLog(String userId, UUID secretId, String targetUserId) {
	}

	@Before("unshareLog(userId, secretId, targetUserId)")
	public void unshareSecretLog(String userId, UUID secretId, String targetUserId) {
		System.out.println("ASPECT: Unshare Log!!");
		//check if the user owns the secret 
			//now check if the target user has the secret
			//if yes then remove from the list
			//else ignore
		//else !own && !sharedwith exception
		
		if(userId != null && secretId != null && targetUserId != null){
			User tempUser = Utility.getUserFromUserId(userId);
			if(tempUser != null){
				if(userId.equals(targetUserId)) //Self sharing has no effect
					return;
				if(tempUser.getOwn().contains(secretId) ){
					User targetUser = Utility.getUserFromUserId(targetUserId);
					if(targetUser != null){
						if(targetUser.getSharedWithMe().contains(secretId)){
							targetUser.getSharedWithMe().remove(secretId);
							System.out.println("Unshared Successfully!!");
							return;
						}else{
							//do nothing
						}
					}
				}else if(!tempUser.getOwn().contains(secretId) && !tempUser.getSharedWithMe().contains(secretId)){
					System.out.println("Unauthorized access!!");
					throw new UnauthorizedException();
				}
			}
		}else{
			System.out.println("Contains null!!");
		}
	}



	

}
