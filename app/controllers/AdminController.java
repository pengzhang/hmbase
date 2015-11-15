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
@Login()
public class AdminController extends Controller {
	
    public static void index(){
    	renderArgs.put("userTotal", User.count());
    	renderArgs.put("postTotal", Post.count());
    	renderArgs.put("imageTotal", Image.count());
    	renderArgs.put("commentTotal", Comment.count());
        render();
    }

}