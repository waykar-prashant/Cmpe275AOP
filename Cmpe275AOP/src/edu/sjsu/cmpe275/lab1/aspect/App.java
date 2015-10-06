package edu.sjsu.cmpe275.lab1.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.sjsu.cmpe275.lab1.model.User;
import edu.sjsu.cmpe275.lab1.services.SecretService;

public class App {
	
	public static List<User> globalUserList = new ArrayList<>();
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("edu/sjsu/cmpe275/assignment1/bean/bean.xml");
		Object obj = context.getBean("secretservice");
		System.out.println(obj.getClass());
		SecretService ss = (SecretService)context.getBean("secretservice");
		User alice = new User("Alice");
		User bob = new User("Bob");
		User carl = new User("Carl");
		System.out.println("Restart Application");
		//Adding the users to the global users list
		globalUserList.add(alice);
		globalUserList.add(bob);
		globalUserList.add(carl);
		
		
		//System.out.println(UUID.fromString("aaa"));
		//System.out.println(UUID.fromString("aaa"));
		//System.out.println(UUID.fromString("aaa"));
		
		ss.readSecret("aa", UUID.randomUUID());
		ss.shareSecret("aa", UUID.randomUUID(), "bb");
		ss.storeSecret("aa", null);
		ss.unshareSecret(null, null, null);
		/*Lens lens = (Lens)context.getBean("lens");
		
		try {
			camera.snap();
		} catch (Exception e) {
			System.out.println("Caught Exception!! bye bye ..");
		}*/
		/*camera.snap(1000);
		camera.snap("Prashant   ");
		camera.snapNightTime();
		
		lens.zoom(5);*/
		
		context.close();
	}

}
