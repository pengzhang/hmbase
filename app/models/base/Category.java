package models.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import exceptions.ServiceException;
import models.BaseModel;
import models.data.PageData;
import play.db.Model;
import utils.SQLUtil;

@Entity
@Table(name="category")
@org.hibernate.annotations.Table(comment="分类管理", appliesTo = "category")
public class Category extends BaseModel {
    
	@Column(nullable=false,columnDefinition="varchar(255) comment '分类名称'")
    public String category;  
	
	@Column(columnDefinition="varchar(255) comment '分类描述'")
    public String description;  
	
	@Column(name="category_order",columnDefinition="int comment '分类排序,默认:0'")
    public int categoryOrder = 0;    
	
	@Column(columnDefinition="tinyint comment '0-不推荐 1-推荐'")
	public boolean recommend = false;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pid")
	@ForeignKey(name="null")
	public Category parent;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent")
	public List<Category> children;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="categories")
	@ForeignKey(name="null")
	public List<Post> posts;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="categories")
	@ForeignKey(name="null")
	public List<Image> images;
	
	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Category.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Category.class, page, size, search, searchFields, orderBy, order, where);
	}

	@Override
	public String toString() {
		return "Category [category=" + category + ", description=" + description + ", categoryOrder=" + categoryOrder
				+ ", recommend=" + recommend + ", parent=" + parent + ", children=" + children + ", posts=" + posts
				+ ", images=" + images + "]";
	}
	
}
