package models.logs;

import javax.persistence.Entity;
import javax.persistence.Table;

import models.BaseModel;

@Entity
@Table(name="login_log")
public class LoginLog extends BaseModel{

}
