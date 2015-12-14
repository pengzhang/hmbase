package models.base;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import models.BaseModel;
import models.data.PageData;
import models.data.ResponseData;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.Model;
import play.libs.Crypto;
import utils.MengDateUtils;
import utils.SQLUtil;
import exceptions.ServiceException;

@Entity
@Table(name="user")
@org.hibernate.annotations.Table(comment="用户信息_status:0_正常,1_删除", appliesTo = "user")
public class User extends BaseModel {
	
	@Required(message="填写用户名")
	@MaxSize(value=20,message="用户名最多20个字符")
	@Column(columnDefinition = "varchar(20) comment '用户名'")
	public String username;
	
	@Required(message="请填写密码")
	@MinSize(value=6,message="密码至少6个字符")
	@Equals(value="repeatPassword",message="两次密码不一致")
	@Column(columnDefinition = "varchar(255) comment '密码'")
	public String password;
	
	@Transient
	@Required(message="请再次填写密码")
	public String repeatPassword;
	
	@MaxSize(value=20,message="昵称最多20个字符")
	@Column(columnDefinition = "varchar(20) comment '昵称'")
	public String nickname;
	
	@Email(message="请正确填写邮箱")
	@Column(columnDefinition = "varchar(255) comment '邮箱'")
	public String email;
	
	@Match(value="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message="请正确填写手机号码")
	@Column(columnDefinition = "varchar(255) comment '手机'")
	public String mobile;
	
	@Column(columnDefinition = "varchar(255) comment '头像'")
	public String avatar;
	
	@Column(columnDefinition = "tinyint comment '类型:0_admin 1_管理员 2_vip 3_普通'")
	public int type = 3;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="user")
	public UserProfile profile;
	
	public static void update(User user){
		user.updateDate = new Date();
		user.save();
	}
	
	public static void remove(long id){
		User user = User.findById(id);
		user.status = true;
		user.save();
	}
	
	public static long count(int day){
		if(day<0){
			return User.count("createDate>? and createDate<?",MengDateUtils.dayTruncate(day),MengDateUtils.tomorrowTruncate());
		}
		return User.count("createDate>? and createDate<?",MengDateUtils.todayTruncate(),MengDateUtils.dayTruncate(day));
	}
	
	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(User.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(User.class, page, size, search, searchFields, orderBy, order, where);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", repeatPassword=" + repeatPassword + ", nickname=" + nickname + ", email=" + email + ", mobile=" + mobile + ", avatar="
				+ avatar + ", type=" + type + "]";
	}
	
}
