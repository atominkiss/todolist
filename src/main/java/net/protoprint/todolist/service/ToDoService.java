package net.protoprint.todolist.service;

import net.protoprint.todolist.persist.entity.ToDo;
import net.protoprint.todolist.persist.entity.User;
import net.protoprint.todolist.persist.repo.ToDoRepo;
import net.protoprint.todolist.persist.repo.UserRepo;
import net.protoprint.todolist.repr.ToDoRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static net.protoprint.todolist.service.UserService.getCurrentUser;

@Service
@Transactional
public class ToDoService {

	private ToDoRepo toDoRepo;
	private UserRepo userRepo;

	@Autowired
	public ToDoService(ToDoRepo toDoRepo, UserRepo userRepo) {
		this.toDoRepo = toDoRepo;
		this.userRepo = userRepo;
	}

	public Optional<ToDoRepr> findById(Long id) {
		return toDoRepo.findById(id)
				.map(ToDoRepr::new);
	}

	public List<ToDoRepr> fingToDosByUserId(Long userId) {
		return toDoRepo.fingToDosByUserId(userId);
	}

	public void save(ToDoRepr toDoRepr) {
		Optional<String> currentUser = getCurrentUser();
		if (currentUser.isPresent()) {
			Optional<User> optUser = userRepo.getUserByUsername(currentUser.get());
			if (optUser.isPresent()) {
				ToDo toDo = new ToDo();
				toDo.setId(toDoRepr.getId());
				toDo.setDescription(toDoRepr.getDescription());
				toDo.setTargetDate(toDoRepr.getTargetDate());
				toDo.setUser(optUser.get());
				toDoRepo.save(toDo);
			}
		}
	}

	public void delete(Long id) {
		toDoRepo.findById(id)
				.ifPresent(toDo -> toDoRepo.delete(toDo));
	}
}
