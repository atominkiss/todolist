package net.protoprint.todolist.security;

import net.protoprint.todolist.persist.entity.User;
import net.protoprint.todolist.persist.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

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
		Optional<User> optUser = userRepo.getUserByUsername(username);
		if (!optUser.isPresent()) {
			throw new UsernameNotFoundException("User not found!");
		}

		return new org.springframework.security.core.userdetails.User(
				optUser.get().getUsername(),
				optUser.get().getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("USER"))
		);
	}
}
