package controllers.admin;

import java.util.Date;

import controllers.AdminController;
import models.base.User;
import models.data.ResponseData;
import utils.ParamUtil;

public class AdminUser extends AdminController {
	
	public static void createUser(){
		User user = ParamUtil.getJsonParams(request.body, User.class);
		user.save();
		renderJSON(ResponseData.response(true, "用户创建成功"));
	}

	public static void modifyUser(long id){
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
