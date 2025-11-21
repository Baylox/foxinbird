package fr.dawan.magasin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(SQLException e, Model model) {
        model.addAttribute("errorType", "Erreur SQL");
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException e, Model model) {
        model.addAttribute("errorType", "Erreur I/O");
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(ResponseStatusException.class)
    public String handleResponseStatusException(ResponseStatusException e, Model model) {
        model.addAttribute("errorType", "Erreur " + e.getStatusCode().value());
        model.addAttribute("errorMessage", e.getReason());
        if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
            return "error/403";
        }
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception e, Model model) {
        model.addAttribute("errorType", "Erreur");
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}
