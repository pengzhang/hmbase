package controllers.admin;

import models.base.User;
import models.data.ResponseData;

import org.apache.commons.lang.StringUtils;

import play.data.validation.Valid;
import play.libs.Crypto;
import play.mvc.With;
import services.base.UserService;
import utils.JsonUtil;
import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Check;
import controllers.Secure;

@Check("admin")
@With({ActionInterceptor.class,Secure.class})
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
		}else{
			user.password = Crypto.passwordHash(user.password);
			user.repeatPassword = user.password ;
		}
		
		validation.valid(user);
	    if(validation.hasErrors()) {
	        user.refresh();
	        validation.keep();
	        modify(id);
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
		renderJSON(JsonUtil.toJson(User.findByPageData(offset/limit+1, limit, search, null, sort, order, null),"user"));
	}
}
