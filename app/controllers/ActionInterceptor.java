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
		AccessLog.record(request, NumberUtils.toLong(session.get("userId")), false);
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