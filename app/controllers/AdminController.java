package controllers;

import java.text.ParseException;
import java.util.Map;

import models.base.Category;
import models.base.Comment;
import models.base.Image;
import models.base.Post;
import models.base.User;
import models.logs.AccessLog;
import play.mvc.Controller;
import play.mvc.With;

@Check("manager")
@With(value={ActionInterceptor.class,Secure.class})
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
    
    @Check("admin")
    public static void users(){
    	renderArgs.put("userTotal", User.count());
    	renderArgs.put("todayTotal", User.count(1));
    	renderArgs.put("weekTotal", User.count(-7));
    	renderArgs.put("monthTotal", User.count(-30));
    	renderArgs.put("_100Total", User.count(-100));
    	String menu = "user";
    	render(menu);
    }
    
    public static void categories(){
    	renderArgs.put("categoryTotal", Category.count());
    	String menu = "category";
    	render(menu);
    }
    
    public static void posts(){
    	renderArgs.put("postTotal", Post.count());
    	renderArgs.put("todayTotal", Post.count(1));
    	renderArgs.put("publishTotal", Post.count("draft",true));
    	renderArgs.put("draftTotal", Post.count("draft",false));
    	renderArgs.put("recommendTotal", Post.count("recommend",true));
    	renderArgs.put("hotTotal", Post.count("hot",true));
    	renderArgs.put("removeTotal", Post.count("status",true));
    	String menu = "post";
    	render(menu);
    }
    
    public static void comments(){
    	renderArgs.put("commentTotal", Comment.count());
    	renderArgs.put("todayTotal", Comment.count(1));
    	renderArgs.put("unAuditTotal", Comment.count("audit",false));
    	renderArgs.put("auditedTotal", Comment.count("audit",true));
    	renderArgs.put("removeTotal", Comment.count("status",true));
    	String menu = "comment";
    	render(menu);
    }
    
    public static void images(){
    	String menu = "image";
    	render(menu);
    }
    
    @Check("admin")
    public static void userProfiles(Long user_id){
    	String menu = "user_profile";
    	render(menu,user_id);
    }

}