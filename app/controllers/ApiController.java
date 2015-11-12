package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(ApiInterceptor.class)
public class ApiController extends Controller {

	

}