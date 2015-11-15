package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.data.PageData;
import play.db.jpa.Model;
import exceptions.ServiceException;

public class SQLUtil {
	
	public static List<play.db.Model> findByPage(Class clazz, int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		int offset = (page - 1) * size;
		List<String> properties = searchFields == null ? new ArrayList<String>(0) : Arrays.asList(searchFields.split("[ ]"));
		try {
			return Model.Manager.factoryFor(clazz).fetch(offset, size, orderBy, order, properties, search, where);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询数据失败");
		}
	}

	public static PageData findByPageData(Class clazz, int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		PageData pageData = new PageData();
		int offset = (page - 1) * size;
		List<String> properties = searchFields == null ? new ArrayList<String>(0) : Arrays.asList(searchFields.split("[ ]"));
		try {
			pageData.total = Model.Manager.factoryFor(clazz).count(properties, search, where);
			pageData.rows = Model.Manager.factoryFor(clazz).fetch(offset, size, orderBy, order, properties, search, where);
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询数据失败");
		}
	}
}
