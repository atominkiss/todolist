package net.protoprint.todolist.service;

import net.protoprint.todolist.persist.entity.User;
import net.protoprint.todolist.persist.repo.UserRepo;
import net.protoprint.todolist.repr.UserRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	private final UserRepo userRepo;

	@Autowired

	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public void create(UserRepr userRepr){
		User user = new User();
		user.setUsername(userRepr.getUsername());
		user.setPassword(userRepr.getPassword());
		userRepo.save(user);
	}
}
