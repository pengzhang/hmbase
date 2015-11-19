package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import exceptions.ServiceException;
import models.BaseModel;
import models.data.PageData;
import play.db.Model;
import utils.SQLUtil;

@Entity
@Table(name="user")
@org.hibernate.annotations.Table(comment="用户信息_status:0_正常,1_删除", appliesTo = "user")
public class User extends BaseModel {
	
	@Column(columnDefinition = "varchar(255) comment '用户名'")
	public String username;
	
	@Column(columnDefinition = "varchar(255) comment '密码'")
	public String password;
	
	@Column(columnDefinition = "varchar(255) comment '昵称'")
	public String nickname;
	
	@Column(columnDefinition = "varchar(255) comment '邮箱'")
	public String email;
	
	@Column(columnDefinition = "varchar(255) comment '手机'")
	public String mobile;
	
	@Column(columnDefinition = "varchar(255) comment '头像'")
	public String avatar;
	
	@Column(columnDefinition = "int comment '类型:0_admin 1_管理员 2_vip 3_普通'")
	public int type;
	
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