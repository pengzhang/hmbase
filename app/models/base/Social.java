package models.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="social")
@org.hibernate.annotations.Table(comment="社交化管理", appliesTo = "social")
public class Social extends BaseModel {
	
	@Column(nullable=false,name="social_type",columnDefinition="tinyint comment '社交化类型:0_喜欢 1_收藏'")
    public int SocialType; 
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	@ForeignKey(name="null")
	public User user;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="post_id")
	@ForeignKey(name="null")
	public Post post;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="image_id")
	@ForeignKey(name="null")
	public Image image;
	
	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Social.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Social.class, page, size, search, searchFields, orderBy, order, where);
	}
	
}
