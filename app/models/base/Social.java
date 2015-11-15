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
@Table(name="social")
@org.hibernate.annotations.Table(comment="社交化管理", appliesTo = "social")
public class Social extends BaseModel {
	
	@Column(nullable=false,name="social_type",columnDefinition="tinyint comment '社交化类型:0_喜欢 1_收藏'")
    public int SocialType; 
	
	@OneToOne
	public User user;
	
	@OneToMany
	public List<Post> posts;
	
	@OneToMany
	public List<Image> images;
	
	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Social.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Social.class, page, size, search, searchFields, orderBy, order, where);
	}
	
}
