package net.protoprint.todolist.repr;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

//Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check
//all non-transient fields. Will also generate setters for all non-final fields, as well as a constructor.
@Data
public class ToDoRepr {

	private Long id;

	@NotEmpty
	private String description;

	private String username;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate targetDate;


}
