package controllers.admin;

import java.util.List;

import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Secure;
import models.base.Category;
import models.base.Post;
import models.data.ResponseData;
import play.data.validation.Valid;
import play.mvc.With;

@With({ActionInterceptor.class,Secure.class})
public class AdminPost extends AdminController {
	
	public static void create(){
		List<Category> categories = Category.findAll();
		render(categories);
	}
	
	public static void save(@Valid(message="请检查您的文章内容") Post post){
		if(validation.hasErrors()){
			params.flash();
			validation.keep();
			create();
		}
		post.save();
		flash.success("创建文章%s成功", post.title);
		redirect("/admin/posts");
	}

	public static void modify(long id){
		Post post = Post.findById(id);
		List<Category> categories = Category.findAll();
		render(post,categories);
	}
	
	public static void update(long id){
		Post post = Post.findById(id);
		
		post.edit(params.getRootParamNode(), "post");
		validation.valid(post);
	    if(validation.hasErrors()) {
	    	post.refresh();
	        validation.keep();
	        modify(id);
	    }
	    
	    Post.update(post);
		flash.success("修改文章%s成功", post.title);
		modify(id);
	}

	public static void remove(long id){
		Post.remove(id);
		renderJSON(ResponseData.response(true, "文章删除成功"));
	}

	public static void PostsData(Integer limit, Integer offset, String search, String sort, String order){
		renderJSON(Post.findByPageData(offset/limit+1, limit, search, null, sort, order, null));
	}
}
