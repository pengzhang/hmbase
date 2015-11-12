package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.BaseModel;

@Entity
@Table(name="user")
@org.hibernate.annotations.Table(comment="用户信息", appliesTo = "user")
public class User extends BaseModel {
	
	@Column(columnDefinition = "varchar(255) comment '用户名'")
	public String username;
	
	@Column(columnDefinition = "varchar(255) comment '密码'")
	public String password;
	
	@Column(columnDefinition = "varchar(255) comment '姓名'")
	public String realname;
	
	@Column(columnDefinition = "tinyint comment '性别:0_男 1_女'")
	public boolean gender;
	
	@Column(name = "card_id", columnDefinition = "varchar(255) comment '身份证号'")
	public String cardId;
	
	@Column(columnDefinition = "varchar(255) comment '昵称'")
	public String nickname;
	
	@Column(columnDefinition = "varchar(255) comment '邮箱'")
	public String email;
	
	@Column(columnDefinition = "varchar(255) comment '手机'")
	public String mobile;
	
	@Column(columnDefinition = "varchar(255) comment '头像'")
	public String avatar;
	
	@Column(columnDefinition = "int comment '级别'")
	public int level;
	
	@Column(columnDefinition = "int comment '类型:0_admin 1_管理员 2_vip 3_普通'")
	public int type;
	
	@Column(columnDefinition = "varchar(255) comment '公司'")
	public String company;
	
	@Column(columnDefinition = "varchar(255) comment '职位'")
	public String jobtitle;
	
	@Column(columnDefinition = "varchar(255) comment '工作年限'")
	public String workYears;
	
	@Column(columnDefinition = "varchar(255) comment '城市'")
	public String city;
	
	@Column(name = "usersign", columnDefinition = "varchar(255) comment '用户签名'")
	public String userSign;
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
	
}