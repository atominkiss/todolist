package net.protoprint.todolist.service;

import net.protoprint.todolist.persist.entity.User;
import net.protoprint.todolist.persist.repo.UserRepo;
import net.protoprint.todolist.repr.UserRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

	private final UserRepo userRepo;

	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public void create(UserRepr userRepr){
		User user = new User();
		user.setUsername(userRepr.getUsername());
		user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
		userRepo.save(user);
	}

	public Optional<Long> getCurrentUserId() {
		Optional<String> currentUser = getCurrentUser();
		if (currentUser.isPresent()) {
			return userRepo.getUserByUsername(currentUser.get())
					.map(User::getId);
		}
		return Optional.empty();
	}

	public static Optional<String> getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return Optional.of(authentication.getName());
		}
		return Optional.empty();
	}
}
