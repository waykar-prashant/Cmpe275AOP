package edu.sjsu.cmpe275.lab1;

import edu.sjsu.cmpe275.lab1.aspect.AccessControlAspect;
import edu.sjsu.cmpe275.lab1.model.User;
import edu.sjsu.cmpe275.lab1.services.SecretService;

public class Utility {

	public static User getUserFromUserId(String userId){
		User user = null;
		if(userId != null){
			for (User userObj : AccessControlAspect.globalUserList) {
				if(userId.toLowerCase().equals(userObj.getUserId())){
					user = userObj;
					break;
				}
			}
		}
		return user;
	}
}
