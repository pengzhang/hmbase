package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import play.db.jpa.Model;

/**
 * Base Model
 * @author zp
 *
 */
@MappedSuperclass
public class BaseModel extends Model{
	
	@Column(columnDefinition="tinyint comment '状态:0-假 ,1-真'")
	public boolean status = false;
	
	@Column(name="create_date",columnDefinition="datetime comment '创建日期'")
	public Date createDate = new Date();
	
	@Column(name="update_date",columnDefinition="datetime comment '更新日期'")
	public Date updateDate = new Date();
}
