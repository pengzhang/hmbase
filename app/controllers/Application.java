package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(ActionInterceptor.class)
public class Application extends Controller {

    public static void index() {
        render();
    }

}
