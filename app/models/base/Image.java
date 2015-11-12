package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.BaseModel;

@Entity
@Table(name="image")
@org.hibernate.annotations.Table(comment="图片管理", appliesTo = "image")
public class Image extends BaseModel {

	@Column(columnDefinition="varchar(255) comment '图片名称'")
	public String imageName;
	
	@Column(columnDefinition="varchar(255) comment '图片描述'")
	public String imageDesc;
	
	@Column(columnDefinition="varchar(1000) comment '图片地址'")
	public String imageUrl;
	
	public User user;
	
	@OneToMany
	public List<Category> categories;
	
	@OneToMany
	public List<Tag> tags;
	
	@OneToMany
	public List<Comment> comments;
	
	@OneToMany
	public List<Social> social;

}
