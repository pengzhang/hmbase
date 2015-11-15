package models.logs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import eu.bitwalker.useragentutils.UserAgent;
import models.BaseModel;
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

}
