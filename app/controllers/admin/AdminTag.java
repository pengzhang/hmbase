package controllers.admin;

import java.util.Date;

import models.base.Tag;
import models.data.PageData;
import models.data.ResponseData;
import play.mvc.With;
import utils.ParamUtil;
import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Secure;

@With({ActionInterceptor.class,Secure.class})
public class AdminTag extends AdminController {
	
	public static void createTag(){
		Tag tag = ParamUtil.getJsonParams(request.body, Tag.class);
		tag.save();
		renderJSON(ResponseData.response(true, "标签创建成功"));
	}

	public static void modifyTag(long id){
		Tag tag = Tag.findById(id);
		ParamUtil.getEditParams(request.body);
		tag.updateDate = new Date();
		tag.save();
		renderJSON(ResponseData.response(true, "标签修改成功"));
	}

	public static void removeTag(long id){
		Tag tag = Tag.findById(id);
		tag.status = true;
		tag.save();
		renderJSON(ResponseData.response(true, "标签删除成功"));
	}

	public static void TagsData(Integer limit, Integer offset, String search, String sort, String order){
		PageData pageData = Tag.findByPageData(offset/limit+1, limit, search, null, sort, order, null);
		renderJSON(pageData);
	}
}
