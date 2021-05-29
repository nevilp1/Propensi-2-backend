package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import propensi.Pin.Insight.model.ParticipantModel;
import propensi.Pin.Insight.repository.SurveyDb;
import propensi.Pin.Insight.rest.ParticipantDetail;
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
    private ParticipantModel createParticipant(@Valid @RequestBody ParticipantDetail participant, BindingResult bindingResult){
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

    @GetMapping(value="/participant/list/{surveyId}")
    private List<ParticipantModel> retrieveParticipantList(@PathVariable("surveyId") Long surveyId){
        try{
            return participantRestService.retrieveListParticipant(surveyId);
        }catch (NoSuchElementException e){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Participant"+String.valueOf(surveyId)+" Not Found"
            );
        }
    }

    @GetMapping(value="/participant/{participantId}")
    private ParticipantModel retrieveParticipant(@PathVariable("participantId") Long participantId){
        try{
            return participantRestService.getParticipantByParticipantId(participantId);
        }catch (NoSuchElementException e){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Participant"+String.valueOf(participantId)+" Not Found"
            );
        }
    }

    @PutMapping(value="participant/update/{participantId}")
    private ResponseEntity<String> updateParticipant(
            @PathVariable (value = "participantId") Long participantId,
            @RequestBody ParticipantModel participant
    ) {
        try {
            participantRestService.updateParticipant(participantId, participant);
            return ResponseEntity.ok("Update participant success");
        } catch (NoSuchElementException e){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Participant with ID " + String.valueOf(participantId) + " Not Found"
            );
        }
    }
}
