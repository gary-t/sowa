package pers.tbsowa.common.dto;

import java.io.Serializable;

/**
 * 返回页面的DTO
 * @author dell
 *
 * @param <T>
 */
public class ResultDTO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	/**返回结果*/
	private boolean success = Boolean.TRUE;
	
	/**返回码*/
	private String code;

	/**返回数据*/
	private T data;
	
	/**返回消息*/
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
