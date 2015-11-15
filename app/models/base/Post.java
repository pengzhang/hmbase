package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.Model;
import utils.SQLUtil;
import exceptions.ServiceException;
import models.BaseModel;
import models.data.PageData;

@Entity
@Table(name="post")
@org.hibernate.annotations.Table(comment="内容管理", appliesTo = "post")
public class Post extends BaseModel {
	
	@Column(nullable=false,columnDefinition="varchar(255) comment '标题'")
	public String title;
	
	@Column(columnDefinition="varchar(255) comment '子标题'")
	public String subtitle;
	
	@Column(columnDefinition="varchar(255) comment '简介'")
	public String introduction;
	
	@Column(columnDefinition="text comment '内容'")
	public String content;
	
	@Column(columnDefinition="varchar(255) comment '封面图'")
	public String coverImage;
	
	@Column(columnDefinition="varchar(255) comment '封面图Alt'")
	public String coverImageAlt;
	
	@Column(columnDefinition="tinyint comment '0-不推荐 1-推荐'")
	public boolean recommend = false;
	
	@Column(columnDefinition="tinyint comment '0-普通 1-热文'")
	public boolean hot = false;
	
	@Column(columnDefinition="tinyint comment '0-普通 1-滚动'")
	public boolean banner = false;
	
	@OneToOne
	public User user;
	
	@OneToMany
	public List<Category> categories;
	
	@OneToMany
	public List<Image> images;
	
	@OneToMany
	public List<Tag> tags;
	
	@OneToMany
	public List<Comment> comments;
	
	@OneToMany
	public List<Social> social;

	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Post.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Post.class, page, size, search, searchFields, orderBy, order, where);
	}
}
