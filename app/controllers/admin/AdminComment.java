package controllers.admin;

import java.util.Date;

import controllers.AdminController;
import models.base.Comment;
import models.data.ResponseData;
import utils.ParamUtil;

public class AdminComment extends AdminController {
	
	public static void createComment(){
		Comment comment = ParamUtil.getJsonParams(request.body, Comment.class);
		comment.save();
		renderJSON(ResponseData.response(true, "评论创建成功"));
	}

	public static void modifyComment(long id){
		Comment comment = Comment.findById(id);
		ParamUtil.getEditParams(request.body);
		comment.updateDate = new Date();
		comment.save();
		renderJSON(ResponseData.response(true, "评论修改成功"));
	}

	public static void removeComment(long id){
		Comment comment = Comment.findById(id);
		comment.status = true;
		comment.save();
		renderJSON(ResponseData.response(true, "评论删除成功"));
	}

	public static void CommentsData(Integer limit, Integer offset, String search, String sort, String order){
		renderJSON(Comment.findByPageData(offset/limit+1, limit, search, null, sort, order, null));
	}
}
