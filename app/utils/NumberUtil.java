package utils;

import exceptions.ServiceException;

public class NumberUtil {

	public static int defaultInteger(Integer param, int defaultValue) {
		if (param == null) {
			return defaultValue;
		}
		return param;
	}

	public static long defaultLong(Long param, long defaultValue) {
		if (param == null) {
			return defaultValue;
		}
		return param;
	}

	public static double defaultDouble(Double param, double defaultValue) {
		if (param == null) {
			return defaultValue;
		}
		return param;
	}

	public static float defaultFloat(Float param, float defaultValue) {
		if (param == null) {
			return defaultValue;
		}
		return param;
	}
}
