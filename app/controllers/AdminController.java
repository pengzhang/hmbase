package controllers;

import java.util.Date;

import models.base.Category;
import models.base.Comment;
import models.base.Image;
import models.base.Post;
import models.base.User;
import models.data.ResponseData;
import play.mvc.Controller;
import play.mvc.With;
import utils.ParamUtil;
import annotation.Login;

import com.google.gson.Gson;

@With(ActionInterceptor.class)
@Login()
public class AdminController extends Controller {
	
    public static void index(){
    	renderArgs.put("userTotal", User.count());
    	renderArgs.put("postTotal", Post.count());
    	renderArgs.put("imageTotal", Image.count());
    	renderArgs.put("commentTotal", Comment.count());
        render();
    }
    
    public static void users(){
    	String menu = "user";
    	render(menu);
    }
    
    public static void categories(){
    	String menu = "category";
    	render(menu);
    }
    
    public static void posts(){
    	String menu = "post";
    	render(menu);
    }
    
    public static void comments(){
    	String menu = "comment";
    	render(menu);
    }
    
    public static void images(){
    	String menu = "image";
    	render(menu);
    }

}