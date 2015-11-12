package utils;

import org.apache.commons.lang.StringUtils;

import exceptions.ParamException;

public class ParamUtil {

	public static void validateString(String... params) throws ParamException{
		for (String param : params) {
			if (StringUtils.isEmpty(param)) {
				throw new ParamException("param is error");
			}
		}
	}

	public static void validateIntGTZero(int... params) throws ParamException {
		for (int param : params) {
			if (param <= 0) {
				throw new ParamException("param less than 0");
			}
		}
	}

	public static void validateLong(Long... params) throws ParamException {
		for (Long param : params) {
			if (param == null) {
				throw new ParamException("param is null");
			} else {
				if (param <= 0) {
					throw new ParamException("param_less_than_0");
				}
			}
		}
	}

	public static void validateLongGEZero(Long... params) throws ParamException {
		for (Long param : params) {
			if (param == null) {
				throw new ParamException("param is null");
			} else {
				if (param < 0) {
					throw new ParamException("param less than 0");
				}
			}
		}
	}

	public static void validateObject(Object... params) throws ParamException {
		for (Object param : params) {
			if (param == null) {
				throw new ParamException("param is null");
			}
		}
	}

}
