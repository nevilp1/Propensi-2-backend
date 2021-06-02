package propensi.Pin.Insight.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.InsightModel;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.model.UserTypeModel;
import propensi.Pin.Insight.model.InsightArchetypeModel;
import propensi.Pin.Insight.repository.*;
import propensi.Pin.Insight.rest.InsightDetail;
import propensi.Pin.Insight.rest.InsightDetailCreate;
import propensi.Pin.Insight.rest.InsightUserType;

import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
public class InsightRestServiceImpl implements InsightRestService {

    @Autowired
    InsightDb insightDb;

    @Autowired
    SurveyDb surveyDb;

    @Autowired
    RisetDb risetDb;

    @Autowired
    ArchetypeDb archetypeDb;

    @Autowired
    InsightArchetypeDb insightArchetypeDb;

    @Autowired
    ListArchetypeDb listArchetypeDb;

    @Override
    public InsightUserType getJumlahInsightPerBulan() {
        List<String> listBulan = new ArrayList<>();
        List<Integer> listJumlah = new ArrayList<>();
        InsightUserType insightUserType = new InsightUserType();

        listBulan.add("January");
        listBulan.add("February");
        listBulan.add("March");
        listBulan.add("April");
        listBulan.add("May");
        listBulan.add("June");
        listBulan.add("July");
        listBulan.add("August");
        listBulan.add("September");
        listBulan.add("October");
        listBulan.add("November");
        listBulan.add("December");

        insightUserType.setUserType(listBulan);
        List<InsightModel> insightModels = insightDb.findAll();

        //initialize
        Integer jan, feb, march, april, may, june, july, august, sep, oct, nov, dec;
        jan = feb = march = april = may = june = july = august = sep = oct = nov= dec = 0;

        for(InsightModel i : insightModels){
            switch(i.getInputDate().getMonth()) {
                case 0:
                    jan++;
                    break;
                case 1:
                    feb++;
                    break;
                case 2:
                    march++;
                    break;
                case 3:
                    april++;
                    break;
                case 4:
                    may++;
                    break;
                case 5:
                    june++;
                    break;
                case 6:
                    july++;
                    break;
                case 7:
                    august++;
                    break;
                case 8:
                    sep++;
                    break;
                case 9:
                    oct++;
                    break;
                case 10:
                    nov++;
                    break;
                case 11:
                    dec++;
                    break;

            }

        }
        listJumlah.add(jan);
        listJumlah.add(feb);
        listJumlah.add(march);
        listJumlah.add(april);
        listJumlah.add(may);
        listJumlah.add(june);
        listJumlah.add(july);
        listJumlah.add(august);
        listJumlah.add(sep);
        listJumlah.add(oct);
        listJumlah.add(nov);
        listJumlah.add(dec);
        insightUserType.setJumlahInsight(listJumlah);

        return insightUserType;
    }

    @Override
    public InsightUserType getJumlahInsightByUserType(Long bulan) {
        InsightUserType temp = new InsightUserType();
        List<UserTypeModel> listArchetype = archetypeDb.findAll();

        HashMap<String, List<InsightModel>> map = new HashMap<>();
        for(UserTypeModel u : listArchetype){
            map.put(u.getTypeName(), new ArrayList<InsightModel>());
        }
        List<InsightModel> insightModels = insightDb.findAll();
        for(InsightModel i : insightModels){
            System.out.println("TEST3" + i.getInputDate().getMonth());
            if((i.getInputDate().getMonth() + 1) != bulan){
//                insightModels.remove(i);
            }else{
                Integer counter = i.getInsightArchetypeModels().size();
                for(int j = 0; j< counter; j++) {
                    System.out.println("TEST1" + i.getInsightPicName());
                    List<InsightModel> list2 = map.get(i.getInsightArchetypeModels().get(j).getUserType().getTypeName());
                    list2.add(i);
                    System.out.println("TEST2" + list2.get(0));
                    map.replace((i.getInsightArchetypeModels().get(j).getUserType().getTypeName()), list2);
                }
            }
        }
        List<String> listNama = new ArrayList<>();
        List<Integer> listJumlah = new ArrayList<>();

        for(UserTypeModel u : listArchetype){
            listNama.add(u.getTypeName());
            listJumlah.add(map.get(u.getTypeName()).size() );
        }

        temp.setUserType(listNama);
        temp.setJumlahInsight(listJumlah);
        return temp;
    }

    @Override
    public List<InsightDetail> getAllInsight() {
        List<InsightModel> insightDataFromDb = insightDb.findAllByStatusIsTrue();
        List<InsightDetail> insightDetails = new LinkedList<>();

        List<InsightArchetypeModel> archetypeModels = new ArrayList<>();


        for (int i = 0; i < insightDataFromDb.size(); i++) {

            List<UserTypeModel> listArchetype = new ArrayList<>();
            InsightModel target = insightDb.findById(insightDataFromDb.get(i).getId()).get();
            for (int j = 0; j < target.getInsightArchetypeModels().size() ; j++) {
                listArchetype.add(target.getInsightArchetypeModels().get(j).getUserType());
            }
            for (InsightModel k : insightDataFromDb) {
                InsightDetail insight = new InsightDetail();
                insight.setId(k.getId());
                insight.setInputDate(k.getInputDate());
                insight.setInsightStatement(k.getInsightStatement());
                insight.setInsightPicName(k.getInsightPicName());
                insight.setListArchetype(listArchetype);
                insight.setInsightTeamName(k.getInsightTeamName());
                try {
                    insight.setRiset(k.getRisetInsight().getResearchTitle());
                } catch (NullPointerException e){
                    System.out.println("null pointer reached");
                }
                insight.setUsername(k.getUser().getUsername());
                insight.setStatus(k.getStatus());
                insightDetails.add(insight);
            }
            return insightDetails;
        }

//        for (int i = 0; i < archetypeModels.size(); i++) {
//            listArchetype.add(archetypeModels.get(i).getUserType());
//        }
        return insightDetails;
    }

    @Override
    public Optional<InsightModel> getInsight(Long idInsight) {
        Optional<InsightModel> insightModel = insightDb.findById(idInsight);

        return insightModel;
    }

    @Override
    public InsightModel createInsight(InsightModel insightModel) {
        return insightDb.save(insightModel);
    }

    @Override
    public InsightModel archiveInsight(InsightModel insightModel) {
        return insightDb.save(insightModel);
    }

    @Override
    public InsightModel updateInsight(InsightModel insightModel) {
        insightDb.save(insightModel);
        return insightModel;
    }

    @Override
    public List<RisetModel> getAllRiset() {
        return risetDb.findAllByStatusIsTrue();
    }
}