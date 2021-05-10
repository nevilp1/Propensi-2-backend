package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import propensi.Pin.Insight.model.*;
import propensi.Pin.Insight.model.InsightArchetypeModel;
import propensi.Pin.Insight.rest.BaseResponse;
import propensi.Pin.Insight.rest.InsightDetail;
import propensi.Pin.Insight.rest.InsightDetailCreate;
import propensi.Pin.Insight.service.*;

import java.sql.Timestamp;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class InsightRestController {

    @Autowired
    InsightRestService insightRestService;

    @Autowired
    RisetService risetService;

    @Autowired
    ArchetypeService archetypeService;

    @Autowired
    InsightArchetypeService insightArchetypeService;

//    @Autowired
//    TrashBinService trashBinService;

/*    @Autowired
    ArchetypeRestService archetypeRestService;*/

    @Autowired
    UserRestService userRestService;

    @GetMapping("/insights")
    private BaseResponse<List> getAllInsight() {
        List<InsightDetail> insightList = insightRestService.getAllInsight();
        return new BaseResponse<>(200, "Insights data retrived", insightList);
    }

    @GetMapping("/insightRisetList")
    private List<Map<String, Object>> retriveListRiset() {
        return risetService.insightRisetList();
    }

    @GetMapping("/insight/detail/{id}")
    private Object getInsight(@PathVariable(value = "id") Long id) {
        try {
            Optional<InsightModel> insightModel = insightRestService.getInsight(id);
            List<UserTypeModel> list = new ArrayList<>();

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
            return new BaseResponse<>(200, "Insight Data Retrieved", insightDetail);
        } catch (NoSuchElementException e) {
            return new BaseResponse<>(404, "Not found", null);
        }
    }

    @GetMapping("/insight/update/{id}")
    private Object getDataUpdate(@PathVariable(value = "id") Long id) {
        try {
            InsightModel insightModel = insightRestService.getInsight(id).get();
            List<Integer> listOfArchtype = new ArrayList<>();
            InsightDetailCreate dbData = new InsightDetailCreate();
//            dbData.setAll(insightModel.getInsightPicName(),insightModel.getInsightTeamName(), insightModel.getNote(), insightModel.getInsightStatement(), Math.toIntExact(insightModel.getRisetInsight().getId()), insightModel.getStatus(), insightModel.getInsightArchetypeModels().get());
            dbData.setInsightPicName(insightModel.getInsightPicName());
            dbData.setInsightTeamName(insightModel.getInsightTeamName());
            dbData.setIdRiset(Math.toIntExact(insightModel.getRisetInsight().getId()));
            dbData.setNote(insightModel.getNote());
            dbData.setInsightStatement(insightModel.getInsightStatement());

            for (InsightArchetypeModel i :
                    insightModel.getInsightArchetypeModels()) {
                listOfArchtype.add(i.getUserType().getId());
            }
            dbData.setUserTypeModels(listOfArchtype);

            return new BaseResponse<>(200, "Data Retrieved", dbData);
        } catch (NoSuchElementException e) {
            return new BaseResponse<>(404, "Not found", null);
        }
    }

    @PostMapping("/insight/delete/{id}")
    private Object archiveInsight(@PathVariable(value = "id") Long id) {
        try {
            Optional<InsightModel> insightModel = insightRestService.getInsight(id);
            insightModel.get().setStatus(false);
            insightRestService.archiveInsight(insightModel.get());
            return new BaseResponse<>(200, "Data has been archived", null);
        } catch (NoSuchElementException e) {
            return new BaseResponse<>(500, "Internal Server error", null);
        }
    }

    @PostMapping("/insight/update/{id}/submit")
    private Object updateInsight(@PathVariable(value = "id") Long id, @RequestBody InsightDetailCreate postData) {
        try {
            InsightModel insightModel = insightRestService.getInsight(id).get();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String pic = postData.getInsightPicName();
            String team = postData.getInsightTeamName();
            String note = postData.getNote();
            String insightStatement = postData.getInsightStatement();
            Integer idRiset = postData.getIdRiset();
            Integer idUser = postData.getIdUser();
            Boolean status = postData.getStatus();
            List<Integer> archetype = postData.getArchetype();

            RisetModel riset = risetService.getRisetById(Long.valueOf(idRiset)).get();
//            UserTypeModel archetypeServiceById = archetypeService.findById(archetype).get();
            UserModel user = userRestService.getUser(Long.valueOf(idUser)).get();
            insightModel.setInputDate(timestamp);
            insightModel.setInsightPicName(pic);
            insightModel.setInsightTeamName(team);
            insightModel.setNote(note);
            insightModel.setInsightStatement(insightStatement);
            insightModel.setStatus(status);
            insightModel.setRisetInsight(riset);
            insightModel.setUser(user);
            insightRestService.updateInsight(insightModel);


            List<InsightArchetypeModel> getID = insightModel.getInsightArchetypeModels();
            insightArchetypeService.remove(getID);

            for (Integer i :
                    archetype) {
                UserTypeModel userTypeArchtype = archetypeService.findById(i).get();
                InsightArchetypeModel insightArchetypeModel = new InsightArchetypeModel();
                insightArchetypeModel.setInsightModel(insightModel);
                insightArchetypeModel.setUserType(userTypeArchtype);
                insightArchetypeService.addListArchetype(insightArchetypeModel);
            }

            return new BaseResponse<>(200, "Data has been updated", insightModel);
        } catch (NoSuchElementException e) {
            return new BaseResponse<>(500, "Internal server error, failed updating entry", null);
        }
    }

    @PostMapping(value = "/insight/create")
    private Object createInsight(@RequestBody InsightDetailCreate postData) {
        InsightModel insightModel = new InsightModel();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String pic = postData.getInsightPicName();
        String team = postData.getInsightTeamName();
        String note = postData.getNote();
        String insightStatement = postData.getInsightStatement();
        Integer idRiset = postData.getIdRiset();
        Integer idUser = postData.getIdUser();
        Boolean status = postData.getStatus();

        System.out.println(postData.toString());
        System.out.println(idRiset);
        System.out.println(status);

        RisetModel riset = risetService.getRisetById(Long.valueOf(idRiset)).get();
        UserModel user = userRestService.getUser(Long.valueOf(idUser)).get();

        insightModel.setInputDate(timestamp);
        insightModel.setInsightPicName(pic);
        insightModel.setInsightTeamName(team);
        insightModel.setNote(note);
        insightModel.setInsightStatement(insightStatement);
        insightModel.setStatus(status);
        insightModel.setRisetInsight(riset);
        insightModel.setUser(user);
        InsightModel savedData = insightRestService.createInsight(insightModel);

        // Add multiple archtype to the database
        // use for loop to save each archtype id to insightArchtype that save the records of multiple archtype
        for (Integer i :
                postData.getArchetype()) {
            UserTypeModel userTypeArchtype = archetypeService.findById(i).get();
            InsightArchetypeModel insightArchetypeModel = new InsightArchetypeModel();
            insightArchetypeModel.setInsightModel(savedData);
            insightArchetypeModel.setUserType(userTypeArchtype);
            insightArchetypeService.addListArchetype(insightArchetypeModel);
        }
        return new BaseResponse<>(200, "Data has been updated", insightModel);
    }
}
