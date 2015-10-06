package com.sjsu.project1;

import org.springframework.stereotype.Component;

@Component
public class Lens {

	public void zoom(int factor){
		System.out.println("Zooming Lens : " + factor);
	}
}
