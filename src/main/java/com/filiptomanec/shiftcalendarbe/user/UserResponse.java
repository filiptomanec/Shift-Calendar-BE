package com.filiptomanec.shiftcalendarbe.user;

import com.filiptomanec.shiftcalendarbe.token.TokenResponse;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
	private Integer id;
	private String username;
	private String email;
	private List<TokenResponse> tokens;
}
