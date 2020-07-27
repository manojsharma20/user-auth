package com.userauth.org.common.utility;

import java.lang.reflect.Field;

import org.springframework.http.HttpStatus;

import com.userauth.org.common.ApiResponse;


public class Utility {

	private static ApiResponse apiResponse;

	public static ApiResponse prepareApiResponse(HttpStatus status, String message, boolean success) {
		apiResponse = getApiResponse();

		apiResponse.setStatus(status);
		apiResponse.setMessage(message);
		apiResponse.setSuccess(success);

		return apiResponse;
	}

	public static ApiResponse prepareApiResponseData(HttpStatus status, String message, Object data) {

		apiResponse = getApiResponse();

		apiResponse.setStatus(status);
		apiResponse.setMessage(message);
		apiResponse.setSuccess(true);
		apiResponse.setData(data);

		return apiResponse;
	}

	public static ApiResponse prepareApiResponseErrorData(HttpStatus status, String message, Object error) {

		apiResponse = getApiResponse();

		apiResponse.setStatus(status);
		apiResponse.setMessage(message);
		apiResponse.setSuccess(false);
		apiResponse.setError(error);

		return apiResponse;
	}

	@SuppressWarnings("unchecked")
	public static <T> T mergeObjects(T updated, T old) throws IllegalAccessException, InstantiationException {
		Class<?> clazz = updated.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Object returnValue = clazz.newInstance();
		for (Field field : fields) {
			field.setAccessible(true);
			Object value1 = field.get(updated);
			Object value2 = field.get(old);
			Object value = (value1 != null) ? value1 : value2;
			field.set(returnValue, value);
		}
		return (T) returnValue;
	}

	private static ApiResponse getApiResponse() {
		return ApiResponse.getInstance();
	}
}
