package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import propensi.Pin.Insight.model.UserModel;
import propensi.Pin.Insight.repository.UserDb;
import propensi.Pin.Insight.service.UserRestService;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    UserRestService userRestService;

    @Autowired

    @GetMapping("/users")
    private List<Map<String, Object>> retrieveListUser() {return userRestService.listUser();}

    @GetMapping(value = "/user/{id}")
    private HashMap<String, Object> retrieveUser(@PathVariable(value = "id") Long id) {
        try {
            return userRestService.getUserById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }

    @DeleteMapping(value = "/user/{id}/delete")
    private ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        try {
            userRestService.deleteUser(id);
            return ResponseEntity.ok("User with ID " + String.valueOf(id) + "has been deleted!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }
}
