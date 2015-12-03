package models.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import models.BaseModel;
import models.data.PageData;

import org.hibernate.annotations.ForeignKey;

import com.google.gson.annotations.Expose;

import play.data.validation.MaxSize;
import play.db.Model;
import utils.SQLUtil;
import exceptions.ServiceException;

@Entity
@Table(name="user_profile")
@org.hibernate.annotations.Table(comment="用户资料信息_status:0_正常,1_删除", appliesTo = "user_profile")
public class UserProfile extends BaseModel implements Serializable {
	
	@Column(columnDefinition = "varchar(255) comment '姓名'")
	public String realname;
	
	@Column(columnDefinition = "tinyint comment '性别:0_男 1_女'")
	public boolean gender = false;
	
	@MaxSize(value=18,message="身份证号最多18个字符")
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
	
	@OneToOne(cascade=CascadeType.ALL,optional=false)
	@JoinColumn(name="userid")
	@ForeignKey(name="null")
	public User user;
	
	public static void save(User user){
		UserProfile profile = new UserProfile();
		profile.user = user;
		profile.save();
	}
	
	public static List<Model> findByPage(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPage(UserProfile.class, page, size, search, searchFields, orderBy, order, where);
	}
	
	public static PageData findByPageData(int page, int size, String search, String searchFields, String orderBy, String order, String where) throws ServiceException {
		return SQLUtil.findByPageData(UserProfile.class, page, size, search, searchFields, orderBy, order, where);
	}

}
