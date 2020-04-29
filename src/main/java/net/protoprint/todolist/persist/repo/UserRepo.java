package net.protoprint.todolist.persist.repo;

import net.protoprint.todolist.persist.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

	boolean existsUserByUsername(String username);

	Optional<User> getUserByUsername(String username);
}
