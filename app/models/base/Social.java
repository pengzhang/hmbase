package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import models.BaseModel;

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
	
}
