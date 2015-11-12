package controllers;

import java.util.Arrays;

import models.logs.AccessLog;

import org.apache.commons.lang.math.NumberUtils;

import play.mvc.Before;
import play.mvc.Catch;
import play.mvc.Controller;
import annotation.Login;

/**
 * 拦截器
 * @author zp
 *
 */
public class ActionInterceptor extends Controller {

	@Before
    static void actionAccess()
    {
		authorize();
    }

	private static void authorize() {
		
		AccessLog.record(request, NumberUtils.toLong(session.get("userId")), false);
		
		try {
			Class controller = Class.forName("controllers."	+ request.action.substring(0, request.action.lastIndexOf(".")));
			if (controller.isAnnotationPresent(Login.class)) {
				String[] unless = ((Login) controller.getAnnotation(Login.class)).unless();
				if (unless != null && unless.length > 0) {
					if(!Arrays.asList(unless).contains(request.actionMethod)){
						loginPage();
					}
				}else{
					loginPage();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			error();
		}		
	}

	private static void loginPage(){
    	if(session.get("userId") == null){
    		redirect("/login");
    	}
    }
    
    /**
     * 异常处理
     * @param t
     */
    @Catch(value = Throwable.class,priority=3)
    public static void exceptionProcess(Throwable t)
    {
        t.printStackTrace();
        error();
    }

}