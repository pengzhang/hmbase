package controllers.admin;

import java.util.Date;

import controllers.AdminController;
import models.base.UserProfile;
import models.data.PageData;
import models.data.ResponseData;
import utils.ParamUtil;

public class AdminUserProfile extends AdminController {
	
	public static void createUserProfile(){
		UserProfile userProfile = ParamUtil.getJsonParams(request.body, UserProfile.class);
		userProfile.save();
		renderJSON(ResponseData.response(true, "用户资料创建成功"));
	}

	public static void modifyUserProfile(long id){
		UserProfile userProfile = UserProfile.findById(id);
		ParamUtil.getEditParams(request.body);
		userProfile.updateDate = new Date();
		userProfile.save();
		renderJSON(ResponseData.response(true, "用户资料信息修改成功"));
	}

	public static void removeUserProfile(long id){
		UserProfile userProfile = UserProfile.findById(id);
		userProfile.status = true;
		userProfile.save();
		renderJSON(ResponseData.response(true, "用户资料信息删除成功"));
	}

	public static void userProfilesData(Integer limit, Integer offset, String search, String sort, String order){
		renderJSON(UserProfile.findByPageData(offset/limit+1, limit, search, null, sort, order, null));
	}
}
