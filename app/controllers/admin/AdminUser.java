package controllers.admin;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import models.base.User;
import models.base.UserProfile;
import models.data.ResponseData;
import play.data.validation.Valid;
import play.libs.Crypto;
import services.base.UserService;
import utils.ParamUtil;
import controllers.AdminController;

public class AdminUser extends AdminController {
	
	public static void create(){
		render();
	}
	
	public static void save(@Valid User user){
		if(validation.hasErrors()){
			params.flash();
			validation.keep();
			create();
		}
		UserService.register(user);
		flash.success("创建用户%s成功", user.username);
		redirect("/admin/users");
	}
	
	public static void modify(Long id){
		User user = User.findById(id);
		render(user);
		
	}

	public static void update(Long id){
		User user = User.findById(id);
		String password = user.password;
		user.edit(params.getRootParamNode(), "user");
		if(StringUtils.isEmpty(user.password)){
			user.password = password;
			user.repeatPassword = password;
		}
		
		validation.valid(user);
	    if(validation.hasErrors()) {
	        user.refresh();
	        validation.keep();
	        update(id);
	    }
	    
	    User.update(user);
		flash.success("修改用户%s成功", user.username);
		modify(id);
	}
	

	public static void remove(long id){
		User.remove(id);
		renderJSON(ResponseData.response(true, "用户信息删除成功"));
	}

	public static void usersData(Integer limit, Integer offset, String search, String sort, String order){
		renderJSON(User.findByPageData(offset/limit+1, limit, search, null, sort, order, null));
	}
}
