package com.todolist.handler;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"com.todolist"})
public class DaoErrorHandler {
    @InitBinder
    public void dataBinding(WebDataBinder binder) {
    }

    @ModelAttribute
    public void globalAttributes(Model model) {
        model.addAttribute("technicalError", "Une erreur technique est survenue !");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Void> duplicateKeyError(HttpServletRequest req, Exception ex) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Void> unknownError(HttpServletRequest req, Exception ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
