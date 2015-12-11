package controllers.admin;

import java.util.List;

import models.base.Category;
import models.base.Post;
import models.base.User;
import models.data.ResponseData;
import play.data.validation.Valid;
import play.mvc.Before;
import play.mvc.With;
import utils.JsonUtil;
import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Secure;
import controllers.Security;

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
	
	public static void save(@Valid(message="请检查") Post post){
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
	
	public static void update(long id){
		Post post = Post.findById(id);
		System.out.println("post=====================" + post);
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
		renderJSON(JsonUtil.toJson(Post.findByPageData(offset/limit+1, limit, search, null, sort, order, null),"posts","post","parent","children","profile"));
	}
}
