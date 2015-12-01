package controllers;

import models.base.User;
import models.base.UserProfile;
import play.mvc.Controller;
import play.mvc.With;
import utils.JsonUtil;

@With(ActionInterceptor.class)
public class Application extends Controller {

    public static void index() {
//    	User user = new User();
//    	user.save();
//    	UserProfile profile = new UserProfile();
//    	profile.user = user;
//    	profile.save();
    	
    	User user = User.findById(2L);
    	UserProfile profile = UserProfile.find("user.id=?", 2L).first();
    	
        renderJSON(JsonUtil.toJson(user, "user"));
    }

}