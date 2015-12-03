package controllers;

import java.util.Date;

import models.base.User;
import models.base.UserProfile;
import play.mvc.Controller;
import play.mvc.With;
import services.base.UserService;

@With(ActionInterceptor.class)
public class Application extends Controller {

    public static void index() {
    	User user = new User();
		user.username = "test";
		user.password = "test";
		user.type = 1;
		User $user = UserService.register(user);
		UserProfile profile = new UserProfile();
		profile.user = $user;
		profile.save();
        render();
    }
}
