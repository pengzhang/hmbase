package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.BaseModel;

@Entity
@Table(name="tag")
@org.hibernate.annotations.Table(comment="标签管理", appliesTo = "tag")
public class Tag extends BaseModel {
    
	@Column(nullable=false,columnDefinition="varchar(255) comment '标签名称'")
    public String tag;  
	
	@Column(columnDefinition="varchar(255) comment '标签描述'")
    public String description;  
	
	@Column(columnDefinition="varchar(255) comment '标签颜色'")
    public String tagcolor; 
	
	@OneToMany
	public List<Post> posts;
	
	@OneToMany
	public List<Image> images;
}
