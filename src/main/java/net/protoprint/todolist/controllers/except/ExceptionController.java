package net.protoprint.todolist.controllers.except;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionController {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handleNotFoundErrors(HttpServletRequest req, Exception ex) {
		log.error("Request: {} raised {}", req.getRequestURL(), ex);

		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.setStatus(HttpStatus.NOT_FOUND);
		return modelAndView;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleOtherErrors(HttpServletRequest req, Exception ex) {
		log.error("Request: {} raised {}", req.getRequestURL(), ex);

		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", req.getRequestURL());
		modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return modelAndView;
	}
}
