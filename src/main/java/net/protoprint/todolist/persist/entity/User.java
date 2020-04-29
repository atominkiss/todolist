package net.protoprint.todolist.persist.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL,
			//удалять поля у которых отсутствует связь
			orphanRemoval = true
	)
	private List<ToDo> todos;

}
