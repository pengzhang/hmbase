package modules.notify;

import models.base.User;

public class Notify {

	public static void all(User user,String action){
		sms(user.mobile, action);
		email(user.email, action);
	}
	
	public static void sms(String mobile, String action){
		
	}
	
	public static void sms(String mobile, String message, String action){
		
	}
	
	public static void email(String email, String action){
		
	}
	
	public static void email(String email, String subject, String message, String action){
		
	}
	
	public static void wechat(String wechat, String action){
		
	}
}
