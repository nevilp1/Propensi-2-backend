package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import propensi.Pin.Insight.model.InsightModel;
import propensi.Pin.Insight.model.KomentarModel;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.model.UserTypeModel;
import propensi.Pin.Insight.rest.ArchiveDetail;
import propensi.Pin.Insight.rest.BaseResponse;
import propensi.Pin.Insight.rest.InsightDetail;
import propensi.Pin.Insight.rest.KomentarDetail;
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
            insightDetail.setNote(insightModel.get().getNote());
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
            return new BaseResponse<>(200, "Data has been archived", null);
        } catch (NoSuchElementException e) {
            return new BaseResponse<>(500, "Internal Server error", null);
        }
    }

}
