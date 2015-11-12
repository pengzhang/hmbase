package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.BaseModel;


@Entity
@Table(name="comment")
@org.hibernate.annotations.Table(comment="评论管理", appliesTo = "comment")
public class Comment extends BaseModel {
	
	@Column(nullable=false,columnDefinition="varchar(2000) comment '评论内容'")
    public String content;
	
	public User user;
	
	@ManyToOne
	public Comment parent;
	
	@OneToMany
	public List<Comment> children;
	
	@OneToMany
	public List<Post> posts;

	@OneToMany
	public List<Image> images;
}
