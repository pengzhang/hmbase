package models.base;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import exceptions.ServiceException;
import models.BaseModel;
import models.data.PageData;
import play.db.Model;
import utils.SQLUtil;

@Entity
@Table(name = "tag")
@org.hibernate.annotations.Table(comment = "标签管理", appliesTo = "tag")
public class Tag extends BaseModel {

	@Column(nullable = false, columnDefinition = "varchar(255) comment '标签名称'")
	@Index(name="tag")
	public String tag;

	@Column(columnDefinition = "varchar(255) comment '标签描述'")
	public String description;

	@Column(columnDefinition = "varchar(255) comment '标签颜色'")
	public String tagcolor;

	public Tag(String tag) {
		this.tag = tag;
	}

	public static Tag findOrCreateByName(String name) {
		Tag tag = Tag.find("tag=?", name).first();
		if (tag == null) {
			tag = new Tag(name);
		}
		return tag;
	}
	
	public static List<Map> getPostCloud() {
	    List<Map> result = Tag.find(
	        "select new map(t.name as tag, count(p.id) as pound) from Post p join p.tags as t group by t.name order by t.name"
	    ).fetch();
	    return result;
	}

	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy,
			String order, String where) throws ServiceException {
		return SQLUtil.findByPage(Tag.class, page, size, search, searchFields, orderBy, order, where);
	}

	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy,
			String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(Tag.class, page, size, search, searchFields, orderBy, order, where);
	}
}
