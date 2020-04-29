package net.protoprint.todolist.persist.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "todos")
public class ToDo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	private LocalDate targetDate;

}
