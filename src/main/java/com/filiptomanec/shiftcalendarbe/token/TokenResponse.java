package com.filiptomanec.shiftcalendarbe.token;

import lombok.Data;

@Data
public class TokenResponse {
	public Integer id;
	public String token;
	public boolean revoked;
	public boolean expired;
	public Integer userId;
}
