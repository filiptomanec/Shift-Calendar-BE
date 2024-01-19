package com.filiptomanec.shiftcalendarbe.configuration;

import lombok.Data;

@Data
public class Response <T> {
	private Object data;
	private String status = "success";
	private String message;
	private int code = 200;

	public static Response<Object> error(String message, int statusCode) {
		Response<Object> response = new Response<>();
		response.setStatus("error");
		response.setCode(statusCode);
		response.setData(null);
		response.setMessage(message);
		return response;
	}
}
