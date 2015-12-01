package models.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import exceptions.ServiceException;
import models.BaseModel;
import models.data.PageData;
import play.db.Model;
import utils.SQLUtil;

@Entity
@Table(name="user_profile")
@org.hibernate.annotations.Table(comment="用户资料信息_status:0_正常,1_删除", appliesTo = "user_profile")
public class UserProfile extends BaseModel {
	
	@Column(columnDefinition = "varchar(255) comment '姓名'")
	public String realname;
	
	@Column(columnDefinition = "tinyint comment '性别:0_男 1_女'")
	public boolean gender = false;
	
	@Column(name = "card_id", columnDefinition = "varchar(255) comment '身份证号'")
	public String cardId;
	
	@Column(columnDefinition = "int comment '级别'")
	public int level;
	
	@Column(columnDefinition = "varchar(255) comment '公司'")
	public String company;
	
	@Column(columnDefinition = "varchar(255) comment '职位'")
	public String jobtitle;
	
	@Column(name="work_years",columnDefinition = "varchar(255) comment '工作年限'")
	public String workYears;
	
	@Column(columnDefinition = "varchar(255) comment '城市'")
	public String city;
	
	@Column(name = "user_sign", columnDefinition = "varchar(255) comment '用户签名'")
	public String userSign;
	
	@Column(name = "user_id", columnDefinition = "bigint comment '用户ID'")
	public long userId;
	
	@OneToOne()
	@JoinColumn(name="user_id",insertable=false, updatable=false)
	@ForeignKey(name = "null")
	public User user;
	
	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(UserProfile.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(UserProfile.class, page, size, search, searchFields, orderBy, order, where);
	}

	@Override
	public String toString() {
		return "UserProfile [realname=" + realname + ", gender=" + gender + ", cardId=" + cardId + ", level=" + level
				+ ", company=" + company + ", jobtitle=" + jobtitle + ", workYears=" + workYears + ", city=" + city
				+ ", userSign=" + userSign + ", userId=" + userId + ", user=" + user + "]";
	}
}