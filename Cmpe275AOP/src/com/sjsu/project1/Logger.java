package com.sjsu.project1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {

	//creating a method but a pointcut
	/*@Pointcut("execution(* com.sjsu.project1.Camera.*(..))")
	public void cameraSnap(){
	}
	
	@Pointcut("execution(* com.sjsu.project1.Camera.snap(String))")
	public void cameraSnapName(){
	}
	
	@Pointcut("execution(* com.sjsu.project1.Lens.*(..))")
	public void cameraRelatedAction(){
		
	}
	
	@Before("cameraSnap()")
	public void aboutToTakePhoto(){
		System.out.println("Advice: About to take photo!!");
	}
	
	@Before("cameraSnapName()")
	public void aboutToTakePhotoWithName(){
		System.out.println("Advice : About to take photo with name!!");
	}
	
	@Before("cameraRelatedAction()")
	public void aboutToTakeCameraRelatedAction(){
		System.out.println("Advice : About to take camera related action !!");
	}*/
	
	@Pointcut("execution(* com.sjsu.project1.Camera.*(..))")
	public void cameraSnapTest(){
		
	}
	
	@After("cameraSnapTest()")
	public void afterAdvice(){
		System.out.println("After Advice .. ");
	}
	
	@Before("cameraSnapTest()")
	public void beforeAdvice(){
		System.out.println("Before Advice ..");
	}
	
	@AfterReturning("cameraSnapTest()")
	public void afterReturningAdvice(){
		System.out.println("After Returning Advice .. ");
	}
	
	@AfterThrowing("cameraSnapTest()")
	public void afterThrowingAdvice(){
		System.out.println("After Throwing Advice .. ");
	}
	
	
	@Around("cameraSnapTest()")
	public void aroundAdvice(ProceedingJoinPoint p){
		System.out.println("Around Advice .. (before)");
		try {
			p.proceed();
		} catch (Throwable e) {
			System.out.println("In around advice : " + e.getMessage());
		}
		System.out.println("Around Advice .. (after)");
	}
	
	
	
}
