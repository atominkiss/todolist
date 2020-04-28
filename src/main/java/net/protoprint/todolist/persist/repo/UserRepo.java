package net.protoprint.todolist.persist.repo;

import net.protoprint.todolist.persist.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}
