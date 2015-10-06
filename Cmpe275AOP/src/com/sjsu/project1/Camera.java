package com.sjsu.project1;

import org.springframework.stereotype.Component;

@Component
public class Camera {

	public void snap() throws Exception{
		System.out.println("SNAP!!"  + "\n");
		throw new Exception("bye bye..");
	}
	
	public void snap(int exposure){
		System.out.println("SNAP!! Exposure : " + exposure + "\n");
	}
	
	public String snap(String name){
		System.out.println("SNAP!! Name : " + name  + "\n");
		return name;
	}

	public void snapNightTime(){
		System.out.println("Snap Camera Night Time!!"  + "\n");
	}
	
}
