package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import propensi.Pin.Insight.model.*;
import propensi.Pin.Insight.model.InsightArchetypeModel;
import propensi.Pin.Insight.rest.*;
import propensi.Pin.Insight.rest.BaseResponse;
import propensi.Pin.Insight.rest.InsightDetail;
import propensi.Pin.Insight.rest.InsightDetailCreate;
import propensi.Pin.Insight.rest.InsightUserType;
import propensi.Pin.Insight.service.*;

import javax.validation.constraints.Null;
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

    @Autowired
    KomentarService komentarService;

//    @Autowired
//    TrashBinService trashBinService;

/*    @Autowired
    ArchetypeRestService archetypeRestService;*/

    @Autowired
    UserRestService userRestService;

    @GetMapping("/insight-dashboard/{bulan}")
    private InsightUserType getInsightByUserType(@PathVariable (value = "bulan") Long bulan) {
        return insightRestService.getJumlahInsightByUserType(bulan);
    }
    @GetMapping("/insight-dashboard/")
    private InsightUserType getInsightPerMonth(){
        return insightRestService.getJumlahInsightPerBulan();
    }

    @GetMapping("/insights")
    private BaseResponse<List> getAllInsight() {
        List<InsightDetail> insightList = insightRestService.getAllInsight();
        return new BaseResponse<>(200, "Insights data retrived", insightList);
    }

    @GetMapping("/insightRisetList")
    private List<RisetDetail> retriveListRiset() {
        List<RisetModel> listRiset = insightRestService.getAllRiset();
        List<RisetDetail> data = new ArrayList<>();
        for (RisetModel riset:
                listRiset) {
            RisetDetail detail = new RisetDetail();
            List<Integer> archtype = new ArrayList<>();
            riset.getListArchetypeModel().forEach(i -> archtype.add(i.getUserType().getId()));
            detail.setArchetype(archtype);
            detail.setId(Math.toIntExact(riset.getId()));
            detail.setResearchTitle(riset.getResearchTitle());
            data.add(detail);
        }
        return data;
    }

    @GetMapping("/insight/detail/{id}")
    private Object getInsight(@PathVariable(value = "id") Long id) {
        try {
            Optional<InsightModel> insightModel = insightRestService.getInsight(id);
            List<UserTypeModel> list = new ArrayList<>();
            List<KomentarDetail> listKomentar = new ArrayList<>();

            for (int i = 0; i < insightModel.get().getInsightArchetypeModels().size(); i++) {
                list.add(insightModel.get().getInsightArchetypeModels().get(i).getUserType());
            }
            InsightDetail insightDetail = new InsightDetail();
            try{
                insightDetail.setRiset(insightModel.get().getRisetInsight().getResearchTitle());

            } catch (NullPointerException e){
                System.out.println("catch");
            }
            insightDetail.setInputDate(insightModel.get().getInputDate());
            insightDetail.setInsightStatement(insightModel.get().getInsightStatement());
            insightDetail.setInsightPicName(insightModel.get().getInsightPicName());
            insightDetail.setListArchetype(list);
            insightDetail.setUsername(insightModel.get().getUser().getUsername());
            System.out.println(insightModel.get().getUser().getUsername());
            insightDetail.setInsightTeamName(insightModel.get().getInsightTeamName());
            insightDetail.setStatus(insightModel.get().getStatus());
            List<KomentarModel> insightKomentar = insightModel.get().getInsightCommentModels();
            for (KomentarModel komentar: insightKomentar
            ) {
                KomentarDetail komentarObject = new KomentarDetail();
                komentarObject.setKomentar(komentar.getKomentar());
                komentarObject.setId(komentar.getId());
                komentarObject.setUsername(komentar.getUserKomentar().getUsername());
                komentarObject.setName(komentar.getUserKomentar().getNama());
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

    @GetMapping("/insight/update/{id}")
    private Object getDataUpdate(@PathVariable(value = "id") Long id) {
        try {
            InsightModel insightModel = insightRestService.getInsight(id).get();
            List<Integer> listOfArchtype = new ArrayList<>();
            InsightDetailCreate dbData = new InsightDetailCreate();
//            dbData.setAll(insightModel.getInsightPicName(),insightModel.getInsightTeamName(), insightModel.getNote(), insightModel.getInsightStatement(), Math.toIntExact(insightModel.getRisetInsight().getId()), insightModel.getStatus(), insightModel.getInsightArchetypeModels().get());
            dbData.setInsightPicName(insightModel.getInsightPicName());
            dbData.setInsightTeamName(insightModel.getInsightTeamName());
            try{
                dbData.setIdRiset(Math.toIntExact(insightModel.getRisetInsight().getId()));
            } catch (NullPointerException e){
                System.out.println("expected");
            }
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
            try{
                RisetModel riset = risetService.getRisetById(Long.valueOf(idRiset)).get();
                insightModel.setRisetInsight(riset);
            } catch (NullPointerException e){
                System.out.println("Expected");
            }
//            UserTypeModel archetypeServiceById = archetypeService.findById(archetype).get();
            UserModel user = userRestService.getUser(Long.valueOf(idUser)).get();
            insightModel.setInputDate(timestamp);
            insightModel.setInsightPicName(pic);
            insightModel.setInsightTeamName(team);
            insightModel.setInsightStatement(insightStatement);
            insightModel.setStatus(status);

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
        System.out.println(postData.toString());
        for (String insightValue:
                postData.getInsightList()) {
            InsightModel insightModel = new InsightModel();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String pic = postData.getInsightPicName();
            String team = postData.getInsightTeamName();
            Integer idRiset = postData.getIdRiset();
            Integer idUser = postData.getIdUser();
            Boolean status = postData.getStatus();

            System.out.println(postData.toString());
            try{
                RisetModel riset = risetService.getRisetById(Long.valueOf(idRiset)).get();
                insightModel.setRisetInsight(riset);
                // this for add insight amount of riset
                int tambahJumlahInsight = riset.getInsight_amount()+1;
                riset.setInsight_amount(tambahJumlahInsight);
            }catch (NullPointerException e){
                System.out.println("this is empty");;
            }

            UserModel user = userRestService.getUser(Long.valueOf(idUser)).get();

            insightModel.setInputDate(timestamp);
            insightModel.setInsightPicName(pic);
            insightModel.setInsightTeamName(team);
            insightModel.setInsightStatement(insightValue);
            insightModel.setStatus(status);

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
        }
        return new BaseResponse<>(200, "Data has been updated", null);
    }

    @GetMapping("/komentars")
    private Object getAllKomentar(){
        List<KomentarModel> allKomentar = komentarService.getAllKomentar();
        return new BaseResponse<>(200, "Retrieved all komentar successful", allKomentar);
    }

    @PostMapping(value = "/komentar/create")
    private Object createKomentar(@RequestBody KomentarDetailCreate postData) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        UserModel currentUser = userRestService.getUser(Long.valueOf(postData.getUserId())).get();
        InsightModel currentInsight = insightRestService.getInsight(Long.valueOf(postData.getInsightId())).get();

        KomentarModel newKomentar = new KomentarModel();
        newKomentar.setKomentar(postData.getKomentar());
        newKomentar.setUserKomentar(currentUser);
        newKomentar.setInsightModel(currentInsight);
        newKomentar.setInputDate(timestamp);

        KomentarModel saveData = komentarService.createKomentar(newKomentar);
        return new BaseResponse<>(200, "Komentar Created", saveData);
    }

    @PostMapping("/komentar/delete/{id}")
    private Object deleteKomentar(@PathVariable(value = "id") Long id){
        try{
            komentarService.deleteKomentar(id);
            return new BaseResponse<>(200, "Komentar deleted succesfully", null);
        } catch (NoSuchElementException e) {
            return new BaseResponse<>(500, "Internal server error, failed updating entry", null);
        }
    }
}