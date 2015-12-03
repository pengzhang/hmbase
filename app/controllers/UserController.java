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

@With({ActionInterceptor.class,Security.class})
public class UserController extends Controller {

}