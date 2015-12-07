package controllers.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Secure;
import models.base.Category;
import models.base.User;
import models.data.ResponseData;
import play.data.validation.Valid;
import play.libs.Crypto;
import play.mvc.With;
import utils.JsonUtil;
import utils.ParamUtil;

@With({ActionInterceptor.class,Secure.class})
public class AdminCategory extends AdminController {
	
	public static void create(){
		List<Category> categories = Category.findAll();
		render(categories);
	}
	
	public static void save(@Valid(message="请检查分类信息") Category category){
		if(validation.hasErrors()){
			params.flash();
			validation.keep();
			create();
		}
		category.save();
		flash.success("创建分类%s成功", category.category);
		redirect("/admin/categories");
	}

	public static void modify(long id){
		Category category = Category.findById(id);
		List<Category> categories = Category.findAll();
		render(category,categories);
		
	}
	
	public static void update(Long id){
		Category category  = Category.findById(id);
		
		category.edit(params.getRootParamNode(), "category");
		validation.valid(category);
	    if(validation.hasErrors()) {
	    	category.refresh();
	        validation.keep();
	        modify(id);
	    }
	    
	    Category.update(category);
		flash.success("修改分类%s成功", category.category);
		modify(id);
	}

	public static void remove(long id){
		Category category = Category.findById(id);
		category.updateDate = new Date();
		category.status = true;
		category.save();
		renderJSON(ResponseData.response(true, "分类删除成功"));
	}

	public static void categoriesData(Integer limit, Integer offset, String search, String sort, String order){
		renderJSON(JsonUtil.toJson(Category.findByPageData(offset/limit+1, limit, search, null, sort, order, null),"children"));
	}
}
