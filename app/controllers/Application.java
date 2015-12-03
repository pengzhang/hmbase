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
        render();
    }
}
