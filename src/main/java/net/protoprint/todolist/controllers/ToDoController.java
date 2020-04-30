package net.protoprint.todolist.controllers;

import net.protoprint.todolist.repr.ToDoRepr;
import net.protoprint.todolist.service.ToDoService;
import net.protoprint.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ToDoController {

	private ToDoService toDoService;
	private UserService userService;

	@Autowired
	public ToDoController(ToDoService toDoService, UserService userService) {
		this.toDoService = toDoService;
		this.userService = userService;
	}

	@GetMapping("/")
	public String indexPage(Model model) {
		List<ToDoRepr> todos = toDoService.fingToDosByUserId(userService.getCurrentUserId().orElseThrow(ResourceNotFoundException::new));
		model.addAttribute("todos", todos);
		return "index";
	}

	@GetMapping("/todo/{id}")
	public String todoPage(@PathVariable("id") Long id, Model model) {
		ToDoRepr toDoRepr = toDoService.findById(id).orElseThrow(ResourceNotFoundException::new);
		model.addAttribute("todo", toDoRepr);
		return "todo";
	}

	@PostMapping("/todo/create")
	public String createTodoPost(@ModelAttribute("todo") ToDoRepr toDoRepr) {
		toDoService.save(toDoRepr);
		return "redirect:/";
	}

	@GetMapping("/todo/delete/{id}")
	public String deleteTodo(Model model, @PathVariable Long id) {
		toDoService.delete(id);
		return "redirect:/";
	}

}
