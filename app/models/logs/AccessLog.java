package models.logs;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.google.gson.Gson;

import eu.bitwalker.useragentutils.UserAgent;
import models.BaseModel;
import play.db.jpa.JPA;
import play.mvc.Http.Request;

/**
 * 访问日志
 * @author zp
 *
 */

@Entity
@Table(name = "accesslog")
@org.hibernate.annotations.Table(comment = "访问日志_status:0-page,1-api", appliesTo = "accesslog")
public class AccessLog extends BaseModel {

	@Column(name = "action_method", columnDefinition = "varchar(100) comment 'Action方法名称'")
	public String actionMethod;

	@Column(name = "action_url", columnDefinition = "varchar(2000) comment 'ActionUrl地址'")
	public String actionUrl;

	@Column(name = "user_id", columnDefinition = "bigint comment '用户Id:0-未登录'")
	public long userId = 0;

	@Column(columnDefinition = "varchar(50) comment 'IP地址'")
	public String ip;

	@Column(columnDefinition = "varchar(100) comment '浏览器名称'")
	public String brower;

	@Column(name = "browser_version", columnDefinition = "varchar(100) comment '浏览器版本'")
	public String browserVersion;

	@Column(name = "browser_type", columnDefinition = "varchar(100) comment '浏览器类型'")
	public String browserType;

	@Column(name = "rendering_engine", columnDefinition = "varchar(100) comment '浏览器渲染引擎'")
	public String renderingEngine;

	@Column(name = "operating_system", columnDefinition = "varchar(100) comment '操作系统'")
	public String operatingSystem;

	@Column(name = "device_type", columnDefinition = "varchar(100) comment '设备类型'")
	public String deviceType;

	/**
	 * 记录用户访问日志
	 * @param request
	 * @param userId
	 */
	public static void record(Request request, long userId, boolean status) {
		UserAgent userAgent = new UserAgent(request.headers.get("user-agent").value());
		AccessLog accessLog = new AccessLog();
		accessLog.actionMethod = request.actionMethod;
		accessLog.actionUrl = request.url;
		accessLog.userId = userId;
		accessLog.ip = request.remoteAddress;
		accessLog.brower = userAgent.getBrowser().getName();
		accessLog.browserVersion = userAgent.getBrowserVersion().getVersion();
		accessLog.browserType = userAgent.getBrowser().getBrowserType().getName();
		accessLog.renderingEngine = userAgent.getBrowser().getRenderingEngine().name();
		accessLog.operatingSystem = userAgent.getOperatingSystem().getName();
		accessLog.deviceType = userAgent.getOperatingSystem().getDeviceType().getName();
		accessLog.status = status;
		accessLog.save();
	}
	
	public static Map<String, String> getRecentAccessRecord(int day) throws ParseException{
	    
	    List<String> chart_title = new ArrayList<String>();
	    List<Long> chart_content = new ArrayList<Long>();
	    for(int i=0 ; i<day ; i++ ){
	    	String title = DateFormatUtils.format(DateUtils.addDays(new Date(), -i), "yyyy-MM-dd");
	    	chart_title.add(title);
	    	Date start = DateUtils.truncate(DateUtils.addDays(new Date(), -i), Calendar.DAY_OF_MONTH);
	    	Date end = DateUtils.truncate(DateUtils.addDays(new Date(), -(i-1)), Calendar.DAY_OF_MONTH);
	    	chart_content.add(AccessLog.count("createDate >= ? and createDate < ?", start, end ));
	    }
	    
	    Map<String,String> map = new HashMap<String,String>();
	    map.put("chart_title", new Gson().toJson(chart_title).toString());
	    map.put("chart_content", new Gson().toJson(chart_content).toString());
	    
		return map;
	}
	

}
