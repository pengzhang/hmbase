package controllers.admin;

import java.util.Date;
import java.util.List;

import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Secure;
import models.base.Category;
import models.data.ResponseData;
import play.data.validation.Valid;
import play.mvc.With;
import utils.ParamUtil;

@With({ActionInterceptor.class,Secure.class})
public class AdminCategory extends AdminController {
	
	public static void create(){
		List<Category> categories = Category.findAll();
		render(categories);
	}
	
	public static void save(@Valid Category category){
		System.out.println(category);
		if(validation.hasErrors()){
			params.flash();
			validation.keep();
			create();
		}
		category.save();
		flash.success("创建分类%s成功", category.category);
		redirect("/admin/categories");
	}

	public static void modifyCategory(long id){
		Category category = Category.findById(id);
		ParamUtil.getEditParams(request.body);
		category.edit(params.getRootParamNode(), "");
		System.out.println(category);
		category.updateDate = new Date();
		category.save();
		renderJSON(ResponseData.response(true, "分类修改成功"));
	}
	
	public static void update(){
		
	}

	public static void remove(long id){
		Category category = Category.findById(id);
		category.updateDate = new Date();
		category.status = true;
		category.save();
		renderJSON(ResponseData.response(true, "分类删除成功"));
	}

	public static void categoriesData(Integer limit, Integer offset, String search, String sort, String order){
		renderJSON(Category.findByPageData(offset/limit+1, limit, search, null, sort, order, null));
	}
}
