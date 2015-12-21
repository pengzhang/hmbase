package models.base;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import exceptions.ServiceException;
import models.BaseModel;
import models.data.PageData;
import play.data.validation.Match;
import play.data.validation.Required;
import play.db.Model;
import utils.SQLUtil;

@Entity
@Table(name="category")
@org.hibernate.annotations.Table(comment="分类管理", appliesTo = "category")
public class Category extends BaseModel {
    
	@Required(message="分类名称必须填写")
	@Column(nullable=false,columnDefinition="varchar(255) comment '分类名称'")
    public String category;  
	
	@Column(columnDefinition="varchar(255) comment '分类描述'")
    public String description;  
	
	@Match(value="[0-9]{1}",message="分类顺序范围0-9")
	@Column(name="category_order",columnDefinition="int comment '分类排序,默认:0'")
    public int categoryOrder = 0;    
	
	@Column(columnDefinition="tinyint comment '0-不推荐 1-推荐'")
	public boolean recommend = false;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pid")
//	@ForeignKey(name="null")
	public Category parent;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent",fetch=FetchType.LAZY)
	public List<Category> children;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="categories",fetch=FetchType.LAZY)
//	@ForeignKey(name="null")
	public List<Post> posts;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="categories",fetch=FetchType.LAZY)
//	@ForeignKey(name="null")
	public List<Image> images;
	
	public static void update(Category category) {
		category.updateDate = new Date();
		category.save();
	}
	
	public static void remove(Long id){
		Category category = Category.findById(id);
		category.updateDate = new Date();
		category.status = true;
		category.save();
	}
	
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
