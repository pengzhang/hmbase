package modules.playext;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import play.Logger;
import play.templates.JavaExtensions;
import utils.RelativeDateFormat;

public class MengTemplateExtensions extends JavaExtensions {
	
	/**
	 * 截取字符串
	 * @param str
	 * @param length
	 * @return
	 */
	public static String cutString(String str, int length) {
		return StringUtils.substring(str, 0, length) + "...";
	}

	/**
	 * 截取字符串
	 * @param str
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static String cutString(String str, int beginIndex, int endIndex) {
		return StringUtils.substring(str,beginIndex, endIndex) + "...";
	}
	
	/**
	 * 转为相对时间
	 * @param str
	 * @return
	 */
	public static String relativeDate(Long str) {
		return RelativeDateFormat.format(new Date(str));
	}
	
	/**
	 * 获取html文本的第一张图片
	 * @param str
	 * @return
	 */
	public static String htmlFirstImage(String str){
		
		String image = "";
		try{
			Document doc = Jsoup.parse(str);
			image = StringUtils.defaultString(doc.getElementsByTag("img").first().attr("src"));
		}catch(Throwable t){
			t.printStackTrace();
		}
		return image;
	}
	
	/**
	 * 获取html的文本截取
	 * @param str
	 * @param length
	 * @return
	 */
	public static String htmlTextSubString(String str, int length){
		
		String text = "";
		try{
			Document doc = Jsoup.parse(str);
			text = StringUtils.substring(doc.text(), 0, length);
		}catch(Throwable t){
			t.printStackTrace();
		}
		return text;
	}
	
}
