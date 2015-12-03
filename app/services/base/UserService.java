package services.base;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import play.libs.Crypto;
import exceptions.ParamException;
import exceptions.ServiceException;
import models.base.User;
import models.base.UserProfile;
import modules.notify.Notify;
import utils.ParamUtil;

public class UserService {

	public static User login(String username, String password) throws ParamException, ServiceException{
		ParamUtil.validateString(username, password);
		User user = User.find("username=? and password=? and status=?", username, DigestUtils.md5Hex(password), false).first();
		if(user == null){
			throw new ServiceException("用户名或者密码错误");
		}
		Notify.all(user, "login");
		//TODO LoginLog
		return user;
	}
	
	public static User register(User user) throws ParamException, ServiceException{
		ParamUtil.validateObject(user);
		User $user = User.find("username=? and status=?", user.username, false).first();
		if($user != null){
			throw new ServiceException("用户已存在");
		}
		user.password = Crypto.passwordHash(user.password);
		user.save();
		UserProfile.save(user);
		Notify.all(user, "register");
		return user;
	}
	
	public static boolean resetPassword(long id, String password) throws ParamException, ServiceException{
		ParamUtil.validateString(password);
		User user = User.findById(id);
		if(user == null){
			throw new ServiceException("用户不存在");
		}
		user.password = DigestUtils.md5Hex(password);
		user.save();
		Notify.all(user, "resetPassword");
		return true;
	}
	
	public static boolean resetPassword(long id, String oldPassword, String password) throws ParamException, ServiceException{
		ParamUtil.validateString(oldPassword,password);
		User user = User.findById(id);
		if(user == null){
			throw new ServiceException("用户不存在");
		}else if(!StringUtils.equals(user.password, DigestUtils.md5Hex(oldPassword))){
			throw new ServiceException("旧密码错误");
		}
		user.password = DigestUtils.md5Hex(password);
		user.save();
		Notify.all(user, "resetPassword");
		return true;
	}
}
