package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import models.base.ImageUpload;
import models.data.Simditor;
import play.Play;
import play.cache.Cache;
import play.db.jpa.Blob;
import play.libs.Images;
import play.libs.MimeTypes;
import play.mvc.Controller;

public class Assist extends Controller {

	public final static String ImageServerDomain = Play.configuration.getProperty("image.server.domain");

	/**
	 * 验证码
	 */
	public static void captcha() {
		Images.Captcha captcha = Images.captcha();
		captcha.addNoise();
		String code = captcha.getText();
		Cache.add(code, code, "2mn");
		renderBinary(captcha);
	}

	/**
	 * 图片上传
	 * @param upload_file
	 */
	public static void upload(File upload_file) {

		final ImageUpload upload = new ImageUpload();
		try {
			upload.file = new Blob();
			upload.name = upload_file.getName();
			upload.file.set(new FileInputStream(upload_file), MimeTypes.getContentType(upload_file.getName()));
			upload.save();
			renderJSON(new Simditor(true, "upload success", ImageServerDomain + "/upload/" + upload.file.getUUID()));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			renderJSON(new Simditor(false, "upload failure", ""));
		}

	}

}
