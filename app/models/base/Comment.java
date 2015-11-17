package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@OneToOne
	public User user;
	
	@OneToOne
	public Post post;
	
	@ManyToOne
	public Comment parent;
	
	@OneToMany
	public List<Comment> children;

	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Comment.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Comment.class, page, size, search, searchFields, orderBy, order, where);
	}
}
