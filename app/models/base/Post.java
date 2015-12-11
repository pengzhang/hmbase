package models.base;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import exceptions.ServiceException;
import models.BaseModel;
import models.data.PageData;
import play.data.validation.Required;
import play.db.Model;
import utils.SQLUtil;

@Entity
@Table(name="post")
@org.hibernate.annotations.Table(comment="内容管理", appliesTo = "post")
public class Post extends BaseModel {
	
	@Required(message="文章标题不能为空")
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
	
	@Column(columnDefinition="tinyint default 0 comment '0-草稿 1-发布'")
	public boolean draft = false;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	@ForeignKey(name="null")
	public User user;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@ForeignKey(name="null")
	public List<Category> categories;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@ForeignKey(name="null")
	public List<Tag> tags;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="post")
	public List<Comment> comments;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="post")
	public List<Social> social;
	
	public static void update(Post post){
		post.updateDate = new Date();
		post.save();
	}
	
	public static void remove(long id){
		Post post = Post.findById(id);
		post.status = true;
		post.save();
	}
	
	public static void publish(long id){
		Post post = Post.findById(id);
		post.draft = true;
		post.save();
	}

	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Post.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Post.class, page, size, search, searchFields, orderBy, order, where);
	}
}
