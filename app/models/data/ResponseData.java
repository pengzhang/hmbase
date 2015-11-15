package models.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 响应结果
 * @author zp
 *
 */
public class ResponseData {
	
	/**
     * 返回结果
     * @param status
     * @param message
     * @return
     */
    public static Map<String, Object> response(boolean status, String message) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("status", status);
    	map.put("data", "null");
    	map.put("message", StringUtils.defaultString(message));
    	return map;
    }

	 /**
     * 返回结果
     * @param status
     * @param data
     * @param message
     * @return
     */
    public static Map<String, Object> response(boolean status, Object data, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("data", ObjectUtils.defaultIfNull(data, "null"));
        map.put("message", StringUtils.defaultString(message));
        return map;
    }

}
