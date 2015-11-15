package controllers;

import java.util.Date;

import models.base.Category;
import models.base.Comment;
import models.base.Image;
import models.base.Post;
import models.base.User;
import models.data.ResponseData;
import play.mvc.Controller;
import play.mvc.With;
import utils.ParamUtil;
import annotation.Login;

import com.google.gson.Gson;

@With(ActionInterceptor.class)
@Login()
public class AdminController extends Controller {
	
    public static void index(){
    	renderArgs.put("userTotal", User.count());
    	renderArgs.put("postTotal", Post.count());
    	renderArgs.put("imageTotal", Image.count());
    	renderArgs.put("commentTotal", Comment.count());
        render();
    }
    
    public static void users(){
    	String menu = "user";
    	render(menu);
    }
    
    public static void usersData(Integer limit, Integer offset, String search, String sort, String order){
    	renderJSON(User.findByPageData(offset/limit+1, limit, search, "username", sort, order, null));
    }
    
    public static void categories(){
    	String menu = "category";
    	render(menu);
    }
    
    public static void createCategory(){
    	Category category = ParamUtil.getJsonParams(request.body, Category.class);
    	category.save();
    	renderJSON(ResponseData.response(true, "分类创建成功"));
    }
    
    public static void modifyCategory(long id){
    	Category category = Category.findById(id);
    	ParamUtil.getEditParams(request.body);
    	category.edit(params.getRootParamNode(), "");
    	category.updateDate = new Date();
    	category.save();
    }
    
    public static void deleteCategory(long id){
    	Category category = Category.findById(id);
    	category.updateDate = new Date();
    	category.status = true;
    	category.save();
    }
    
    public static void categoriesData(Integer limit, Integer offset, String search, String sort, String order){
    	renderJSON(Category.findByPageData(offset/limit+1, limit, search, "category", sort, order, null));
    }

}