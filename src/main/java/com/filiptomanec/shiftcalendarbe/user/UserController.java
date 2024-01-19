package com.filiptomanec.shiftcalendarbe.user;

import com.filiptomanec.shiftcalendarbe.token.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Users endpoint", description = "Users related operations.")
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping
	@Operation(summary = "Returns all users.")
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		List<User> users = userService.findAllUsers();
		List<UserResponse> userResponses = users.stream().map(user -> {
			UserResponse userResponse = new UserResponse();
			userResponse.setId(user.getId());
			userResponse.setUsername(user.getUserName());
			userResponse.setEmail(user.getEmail());
			userResponse.setTokens(user.getTokens().stream().map(token -> {
				TokenResponse tokenResponse = new TokenResponse();
				tokenResponse.setId(token.getId());
				tokenResponse.setToken(token.getToken());
				tokenResponse.setRevoked(token.isRevoked());
				tokenResponse.setExpired(token.isExpired());
				tokenResponse.setUserId(token.getUser().getId());
				return tokenResponse;
			}).toList());
			return userResponse;
		}).toList();
		return ResponseEntity.ok(userResponses);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Returns user with given ID.")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		User user = userService.get(id);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Updates user with given ID.")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserUpdateRequest userUpdateRequest) {
		User updatedUser = userService.updateUser(id, userUpdateRequest);
		return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes user with given ID.")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		userService.removeUser(id);
		return ResponseEntity.ok().build();
	}
}
