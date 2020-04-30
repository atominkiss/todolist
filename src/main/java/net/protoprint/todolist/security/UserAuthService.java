package net.protoprint.todolist.security;

import lombok.extern.slf4j.Slf4j;
import net.protoprint.todolist.persist.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Slf4j
@Service
@Transactional
public class UserAuthService implements UserDetailsService {

	private final UserRepo userRepo;

	@Autowired
	public UserAuthService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Spring security loading user by name");

		return userRepo.getUserByUsername(username)
				.map(user -> new org.springframework.security.core.userdetails.User(
						user.getUsername(),
						user.getPassword(),
						Collections.singletonList(new SimpleGrantedAuthority("USER"))))
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}