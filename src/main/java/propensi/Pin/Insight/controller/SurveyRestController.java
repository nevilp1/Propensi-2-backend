package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import propensi.Pin.Insight.model.SurveyModel;
import propensi.Pin.Insight.service.SurveyRestService;


import javax.validation.Valid;
import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class SurveyRestController {
    @Autowired
    SurveyRestService surveyRestService;

    @GetMapping("/surveys")
    private List<SurveyModel> retrieveListSurvey(){
        return surveyRestService.retrieveListSurvey();
    }

    @PostMapping(value="/survey/add")
    private SurveyModel createSurvey(@Valid @RequestBody SurveyModel survey, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }else{
            return surveyRestService.createSurvey(survey);
        }
    }

    @GetMapping(value="/survey/{surveyId}")
    private SurveyModel retrieveSurvey(@PathVariable("surveyId") Long surveyId){
        try{
            return surveyRestService.getSurveyBySurveyId(surveyId);
        }catch (NoSuchElementException e){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Survey ID"+String.valueOf(surveyId)+" Not Found"
            );
        }
    }

    @PutMapping(value="survey/update/{surveyId}")
    private SurveyModel updateSurvey(
            @PathVariable (value = "surveyId") Long surveyId,
            @RequestBody SurveyModel survey,
            BindingResult bindingResult
    ) {
//        try {
//            return surveyRestService.updateSurvey(surveyId,survey);
//        } catch (NoSuchElementException e){
//            throw  new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "Survey with ID " + String.valueOf(surveyId) + " Not Found"
//            );
//        }
        if(bindingResult.hasFieldErrors()){
            bindingResult
                    .getFieldErrors()
                    .stream()
                    .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }else{
            try {
                return surveyRestService.updateSurvey(surveyId,survey);
            } catch (NoSuchElementException e) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Survey with ID " + String.valueOf(surveyId) + " Not Found"
                );
            }
        }
    }

    @DeleteMapping(value="/survey/delete/{surveyId}")
    private ResponseEntity<String> deleteSurvey(@PathVariable("surveyId") Long surveyId){
        try{
            surveyRestService.deleteSurvey(surveyId);
            return ResponseEntity.ok("Survey has been deleted");
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Survey with ID "+ String.valueOf(surveyId)+" Not Found!"
            );
        }
    }
}
