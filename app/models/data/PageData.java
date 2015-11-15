package models.data;

import java.util.List;
import play.db.Model;

/**
 * 分页数据封装
 * 
 * @author zp
 * 
 */
public class PageData {

	public long totalCount;
	public long count;
	public int page;
	public int size;
	public String orderBy;
	public String order;
	public List<play.db.Model> model;

	public PageData() {
	}

	public PageData(long totalCount, long count, int page, int size, String orderBy, String order, List<Model> model) {
		this.totalCount = totalCount;
		this.count = count;
		this.page = page;
		this.size = size;
		this.orderBy = orderBy;
		this.order = order;
		this.model = model;
	}

}
