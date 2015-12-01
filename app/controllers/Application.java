package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.base.User;
import models.base.UserProfile;

@With(ActionInterceptor.class)
public class Application extends Controller {

    public static void index() {
    	User user = new User();
    	user.save();
    	UserProfile profile = new UserProfile();
    	profile.userId = user.id;
    	profile.save();
    	
    	System.out.println(((User)User.findById(user.id)).profile);
    	System.out.println(((UserProfile)UserProfile.findById(profile.id)).user);
        render();
    }

}