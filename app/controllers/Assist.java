package controllers;

import play.libs.Images;
import play.mvc.Controller;

public class Assist extends Controller {
	
	/**
	 * 验证码
	 */
	public static void captcha() {
		Images.Captcha captcha = Images.captcha();
		renderBinary(captcha);
	}
}
