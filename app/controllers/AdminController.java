package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import annotation.Login;
import models.*;

@With(ActionInterceptor.class)
@Login(unless={"login","doLogin"})
public class AdminController extends Controller {

    public static void index() {
        render();
    }

}