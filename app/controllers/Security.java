package controllers;

import play.Logger;
import play.cache.Cache;
import exceptions.ServiceException;
import services.base.UserService;
import models.base.User;

public class Security extends Secure.Security {

	static boolean authenticate(String username, String password) {
		try {
			User user = UserService.login(username, password);
			if (user != null) {
//				Cache.set("user_" + username, user);
				return true;
			}
			return false;
		} catch (ServiceException e) {
			Logger.info("exception message : %s", e.getMessage());
			return false;
		}
	}

	static boolean check(String profile) {
//		User user = (User) Cache.get("user_" + connected());
		User user = User.find("username", connected()).first();
		if (user == null) {
			return false;
		}
		
		if(getUserType(user.type).equals("admin")){
			return true;
		}
		
		if (profile.equals(getUserType(user.type))) {
			return true;
		}
		return false;
	}

	static void onAuthenticated() {
//		User user = (User) Cache.get("user_" + connected());
		User user = User.find("username", connected()).first();
		if (user.type == 0 || user.type == 1) {
			redirect("/admin");
		} else {
			redirect("/user");
		}
	}

	static void onDisconnect() {
		Cache.delete("user_" + connected());
	}

	static void onDisconnected() {
	}

	private static String getUserType(int type) {
		switch (type) {
		case 0:
			return "admin";
		case 1:
			return "manager";
		case 2:
			return "vip";
		case 3:
			return "member";
		}
		return "other";
	}
}
