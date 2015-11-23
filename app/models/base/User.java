package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import models.BaseModel;
import models.data.PageData;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.Model;
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
	
	@Column(columnDefinition = "int comment '类型:0_admin 1_管理员 2_vip 3_普通'")
	public int type = 3;
	
	@OneToOne
	public UserProfile userProfile;
	
	//我的标签
	@OneToMany
	public List<Tag> tags;
	
	@OneToMany
	public List<Post> posts;

	@OneToMany
	public List<Image> images;
	
	@OneToMany
	public List<Social> socials;
	
	@OneToMany
	public List<Comment> comments;
	
	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(User.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(User.class, page, size, search, searchFields, orderBy, order, where);
	}
	
}