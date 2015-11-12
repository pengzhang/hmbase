package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.BaseModel;

@Entity
@Table(name="article")
@org.hibernate.annotations.Table(comment="文章管理", appliesTo = "article")
public class Article extends BaseModel {
	
	@Column(nullable=false,columnDefinition="varchar(255) comment '文章标题'")
	public String title;
	
	@Column(columnDefinition="varchar(255) comment '文章子标题'")
	public String subtitle;
	
	@Column(columnDefinition="varchar(255) comment '文章简介'")
	public String introduction;
	
	@Column(columnDefinition="text comment '文章内容'")
	public String content;
	
	@Column(columnDefinition="varchar(255) comment '封面图'")
	public String coverImage;
	
	@Column(columnDefinition="varchar(255) comment '封面图Alt'")
	public String coverImageAlt;
	
	@Column(columnDefinition="tinyint comment '0-不推荐 1-推荐'")
	public boolean recommend = false;
	
	@Column(columnDefinition="tinyint comment '0-普通 1-热文'")
	public boolean hot = false;
	
	@Column(columnDefinition="tinyint comment '0-普通文章 1-滚动'")
	public boolean banner = false;
	
	public User user;
	
	public Category category;
	
	@OneToMany
	public List<Image> images;
	
	@OneToMany
	public List<Tag> tags;
	
	@OneToMany
	public List<Comment> comments;
	
	@OneToMany
	public List<Social> social;

}
