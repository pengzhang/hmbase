package models.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import play.db.Model;
import utils.SQLUtil;
import exceptions.ServiceException;
import models.BaseModel;
import models.data.PageData;

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
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="tags")
	@ForeignKey(name="null")
	public List<Post> posts;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="tags")
	@ForeignKey(name="null")
	public List<Image> images;
	
	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Tag.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Tag.class, page, size, search, searchFields, orderBy, order, where);
	}
}
