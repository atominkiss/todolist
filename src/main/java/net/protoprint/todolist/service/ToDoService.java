package net.protoprint.todolist.service;

import net.protoprint.todolist.persist.repo.ToDoRepo;
import net.protoprint.todolist.repr.ToDoRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ToDoService {

	private ToDoRepo toDoRepo;

	@Autowired
	public ToDoService(ToDoRepo toDoRepo) {
		this.toDoRepo = toDoRepo;
	}

	public Optional<ToDoRepr> findById(Long id) {
		return toDoRepo.findById(id)
				.map(ToDoRepr::new);
	}

	public List<ToDoRepr> fingToDosByUserId(Long userId) {
		return toDoRepo.fingToDosByUserId(userId);
	}

}
