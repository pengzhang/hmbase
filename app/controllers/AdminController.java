package controllers;

import java.text.ParseException;
import java.util.Map;

import annotation.Login;
import models.base.Comment;
import models.base.Image;
import models.base.Post;
import models.base.User;
import models.logs.AccessLog;
import play.mvc.Controller;
import play.mvc.With;

@With(ActionInterceptor.class)
@Login()
public class AdminController extends Controller {
	
    public static void index() throws ParseException{
    	renderArgs.put("userTotal", User.count());
    	renderArgs.put("postTotal", Post.count());
    	renderArgs.put("imageTotal", Image.count());
    	renderArgs.put("commentTotal", Comment.count());
    	Map<String,String> recentAccess = AccessLog.getRecentAccessRecord(15);
    	renderArgs.put("chart_title", recentAccess.get("chart_title"));
    	renderArgs.put("chart_content", recentAccess.get("chart_content"));
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
    
    public static void userProfiles(){
    	String menu = "user_profile";
    	render(menu);
    }

}