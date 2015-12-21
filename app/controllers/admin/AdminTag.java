package controllers.admin;

import java.util.Date;

import models.base.Comment;
import models.base.Tag;
import models.data.PageData;
import models.data.ResponseData;
import play.mvc.With;
import utils.JsonUtil;
import utils.ParamUtil;
import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Secure;

@With({ActionInterceptor.class,Secure.class})
public class AdminTag extends AdminController {

	public static void tagsData(Integer limit, Integer offset, String search, String sort, String order){
		PageData pageData = Tag.findByPageData(offset/limit+1, limit, search, null, sort, order, null);
		renderJSON(JsonUtil.toJson(pageData, "children","profile","categories","comments","social"));
	}

}
