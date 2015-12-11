package controllers;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import annotation.Login;
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
    	String menu = "user";
    	render(menu);
    }
    
    public static void categories(){
    	String menu = "category";
    	render(menu);
    }
    
    public static void posts(){
    	renderArgs.put("postTotal", Post.count());
    	Date start = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
    	Date end = DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DAY_OF_MONTH);
    	renderArgs.put("todayTotal", Post.count("createDate>? and createDate<?",start,end));
    	renderArgs.put("publishTotal", Post.count("draft",true));
    	renderArgs.put("draftTotal", Post.count("draft",false));
    	renderArgs.put("recommendTotal", Post.count("recommend",true));
    	renderArgs.put("hotTotal", Post.count("hot",true));
    	renderArgs.put("removeTotal", Post.count("status",true));
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
    
    @Check("admin")
    public static void userProfiles(Long user_id){
    	String menu = "user_profile";
    	render(menu,user_id);
    }

}