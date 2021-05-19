package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.rest.ArchiveDetail;
import propensi.Pin.Insight.service.TrashBinRestService;

import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class TrashBinRestController {

    @Autowired
    TrashBinRestService trashBinRestService;

    @GetMapping("/trashBin/riset")
    private List<Map<String, Object>> retrieveTrashBinRiset() {return trashBinRestService.listRiset();}

    @GetMapping(value = "/trashBin/riset/{id}")
    private HashMap<String,Object> retrieveRiset (@PathVariable (value = "id") Long id){
        try {
            return trashBinRestService.getRisetByIdRiset(id);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Riset with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }

    @PutMapping(value = "/trashBin/riset/{id}/active")
    private ResponseEntity<String> activeResearch(@PathVariable("id") Long id, @RequestBody ArchiveDetail riset){
        try{
            RisetModel target = trashBinRestService.getRisetById(id).get();
            Boolean active = true;
            target.setStatus(active);
            System.out.println(active);
            trashBinRestService.addRiset(target);

            return ResponseEntity.ok("Research has been activated");
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Riset with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }

}
