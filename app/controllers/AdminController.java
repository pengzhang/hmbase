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
public class AdminController extends Controller {
	
	public static void login(){
		render();
	}
	
	public static void doLogin(String username, String password){
		try{
			User user = UserService.adminLogin(username, password);
			session.put("admin", user.username);
			redirect("/admin");
		}catch(ParamException p){
			flash.put("error", p.getMessage());
			redirect("/admin/login");
		}catch(ServiceException s){
			flash.put("error", s.getMessage());
			redirect("/admin/login");
		}
		
	}

    public static void index(){
    	renderArgs.put("userTotal", User.count());
    	renderArgs.put("postTotal", Post.count());
    	renderArgs.put("imageTotal", Image.count());
    	renderArgs.put("commentTotal", Comment.count());
        render();
    }

}