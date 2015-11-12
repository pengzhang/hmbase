package models.base;

import java.util.Date;

import javax.persistence.Column;

import play.db.jpa.Blob;
import play.db.jpa.Model;

/**
 * 图片上传
 * @author zp
 *
 */
public class ImageUpload extends Model{
	
	public Blob file;
	
	@Column(columnDefinition="varchar(255) comment '文件名称'")
	public String name;
	
	@Column(name="create_date",columnDefinition="datetime comment '创建日期'")
	public Date createDate = new Date();
}
