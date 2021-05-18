package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

}
