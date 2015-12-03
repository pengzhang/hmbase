package models.base;

import java.util.List;import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

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
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="image")
	public List<Comment> comments;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="image")
	public List<Social> social;

	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Image.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Image.class, page, size, search, searchFields, orderBy, order, where);
	}
}
