package controllers.admin;

import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Secure;
import models.base.Category;
import models.base.Comment;
import models.base.Post;
import models.base.User;
import models.base.UserProfile;
import models.data.PageData;
import models.data.ResponseData;
import play.mvc.With;
import utils.JsonUtil;

@With({ActionInterceptor.class,Secure.class})
public class AdminComment extends AdminController {
	

	public static void remove(long id){
		Comment.remove(id);
		renderJSON(ResponseData.response(true, "评论删除成功"));
	}

	public static void commentsData(Integer limit, Integer offset, String search, String sort, String order){
		PageData pageData = Comment.findByPageData(offset/limit+1, limit, search, null, sort, order, null);
		renderJSON(JsonUtil.toJson(pageData, "children","profile","categories","tags","comments","social"));
	}
}
