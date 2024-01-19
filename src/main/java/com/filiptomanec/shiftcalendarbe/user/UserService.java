package com.filiptomanec.shiftcalendarbe.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));
		List<String> roles = new ArrayList<>();
		roles.add("USER");
		return org.springframework.security.core.userdetails.User.builder()
		                                                         .username(user.getEmail())
		                                                         .password(user.getPassword())
		                                                         .roles(roles.toArray(new String[0]))
		                                                         .build();
	}

	public User createUser(UserCreateRequest userCreateRequest) {
		if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
			throw new RuntimeException("User with this email already exists!");
		}
		User user = new User();
		user.setUsername(userCreateRequest.getUsername());
		user.setEmail(userCreateRequest.getEmail());
		user.setPassword(userCreateRequest.getPassword());
		return this.userRepository.save(user);
	}

	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}

	public User get(Integer id) {
		return this.userRepository.findById(id).orElse(null);
	}

	public User getByUsername(String username) {
		return this.userRepository.findByUsername(username).orElse(null);
	}

	public User updateUser(Integer id, UserUpdateRequest userUpdateRequest) {
		User user = get(id);
		user.setUsername(userUpdateRequest.getUsername());
		user.setEmail(userUpdateRequest.getEmail());
		user.setPassword(userUpdateRequest.getPassword());
		return this.userRepository.save(user);
	}

	public void removeUser(Integer id) {
		this.userRepository.deleteById(id);
	}
}
