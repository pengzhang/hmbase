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

import play.data.validation.Required;
import play.db.Model;
import utils.MengDateUtils;
import utils.SQLUtil;
import exceptions.ServiceException;
import models.BaseModel;
import models.data.PageData;
import models.data.ResponseData;


@Entity
@Table(name="comment")
@org.hibernate.annotations.Table(comment="评论管理", appliesTo = "comment")
public class Comment extends BaseModel {
	
	@Required(message="评论内容不能为空")
	@Column(nullable=false,columnDefinition="varchar(2000) comment '评论内容'")
    public String content;
	
	@Column(nullable=false,columnDefinition="tinyint default 0 comment '审核状态:0_未审核,1_已审核'")
    public boolean audit = false;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
//	@ForeignKey(name="null")
	public User user;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="post_id")
//	@ForeignKey(name="null")
	public Post post;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="image_id")
//	@ForeignKey(name="null")
	public Image image;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pid")
//	@ForeignKey(name="null")
	public Comment parent;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent")
	public List<Comment> children;
	
	public static void remove(long id){
		Comment comment = Comment.findById(id);
		comment.status = true;
		comment.save();
	}
	
	public static long count(int day) {
		if(day<0){
			return Comment.count("createDate>? and createDate<?",MengDateUtils.dayTruncate(day),MengDateUtils.tomorrowTruncate());
		}
		return Comment.count("createDate>? and createDate<?",MengDateUtils.todayTruncate(),MengDateUtils.dayTruncate(day));
	}

	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Comment.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Comment.class, page, size, search, searchFields, orderBy, order, where);
	}

	
}
