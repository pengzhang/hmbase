package controllers.admin;

import java.util.Date;

import models.base.User;
import models.data.ResponseData;
import play.data.validation.Valid;
import utils.ParamUtil;
import controllers.AdminController;

public class AdminUser extends AdminController {
	
	public static void createOrUpdateUser(Long id){
		if(id==null){
			render();
		}else{
			User user = User.findById(id);
			render(user);
		}
		
	}
	
	public static void save(@Valid User user){
		if(validation.hasErrors()){
			params.flash();
			validation.keep();
			createOrUpdateUser(null);
		}
		user.save();
		flash.success("创建用户%s成功", user.username);
		redirect("/admin/users");
	}

	public static void modify(long id){
		User user = User.findById(id);
		ParamUtil.getEditParams(request.body);
		user.updateDate = new Date();
		user.save();
		renderJSON(ResponseData.response(true, "用户信息修改成功"));
	}

	public static void removeUser(long id){
		User user = User.findById(id);
		user.status = true;
		user.save();
		renderJSON(ResponseData.response(true, "用户信息删除成功"));
	}

	public static void usersData(Integer limit, Integer offset, String search, String sort, String order){
		renderJSON(User.findByPageData(offset/limit+1, limit, search, null, sort, order, null));
	}
}
