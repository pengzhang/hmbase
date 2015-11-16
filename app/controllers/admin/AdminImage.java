package controllers.admin;

import java.util.Date;

import controllers.AdminController;
import models.base.Image;
import models.data.ResponseData;
import utils.ParamUtil;

public class AdminImage extends AdminController {
	
	public static void createImage(){
		Image image = ParamUtil.getJsonParams(request.body, Image.class);
		image.save();
		renderJSON(ResponseData.response(true, "图片创建成功"));
	}

	public static void modifyImage(long id){
		Image image = Image.findById(id);
		ParamUtil.getEditParams(request.body);
		image.updateDate = new Date();
		image.save();
		renderJSON(ResponseData.response(true, "图片修改成功"));
	}

	public static void removeImage(long id){
		Image image = Image.findById(id);
		image.status = true;
		image.save();
		renderJSON(ResponseData.response(true, "图片删除成功"));
	}

	public static void ImagesData(Integer limit, Integer offset, String search, String sort, String order){
		renderJSON(Image.findByPageData(offset/limit+1, limit, search, null, sort, order, null));
	}
}
