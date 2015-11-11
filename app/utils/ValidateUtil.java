package utils;

import org.apache.commons.lang.StringUtils;

import exceptions.ServiceException;

public class ValidateUtil {

	public static void validateString(String... params) throws ServiceException {
		for (String param : params) {
			if (StringUtils.isEmpty(param)) {
				throw new ServiceException("param is error");
			}
		}
	}

	public static void validateIntGTZero(int... params) throws ServiceException {
		for (int param : params) {
			if (param <= 0) {
				throw new ServiceException("param less than 0");
			}
		}
	}

	public static void validateLong(Long... params) throws ServiceException {
		for (Long param : params) {
			if (param == null) {
				throw new ServiceException("param is null");
			} else {
				if (param <= 0) {
					throw new ServiceException("param_less_than_0");
				}
			}
		}
	}

	public static void validateLongGEZero(Long... params) throws ServiceException {
		for (Long param : params) {
			if (param == null) {
				throw new ServiceException("param is null");
			} else {
				if (param < 0) {
					throw new ServiceException("param less than 0");
				}
			}
		}
	}

	public static void validateObject(Object... params) throws ServiceException {
		for (Object param : params) {
			if (param == null) {
				throw new ServiceException("param is null");
			}
		}
	}

}
