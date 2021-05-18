package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import propensi.Pin.Insight.service.UserRestService;

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
    private ResponseEntity<String> archiveUser(@PathVariable("id") Long id) {
        try {
            userRestService.deleteUser(id);
            return ResponseEntity.ok("User with ID " + String.valueOf(id) + "has been deleted!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }

//    @PutMapping(value = "/user/{id}/archive")
//    private ResponseEntity<String> archiveUser(@PathVariable("id") Long id, @RequestBody ArchiveDetail user){
//        try{
//            UserModel target = userRestService.getUser(id).get();
//            Boolean archive = user.getStatus();
//            target.setStatus(archive);
//            System.out.println(archive);
//            userRestService.addUser(target);
//
//            return ResponseEntity.ok("User has been archived");
//        }catch (NoSuchElementException e) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "User with ID " + String.valueOf(id) + " doesn't exist!"
//            );
//        }
//    }
}
