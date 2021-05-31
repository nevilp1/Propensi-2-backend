package propensi.Pin.Insight.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import propensi.Pin.Insight.model.*;
import propensi.Pin.Insight.rest.ArchiveDetail;
import propensi.Pin.Insight.rest.BaseResponse;
import propensi.Pin.Insight.rest.InsightDetail;
import propensi.Pin.Insight.rest.KomentarDetail;
import propensi.Pin.Insight.service.InsightRestService;
import propensi.Pin.Insight.service.RisetService;
import propensi.Pin.Insight.service.TrashBinRestService;
import propensi.Pin.Insight.service.UserRestService;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class TrashBinRestController {

    @Autowired
    TrashBinRestService trashBinRestService;

    @Autowired
    UserRestService userRestService;

    @Autowired
    RisetService risetService;

    @Autowired
    InsightRestService insightRestService;

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

    @GetMapping("/insight/risetID/trashBin/{id}")
    private List<Map<String,Object>> retriveListInsightByIDRiset(@PathVariable (value = "id") Long id){
        try {
            return risetService.listInsightByIDRiset(id, false);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Insight with ID Riset " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }

    @PutMapping(value = "/trashBin/riset/{id}/active")
    private ResponseEntity<String> activeResearch(@PathVariable("id") Long id, @RequestBody ArchiveDetail riset){
        try{
            RisetModel target = trashBinRestService.getRisetById(id).get();
            Boolean active = riset.getStatus();
            target.setStatus(active);
            System.out.println(active);
            List<InsightModel> listInsight = target.getInsightModels();
            for (int i = 0; i < listInsight.size() ; i++) {
                InsightModel targetInsight = listInsight.get(i);
                targetInsight.setStatus(active);
                insightRestService.archiveInsight(targetInsight);
            }
            trashBinRestService.addRiset(target);

            return ResponseEntity.ok("Research has been activated");
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Riset with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }

//    @GetMapping("/trashBin/insight1")
//    private List<Map<String, Object>> retrieveTrashBinInsight() {return trashBinRestService.listInsight();}

    @GetMapping("/trashBin/insight")
    private BaseResponse<List> getAllInsight() {
        List<InsightDetail> insightList = trashBinRestService.getAllInsight();
        return new BaseResponse<>(200, "Insights data retrived", insightList);
    }

//    @GetMapping(value = "/trashBin/insight/{id}")
//    private HashMap<String,Object> retrieveInsight (@PathVariable (value = "id") Long id){
//        try {
//            return trashBinRestService.getInsightByIdInsight(id);
//        }catch (NoSuchElementException e){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "Insight with ID " + String.valueOf(id) + " doesn't exist!"
//            );
//        }
//    }

    @GetMapping(value = "/trashBin/insight/{id}")
    private Object getInsight(@PathVariable(value = "id") Long id) {
        try {
            Optional<InsightModel> insightModel = trashBinRestService.getInsight(id);
            List<UserTypeModel> list = new ArrayList<>();
            List<KomentarDetail> listKomentar = new ArrayList<>();

            for (int i = 0; i < insightModel.get().getInsightArchetypeModels().size(); i++) {
                list.add(insightModel.get().getInsightArchetypeModels().get(i).getUserType());
            }
            InsightDetail insightDetail = new InsightDetail();
            insightDetail.setRiset(insightModel.get().getRisetInsight().getResearchTitle());
            insightDetail.setInputDate(insightModel.get().getInputDate());
            insightDetail.setInsightStatement(insightModel.get().getInsightStatement());
            insightDetail.setInsightPicName(insightModel.get().getInsightPicName());
            insightDetail.setListArchetype(list);
//            insightDetail.setArchetype(insightModel.get().getTypeInsight().getType());
            insightDetail.setInsightTeamName(insightModel.get().getInsightTeamName());
//            insightDetail.setRiset(insightModel.get().getRiset().getResearchTitle());
            insightDetail.setStatus(insightModel.get().getStatus());
            List<KomentarModel> insightKomentar = insightModel.get().getInsightCommentModels();
            for (KomentarModel komentar: insightKomentar
            ) {
                KomentarDetail komentarObject = new KomentarDetail();
                komentarObject.setKomentar(komentar.getKomentar());
                komentarObject.setId(komentar.getId());
                komentarObject.setUsername(komentar.getUserKomentar().getUsername());
                komentarObject.setInsightId(Math.toIntExact(komentar.getInsightModel().getId()));
                komentarObject.setInputDate(komentar.getInputDate());
                listKomentar.add(komentarObject);
            }
            insightDetail.setListKomentar(listKomentar);

            return new BaseResponse<>(200, "Insight Data Retrieved", insightDetail);
        } catch (NoSuchElementException e) {
            return new BaseResponse<>(404, "Not found", null);
        }
    }

    @PutMapping(value = "/trashBin/insight/{id}/active")
    private Object activeInsight(@PathVariable(value = "id") Long id) {
        try {
            Optional<InsightModel> insightModel = trashBinRestService.getInsight(id);
            insightModel.get().setStatus(true);
            trashBinRestService.activeInsight(insightModel.get());
            return new BaseResponse<>(200, "Insight has been activated", null);
        } catch (NoSuchElementException e) {
            return new BaseResponse<>(500, "Internal Server error", null);
        }
    }

    @GetMapping("/trashBin/user")
    private List<Map<String, Object>> retrieveTrashBinUser() {return trashBinRestService.listUser();}

    @GetMapping("/trashBin/user/{id}")
    private HashMap<String,Object> retrieveUser(@PathVariable(value = "id") Long id) {
        try {
            return userRestService.getUserById(id);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }

    @PutMapping(value = "/trashBin/user/active/{id}")
    private ResponseEntity<String> archiveResearch(@PathVariable("id") Long id, @RequestBody ArchiveDetail activeStatus){
        try{
            UserModel target = userRestService.getUser(id).get();
            target.setStatus(activeStatus.getStatus());
            userRestService.archiveUser(target);

            return ResponseEntity.ok("User has been active");
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }
}
