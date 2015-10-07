package edu.sjsu.cmpe275.lab1.aspect;

import java.util.UUID;

import org.aspectj.lang.annotation.After;

import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;

import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.lab1.model.Secret;

@Aspect
@Component
public class LoggerAspect {

	@Pointcut("args(userId, secretId)")
	public void readLog(String userId, UUID secretId) {
	}

	@Pointcut("args(userId, secret)")
	public void storeLog(String userId, Secret secret) {
	}

	@Pointcut("args(userId, secretId, targetUserId) && execution(* edu.sjsu.cmpe275.lab1.services.SecretService.unshareSecret(..))")
	public void unshareLog(String userId, UUID secretId, String targetUserId) {
	}

	@Pointcut("args(userId, secretId, targetUserId) && execution(* edu.sjsu.cmpe275.lab1.services.SecretService.shareSecret(..))")
	public void shareLog(String userId, UUID secretId, String targetUserId) {
	}

	@After("storeLog(userId, secret)")
	public void storeSecretLog(String userId, Secret secret) {
		System.out.println(userId +" creates a secret with ID " + secret.getSecretId());
	}

	@Before("readLog(userId, secretId)")
	public void readSecretLog(String userId, UUID secretId) {
		System.out.println(userId +" reads the secret of ID " + secretId);
	}

	@Before("shareLog(userId, secretId, targetUserId)")
	public void shareSecretLog(String userId, UUID secretId, String targetUserId) {
		System.out.println(userId +" shares the secret of ID " + secretId + " with " + targetUserId);
	}

	@Before("unshareLog(userId, secretId, targetUserId)")
	public void unshareSecretLog(String userId, UUID secretId, String targetUserId) {
		System.out.println(userId +" unshares the secret of ID " + secretId + " with " + targetUserId);
	}

}
