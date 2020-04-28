package net.protoprint.todolist.repr;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRepr {

	private Long id;

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	@NotEmpty
	private String matchingPassword;
}
