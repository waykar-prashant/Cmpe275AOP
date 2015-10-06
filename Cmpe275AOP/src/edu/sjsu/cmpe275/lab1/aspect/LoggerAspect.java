package edu.sjsu.cmpe275.lab1.aspect;

import org.aspectj.lang.annotation.After;

import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;

import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	@Pointcut("execution(* edu.sjsu.cmpe275.lab1.services.SecretService.readSecret(..))")
	public void readLog() {
	}

	@Pointcut("execution(* edu.sjsu.cmpe275.lab1.services.SecretService.storeSecret(..))")
	public void storeLog() {
	}

	@Pointcut("execution(* edu.sjsu.cmpe275.lab1.services.SecretService.unshareSecret(..))")
	public void unshareLog() {
	}

	@Pointcut("execution(* edu.sjsu.cmpe275.lab1.services.SecretService.shareSecret(..))")
	public void shareLog() {
	}

	@After("storeLog()")
	public void storeSecretLog() {
		System.out.println("ASPECT: Store Log!!");
	}

	@Before("readLog()")
	public void readSecretLog() {
		System.out.println("ASPECT: Read Log!!");
	}

	@Before("shareLog()")
	public void shareSecretLog() {
		System.out.println("ASPECT: Share Log!!");
	}

	@Before("unshareLog()")
	public void unshareSecretLog() {
		System.out.println("ASPECT: Unshare Log!!");
	}

}
