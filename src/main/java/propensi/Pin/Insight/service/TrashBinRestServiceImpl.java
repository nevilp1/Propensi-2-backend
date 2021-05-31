package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.*;
import propensi.Pin.Insight.repository.InsightDb;
import propensi.Pin.Insight.repository.RisetDb;
import propensi.Pin.Insight.repository.UserDb;
import propensi.Pin.Insight.rest.InsightDetail;

import javax.transaction.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class TrashBinRestServiceImpl implements TrashBinRestService {

    @Autowired
    UserDb userDb;

    @Autowired
    RisetDb risetDb;

    @Autowired
    InsightDb insightDb;

    @Override
    public void addRiset(RisetModel add) {
        risetDb.save(add);
    }

    @Override
    public List<Map<String, Object>> listRiset() {
        List<Map<String,Object>> list = new ArrayList<>();
        List<RisetModel> allRiset = risetDb.findAll();
        for (int i = 0; i < allRiset.size(); i++) {
            if(allRiset.get(i).getStatus() == true){
                continue;
            }else {
                Map<String,Object> data = new HashMap<>();
                Date temp = allRiset.get(i).getResearchDate();
                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String stringDate = date.format(temp);
                data.put("research_date",stringDate);
                data.put("title",allRiset.get(i).getResearchTitle());
                data.put("research_type",allRiset.get(i).getResearchType());
                data.put("project_name",allRiset.get(i).getProjectName());
                data.put("insight_amount",allRiset.get(i).getInsight_amount());
                data.put("id",allRiset.get(i).getId());
                String status = "";
                if (allRiset.get(i).getStatus() == false){
                    status = "Archive";
                }
                data.put("status", status);
                list.add(data);
            }
        }
        return list;
    }

    @Override
    public Optional<RisetModel> getRisetById(Long id) {
        return risetDb.findById(id);
    }

    @Override
    public HashMap<String, Object> getRisetByIdRiset(Long id) {
        HashMap<String,Object> target = new HashMap<>();
        Optional<RisetModel> targetRiset = risetDb.findById(id);

        // Untuk Detail Riset
        Date dateTempResearchDate = targetRiset.get().getResearchDate();
        DateFormat date = new SimpleDateFormat("E, dd MMM yyyy");
        String formated = date.format(dateTempResearchDate);

        Date dateTempInput = targetRiset.get().getInputDate();
        DateFormat inputDate = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss ");
        String formatedInput = inputDate.format(dateTempInput);

        List<UserTypeModel> list = new ArrayList<>();
        for (int i = 0; i < targetRiset.get().getListArchetypeModel().size(); i++) {
            list.add(targetRiset.get().getListArchetypeModel().get(i).getUserType());
        }

        HashMap<String,Object> insightTarget = new HashMap<>();
        List<InsightModel> insightID  = targetRiset.get().getInsightModels();

        for (int i = 0; i < insightID.size(); i++) {
            List<UserTypeModel> listArchetypeInsight = new ArrayList<>();
            if (insightID.get(i).getStatus() == false) {
                for (int j = 0; j < insightID.get(i).getInsightArchetypeModels().size(); j++) {
                    listArchetypeInsight.add(insightID.get(i).getInsightArchetypeModels().get(j).getUserType());
                }
                insightTarget.put("id", insightID.get(i).getId());
                insightTarget.put("insight_statement", insightID.get(i).getInsightStatement());
                insightTarget.put("insightArchetype", listArchetypeInsight);
            }
        }

        target.put("research_title", targetRiset.get().getResearchTitle());
        target.put("research_date", formated);
        target.put("research_type", targetRiset.get().getResearchType());
        target.put("archetype", list);
        target.put("project_name", targetRiset.get().getProjectName());
        target.put("team", targetRiset.get().getTeam());
        target.put("pic", targetRiset.get().getPic());
        String status = "";
        if (targetRiset.get().getStatus() == false){
            status = "Archive";
        }
        target.put("status",status);
        target.put("input_date", formatedInput);
        target.put("insight_amount", targetRiset.get().getInsight_amount());
        target.put("research_link", targetRiset.get().getResearchLink());
        target.put("id",targetRiset.get().getId());
        target.put("insightList",insightTarget);
        return target;
    }

    @Override
    public List<InsightDetail> getAllInsight() {
        List<InsightModel> insightDataFromDb = insightDb.findAllByStatusIsFalse();
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
                insight.setRiset(k.getRisetInsight().getResearchTitle());
                insight.setStatus(k.getStatus());
                insightDetails.add(insight);
            }
            return insightDetails;
        }
        return insightDetails;
    }

    @Override
    public Optional<InsightModel> getInsight(Long idInsight) {
        Optional<InsightModel> insightModel = insightDb.findById(idInsight);

        return insightModel;
    }

    @Override
    public InsightModel activeInsight(InsightModel insightModel) {
        return insightDb.save(insightModel);
    }

    @Override
    public List<Map<String, Object>> listUser() {
        List<Map<String, Object>> userList = new ArrayList<>();
        List<UserModel> allUser = userDb.findAll();

        for (int i = 0; i < allUser.size(); i++) {
            if(allUser.get(i).getStatus() == true){
                continue;
            }else {
                Map<String, Object> data = new HashMap<>();
                UserModel target = allUser.get(i);
                String idUser = "ID-" + target.getId().toString();
                String status = "";
                if (target.getStatus() == false){
                    status = "Archive";
                }
                data.put("id", target.getId());
                data.put("idUser", idUser);
                data.put("nama", target.getNama());
                data.put("username", target.getUsername());
                data.put("team", target.getTeam());
                data.put("role", target.getRoles());
                data.put("status", status);
                userList.add(data);
            }
        }
        return userList;
    }
}
