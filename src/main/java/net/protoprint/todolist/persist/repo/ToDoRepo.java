package net.protoprint.todolist.persist.repo;

import net.protoprint.todolist.persist.entity.ToDo;
import net.protoprint.todolist.persist.entity.User;
import net.protoprint.todolist.repr.ToDoRepr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepo extends CrudRepository<ToDo, Long> {

    List<ToDoRepr> findToDoByUser_Username(String username);

    List<ToDoRepr> findToDoByUser(User user);
}
