package models.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Blob;
import play.db.jpa.Model;

/**
 * 资源附件
 * @author zp
 *
 */
@Entity
@Table(name="attachment")
@org.hibernate.annotations.Table(comment="资源附件", appliesTo = "attachment")
public class Attachment extends Model{
	
	public Blob file;
	
	@Column(columnDefinition="varchar(255) comment '文件名称'")
	public String name;
	
	@Column(name="create_date",columnDefinition="datetime comment '创建日期'")
	public Date createDate = new Date();
}
