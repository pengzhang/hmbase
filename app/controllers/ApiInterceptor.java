package controllers;

import org.bouncycastle.asn1.ocsp.ResponseData;

import com.google.gson.Gson;

import play.mvc.Before;
import play.mvc.Catch;
import play.mvc.Controller;

/**
 * 拦截器
 * @author zp
 *
 */
public class ApiInterceptor extends Controller {

	@Before
    static void actionAccess()
    {
		authorize();
    }

	private static void authorize() {
//		String ak = request.headers.get("access_token").value();
//		User user = User.find("accessToken", ak).first();
//		if(user != null){
//			renderJSON(ResponseData.response(false, "illegal access,您无权访问!"));
//		}
//		AccessLog.record(request, user.id, true);
	}

    /**
     * 异常处理
     * @param t
     */
    @Catch(value = Throwable.class,priority=3)
    public static void exceptionProcess(Throwable t)
    {
        t.printStackTrace();
//        renderJSON(ResponseData.response(false, "application error,程序错误,请检查参数,稍后重置!"));
    }
    
//    public static void main(String[] args) {
//		System.out.println(new Gson().toJson(ResponseData.response(false, "illegal access,您无权访问!")));
//		System.out.println(new Gson().toJson(ResponseData.response(false, "application error,程序错误,请检查参数,稍后重置!")));
//	}

}