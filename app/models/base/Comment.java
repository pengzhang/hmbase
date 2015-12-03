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
@Table(name="comment")
@org.hibernate.annotations.Table(comment="评论管理", appliesTo = "comment")
public class Comment extends BaseModel {
	
	@Column(nullable=false,columnDefinition="varchar(2000) comment '评论内容'")
    public String content;
	
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
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pid")
	@ForeignKey(name="null")
	public Comment parent;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent")
	public List<Comment> children;

	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Comment.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Comment.class, page, size, search, searchFields, orderBy, order, where);
	}
}
