package com.userauth.org.common;

import java.io.Serializable;
import java.util.Map;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ApiResponse implements Cloneable, Serializable {

	private HttpStatus status;
	private String message;
	private boolean success;
	private Object error;
	private Object data;

	private ApiResponse() {
		super();
	}

	private static class Holder {
		private static final ApiResponse INSTANCE = new ApiResponse();
	}

	public static ApiResponse getInstance() {
		return Holder.INSTANCE;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setError(Map<String, String> error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public ApiResponse clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Creater of this class does not allow cloning.");
	}
}