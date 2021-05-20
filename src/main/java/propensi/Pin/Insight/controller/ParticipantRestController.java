package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import propensi.Pin.Insight.model.ParticipantModel;
import propensi.Pin.Insight.service.ParticipantRestService;

import javax.validation.Valid;
import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ParticipantRestController {
    @Autowired
    ParticipantRestService participantRestService;

    @PostMapping(value="/participant/add")
    private ParticipantModel createParticipant(@Valid @RequestBody ParticipantModel participant, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }else{
            return participantRestService.createParticipant(participant);
        }
    }
}
