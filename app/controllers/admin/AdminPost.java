package controllers.admin;

import java.util.Date;

import models.base.Post;
import models.data.ResponseData;
import play.mvc.With;
import utils.ParamUtil;
import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Secure;

@With({ActionInterceptor.class,Secure.class})
public class AdminPost extends AdminController {
	
	public static void createPost(){
		Post post = ParamUtil.getJsonParams(request.body, Post.class);
		post.save();
		renderJSON(ResponseData.response(true, "内容创建成功"));
	}

	public static void modifyPost(long id){
		Post post = Post.findById(id);
		ParamUtil.getEditParams(request.body);
		post.updateDate = new Date();
		post.save();
		renderJSON(ResponseData.response(true, "内容修改成功"));
	}

	public static void removePost(long id){
		Post post = Post.findById(id);
		post.status = true;
		post.save();
		renderJSON(ResponseData.response(true, "内容删除成功"));
	}

	public static void PostsData(Integer limit, Integer offset, String search, String sort, String order){
		renderJSON(Post.findByPageData(offset/limit+1, limit, search, null, sort, order, null));
	}
}
