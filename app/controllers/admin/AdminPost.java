package controllers.admin;

import java.util.ArrayList;
import java.util.List;

import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Secure;
import controllers.Security;
import models.base.Category;
import models.base.Post;
import models.base.Tag;
import models.base.User;
import models.data.PageData;
import models.data.ResponseData;
import play.data.validation.Valid;
import play.mvc.Before;
import play.mvc.With;
import utils.JsonUtil;

@With({ActionInterceptor.class,Secure.class})
public class AdminPost extends AdminController {
	
	@Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("username", Security.connected()).first();
            renderArgs.put("user", user);
        }
    }

	
	public static void create(){
		List<Category> categories = Category.findAll();
		render(categories);
	}
	
	public static void save(@Valid(message="请检查") Post post, String tags){
		// 为post对象添加tag
		post.tags = new ArrayList<Tag>();
	    for(String tag : tags.split("\\s+")) {
	        if(tag.trim().length() > 0) {
	            post.tags.add(Tag.findOrCreateByName(tag));
	        }
	    }

		if(validation.hasErrors()){
			params.flash();
			validation.keep();
			create();
		}
		post.user = (User) renderArgs.get("user");
		post.save();
		flash.success("创建文章%s成功", post.title);
		redirect("/admin/posts");
	}

	public static void modify(long id){
		Post post = Post.findById(id);
		List<Category> categories = Category.findAll();
		render(post,categories);
	}
	
	public static void update(long id,String tags){
		Post post = Post.findById(id);
		post.edit(params.getRootParamNode(), "post");
		for(String tag : tags.split("\\s+")) {
	        if(tag.trim().length() > 0) {
	            post.tags.add(Tag.findOrCreateByName(tag));
	        }
	    }
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
	
	public static void publish(long id){
		Post.publish(id);
		renderJSON(ResponseData.response(true, "文章发布成功"));
	}

	public static void PostsData(Integer limit, Integer offset, String search, String sort, String order){
		PageData pageData = Post.findByPageData(offset/limit+1, limit, search, null, sort, order, null);
		renderJSON(JsonUtil.toJson(pageData,"posts","post","parent","children","profile"));
	}
}
