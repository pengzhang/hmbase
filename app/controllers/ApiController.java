package controllers;

import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import exceptions.ParamException;
import exceptions.ServiceException;
import models.base.User;
import play.mvc.Controller;
import play.mvc.With;
import services.base.UserService;
import utils.ParamUtil;

@With(ApiInterceptor.class)
public class ApiController extends Controller {

	public static void login(){
		try{
			Map<String,Object> json = ParamUtil.getJsonParams(request.body);
			User login = UserService.login(String.valueOf(json.get("username")), String.valueOf(json.get("password")));
			renderJSON("");
		}catch(ParamException p){
			renderJSON("");
		}catch(ServiceException s){
			renderJSON("");
		}
	}
	
	public static void register(){
		try{
			User user = ParamUtil.getJsonParams(request.body, User.class);
			boolean flag = UserService.register(user);
			renderJSON("");
		}catch(ParamException p){
			renderJSON("");
		}catch(ServiceException s){
			renderJSON("");
		}
	}
	
	public static void resetPassword(){
		try{
			Map<String,Object> json = ParamUtil.getJsonParams(request.body);
			boolean flag = UserService.resetPassword(NumberUtils.toLong(String.valueOf(json.get("id"))), String.valueOf(json.get("oldPassword")), String.valueOf(json.get("password")));
			renderJSON("");
		}catch(ParamException p){
			renderJSON("");
		}catch(ServiceException s){
			renderJSON("");
		}
	}
	

}