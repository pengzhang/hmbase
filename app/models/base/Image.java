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
@Table(name="image")
@org.hibernate.annotations.Table(comment="图片管理", appliesTo = "image")
public class Image extends BaseModel {

	@Column(columnDefinition="varchar(255) comment '图片名称'")
	public String imageName;
	
	@Column(columnDefinition="varchar(255) comment '图片描述'")
	public String imageDesc;
	
	@Column(columnDefinition="varchar(1000) comment '图片地址'")
	public String imageUrl;
	
	@OneToOne
	public User user;
	
	@OneToMany
	public List<Category> categories;
	
	@OneToMany
	public List<Tag> tags;
	
	@OneToMany
	public List<Comment> comments;
	
	@OneToMany
	public List<Social> social;

	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Image.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Image.class, page, size, search, searchFields, orderBy, order, where);
	}
}
