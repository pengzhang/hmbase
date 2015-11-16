package utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import exceptions.ParamException;
import play.mvc.Scope.Params;

public class ParamUtil {
	
	 /** 
     * 处理request body为json转换
     * @param <T>
     * @param body
     * @param clazz
     * @return
     */
    public static <T> T getJsonParams(InputStream body, Class<T> clazz) throws ParamException{
    	try{
    		T t = new GsonBuilder().create().fromJson(new InputStreamReader(body), clazz);
    		return t;
    	}catch (Exception e){
    		throw new  ParamException("Json参数错误,请检查");
    	}
    }
    
    public static Map<String,Object> getJsonParams(InputStream body) throws ParamException{
    	try{
    		Map<String,Object> t = new GsonBuilder().create().fromJson(new InputStreamReader(body), new TypeToken<Map<String,Object>>(){}.getType());
    		return t;
    	}catch (Exception e){
    		throw new  ParamException("Json参数错误,请检查");
    	}
    }
    
    /**
     * 处理 request body 转换为 Params
     * @param body
     * @throws ParamException
     */
    public static void getEditParams(InputStream body) throws ParamException{
    	try{
    		Map<String,Object> t = new GsonBuilder().create().fromJson(new InputStreamReader(body), new TypeToken<Map<String, Object>>() {  
            }.getType());
    		for(String key : t.keySet()){
    			Params.current().put(key, String.valueOf(t.get(key)));
    		}
    	}catch (Exception e){
    		e.printStackTrace();
    		throw new  ParamException("Json参数错误,请检查");
    	}
    }

	public static void validateString(String... params) throws ParamException{
		for (String param : params) {
			if (StringUtils.isEmpty(param)) {
				throw new ParamException("参数为空,请检查");
			}
		}
	}

	public static void validateInt(Integer... params) throws ParamException {
		for (Integer param : params) {
			if (param == null) {
				throw new ParamException("参数为空,请检查");
			} else if (param <= 0) {
				throw new ParamException("参数应大于0");
			}
		}
	}
	
	public static void validateIntGEZero(Integer... params) throws ParamException {
		for (Integer param : params) {
			if (param == null) {
				throw new ParamException("参数为空,请检查");
			} else if (param < 0) {
				throw new ParamException("参数应大于等于0");
			}
		}
	}

	public static void validateLong(Long... params) throws ParamException {
		for (Long param : params) {
			if (param == null) {
				throw new ParamException("参数为空,请检查");
			} else if (param <= 0) {
				throw new ParamException("参数应大于0");
			}
		}
	}

	public static void validateLongGEZero(Long... params) throws ParamException {
		for (Long param : params) {
			if (param == null) {
				throw new ParamException("参数为空,请检查");
			} else if (param < 0) {
				throw new ParamException("参数应大于等于0");
			}
		}
	}

	public static void validateObject(Object... params) throws ParamException {
		for (Object param : params) {
			if (param == null) {
				throw new ParamException("参数为空,请检查");
			}
		}
	}

}
