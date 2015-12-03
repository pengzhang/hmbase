package controllers.admin;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import controllers.AdminController;
import models.base.User;
import models.base.UserProfile;
import models.data.PageData;
import models.data.ResponseData;
import utils.JsonUtil;
import utils.ParamUtil;

public class AdminUserProfile extends AdminController {
	
	public static void modify(Long id){
		UserProfile profile = UserProfile.findById(id);
		render(profile);
		
	}
	
	public static void update(Long id){
		UserProfile profile = UserProfile.findById(id);
		profile.edit(params.getRootParamNode(), "profile");
		validation.valid(profile);
	    if(validation.hasErrors()) {
	    	profile.refresh();
	        validation.keep();
	        modify(id);
	    }
	    profile.save();
		flash.success("修改%s用户资料成功", profile.user.username);
		modify(id);
	}
	
	public static void userProfilesData(Long user_id,Integer limit, Integer offset, String search, String sort, String order){
		renderJSON(JsonUtil.toJson(UserProfile.findByPageData(offset/limit+1, limit, search, null, sort, order, user_id!=null?"user.id="+user_id:null),"profile"));
	}
}
