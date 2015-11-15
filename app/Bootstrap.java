import models.base.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import services.base.UserService;

@OnApplicationStart
public class Bootstrap extends Job {
	
	public void doJob(){
		initData();
	}
	
	private void initData(){
		createAdminUser();
	}
	
	private void createAdminUser(){
		if(User.find("username", "admin").first() == null){
		User user = new User();
		user.username = "admin";
		user.password = "admin";
		user.type = 1;
		UserService.register(user);
		}
	}
}