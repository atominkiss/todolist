package net.protoprint.todolist.persist.repo;

import net.protoprint.todolist.persist.entity.ToDo;
import net.protoprint.todolist.repr.ToDoRepr;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepo extends CrudRepository<ToDo, Long> {

	//запрос на вытаскивание из базы списка тудушек связанных с ID конкретного пользователя
	@Query("select new net.protoprint.todolist.repr.ToDoRepr(t) from ToDo t " +
			"inner join fetch User u " +
			"where u.id = :userId")
	List<ToDoRepr> fingToDosByUserId(@Param("userId") Long userId);

}
