package controllers.admin;

import java.util.Date;

import models.base.Category;
import models.data.ResponseData;
import play.mvc.With;
import utils.ParamUtil;
import controllers.ActionInterceptor;
import controllers.AdminController;
import controllers.Secure;

@With({ActionInterceptor.class,Secure.class})
public class AdminCategory extends AdminController {
	
	public static void createCategory(){
		Category category = ParamUtil.getJsonParams(request.body, Category.class);
		category.save();
		renderJSON(ResponseData.response(true, "分类创建成功"));
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

	public static void removeCategory(long id){
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
