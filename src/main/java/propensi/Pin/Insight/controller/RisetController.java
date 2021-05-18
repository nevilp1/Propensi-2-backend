package propensi.Pin.Insight.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import propensi.Pin.Insight.model.ListArchetypeModel;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.model.UserModel;
import propensi.Pin.Insight.model.UserTypeModel;
import propensi.Pin.Insight.rest.ArchiveDetail;
import propensi.Pin.Insight.rest.BaseResponseRiset;
import propensi.Pin.Insight.rest.RisetDetail;
import propensi.Pin.Insight.rest.UpdateRiset;
import propensi.Pin.Insight.service.ArchetypeService;
import propensi.Pin.Insight.service.ListArchetypeService;
import propensi.Pin.Insight.service.RisetService;
import propensi.Pin.Insight.service.UserService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
@Controller
public class RisetController {

//    @Qualifier ("risetServiceImpl")
    @Autowired
    private RisetService risetService;

    @Autowired
    private ArchetypeService archetypeService;

    //    @Qualifier ("risetServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private ListArchetypeService listArchetypeService;

    @GetMapping("/totalRiset")
    private Long totalRiset() {
        System.out.println(risetService.count());
        return risetService.count();
    }

    @GetMapping("/listRiset")
    private List<Map<String,Object>> retriveListRiset() {
        return risetService.listRiset();
    }

    @PostMapping(value = "/addRiset")
    private BaseResponseRiset<RisetModel> addRiset(@Valid @RequestBody RisetDetail riset, BindingResult bindingResult){
        BaseResponseRiset<RisetModel> response = new BaseResponseRiset<RisetModel>();
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }else{
            RisetModel add = new RisetModel();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            UserModel userTarget = userService.getUserModelByUsername(riset.getCurrentUser());
//            UserModel userTarget = userService.getUserModelById(Long.valueOf(1));
            add.setResearchTitle(riset.getResearchTitle());
            add.setResearchDate(riset.getResearchDate());
            add.setResearchLink(riset.getResearchLink());
            add.setResearchType(riset.getResearchType());
            add.setProjectName(riset.getProjectName());
            add.setInsight_amount(0);
            add.setInputDate(timestamp);
            add.setStatus(true);
            add.setUserRiset(userTarget);
            add.setTeam(riset.getTeam());
            add.setPic(riset.getPic());
            risetService.addRiset(add);

            List<Integer> archetypeID = riset.getArchetype();
            List<UserTypeModel> targetListArchetype = new ArrayList<>();
            RisetModel targetRiset = risetService.getRisetById(add.getId()).get();

            for (int i = 0; i < archetypeID.size(); i++) {
                UserTypeModel target = archetypeService.findById(archetypeID.get(i)).get();
                targetListArchetype.add(target);
                ListArchetypeModel addUserType = new ListArchetypeModel();
                addUserType.setRiset(targetRiset);
                addUserType.setUserType(targetListArchetype.get(i));
                listArchetypeService.addListArchetype(addUserType);
            }
            response.setMessage("success");
        }
        return response;
    }

    @GetMapping(value = "/riset/{id}")
    private HashMap<String,Object> retrieveRiset (@PathVariable (value = "id") Long id){
        try {
            return risetService.getRisetByIdRiset(id);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Riset with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }

    @PutMapping(value = "/riset/{id}/update")
    private BaseResponseRiset<RisetModel> updateRiset(@Valid @PathVariable (value = "id") Long id
            ,@RequestBody UpdateRiset riset
            ,BindingResult bindingResult){
        BaseResponseRiset<RisetModel> response = new BaseResponseRiset<RisetModel>();
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }else{
            RisetModel targetRiset = risetService.getRisetById(id).get();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            UserModel userTarget = userService.getUserModelByUsername(riset.getCurrentUser());

            targetRiset.setResearchTitle(riset.getResearchTitle());
            targetRiset.setResearchDate(riset.getResearchDate());
            targetRiset.setResearchLink(riset.getResearchLink());
            targetRiset.setResearchType(riset.getResearchType());
            targetRiset.setProjectName(riset.getProjectName());
//            targetRiset.setInsight_amount(0);
            targetRiset.setInputDate(timestamp);
//            targetRiset.setStatus(true);
            targetRiset.setUserRiset(userTarget);
            targetRiset.setTeam(riset.getTeam());
            targetRiset.setPic(riset.getPic());
            risetService.updateRiset(targetRiset);

            List<UserTypeModel> targetListArchetype = riset.getArchetype();

            List<ListArchetypeModel> getId = targetRiset.getListArchetypeModel();
            listArchetypeService.remove(getId);

            for (int i = 0; i < targetListArchetype.size(); i++) {
                ListArchetypeModel addUserType = new ListArchetypeModel();
                addUserType.setRiset(targetRiset);
                addUserType.setUserType(targetListArchetype.get(i));
                listArchetypeService.addListArchetype(addUserType);
            }
            response.setMessage("success");
        }
        return response;
    }
    @PutMapping(value = "/riset/archive/{id}")
    private ResponseEntity<String> archiveResearch(@PathVariable("id") Long id, @RequestBody ArchiveDetail riset){
        try{
            RisetModel target = risetService.getRisetById(id).get();
            Boolean archive = riset.getStatus();
            target.setStatus(archive);
            System.out.println(archive);
            risetService.addRiset(target);

            return ResponseEntity.ok("Research has been archived");
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Riset with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }
}
