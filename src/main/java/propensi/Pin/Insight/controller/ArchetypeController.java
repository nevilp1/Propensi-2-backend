package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import propensi.Pin.Insight.model.UserTypeModel;
import propensi.Pin.Insight.service.ArchetypeService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
@Controller
public class ArchetypeController {
    @Autowired
    private ArchetypeService archetypeService;

    @GetMapping("/type")
    private List<UserTypeModel> retrievelistarchetype (){
        return archetypeService.findAllArchetype();
    }
}
