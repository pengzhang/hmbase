package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import models.base.Attachment;
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
	 * 附件上传
	 * @param upload_file
	 */
	public static void upload(File file) {

		final Attachment upload = new Attachment();
		try {
			upload.file = new Blob();
			upload.name = file.getName();
			upload.file.set(new FileInputStream(file), MimeTypes.getContentType(file.getName()));
			upload.save();
			renderJSON(new Simditor(true, "upload success", ImageServerDomain + "/download/file?id=" + upload.id));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			renderJSON(new Simditor(false, "upload failure", ""));
		}

	}
	
	public static void download(long id){
		Attachment attachment = Attachment.findById(id);
		response.setContentTypeIfNotSet(attachment.file.type());
		renderBinary(attachment.file.get(), attachment.name);
	}

}
