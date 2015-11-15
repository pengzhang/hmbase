package controllers;

import annotation.Login;
import exceptions.ParamException;
import exceptions.ServiceException;
import models.base.Comment;
import models.base.Image;
import models.base.Post;
import models.base.User;
import play.mvc.Controller;
import play.mvc.With;
import services.base.UserService;

@With(ActionInterceptor.class)
@Login(unless={"login","doLogin"})
public class UserController extends Controller {
	
	public static void login(){
		render();
	}
	
	public static void doLogin(String username, String password){
		try{
			User user = UserService.login(username, password);
			session.put("userId", user.id);
			session.put("username", user.username);
			if(user.type == 0 || user.type ==1){
				redirect("/admin");
			}else{
				redirect("/user");
			}
		}catch(ParamException p){
			flash.put("error", p.getMessage());
			redirect("/user/login");
		}catch(ServiceException s){
			flash.put("error", s.getMessage());
			redirect("/user/login");
		}
		
	}
	
	public static void logout(){
		session.clear();
		redirect("/");
	}

}