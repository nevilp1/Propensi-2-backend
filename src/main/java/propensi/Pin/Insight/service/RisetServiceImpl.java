package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.InsightModel;
import propensi.Pin.Insight.model.ListArchetypeModel;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.model.UserTypeModel;
import propensi.Pin.Insight.repository.RisetDb;
import propensi.Pin.Insight.repository.RisetTeam;
import propensi.Pin.Insight.repository.UserDb;
import propensi.Pin.Insight.rest.TeamDetail;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class RisetServiceImpl implements RisetService{

    @Autowired
    RisetDb risetDb;

    @Autowired
    UserDb userDb;

    @Override
    public Long count() {
        return risetDb.count();
    }

    @Override
    public void addRiset(RisetModel add) {
        risetDb.save(add);
    }

    @Override
    public List<RisetModel> getRisetByMonth(Long bulan) {
        return risetDb.findRisetByMonth(bulan);
    }

    @Override
    public TeamDetail listTeam(Long bulan) {
        List<RisetTeam> listTeam = risetDb.findTeam(bulan);
        List<String> listTim = new ArrayList<>();
        List<Long> listJumlah = new ArrayList<>();
        TeamDetail resp = new TeamDetail();

        for(RisetTeam i : listTeam){
            listTim.add(i.getTeam());
            listJumlah.add(i.getCount());
        }

        resp.setJumlahRiset(listJumlah);
        resp.setNamaTim(listTim);

        return resp;
    }

    @Override
    public List<Map<String, Object>> listRiset() {
        List<Map<String,Object>> list = new ArrayList<>();
        List<RisetModel> allRiset = risetDb.findAll();
        for (int i = 0; i < allRiset.size(); i++) {
            if(allRiset.get(i).getStatus() == false){
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
                data.put("status", allRiset.get(i).getStatus());
                list.add(data);
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> insightRisetList() {
        List<Map<String,Object>> list = new ArrayList<>();
        List<RisetModel> allRiset = risetDb.findAll();
        for (int i = 0; i < allRiset.size(); i++) {
            if(allRiset.get(i).getStatus() == false){
                continue;
            }else {
                Map<String,Object> data = new HashMap<>();
                data.put("title",allRiset.get(i).getResearchTitle());
                data.put("id",allRiset.get(i).getId());
                list.add(data);
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> listInsightByIDRiset(Long id, Boolean status) {
        List<Map<String,Object>> list = new ArrayList<>();
        Optional<RisetModel> targetRiset = risetDb.findById(id);

        System.out.println(status);

        List<InsightModel> insightID  = targetRiset.get().getInsightModels();


        for (int i = 0; i < insightID.size(); i++) {
            List<UserTypeModel> listArchetypeInsight = new ArrayList<>();
            HashMap<String,Object> insightTarget = new HashMap<>();
            if (insightID.get(i).getStatus() == status) {
                for (int j = 0; j < insightID.get(i).getInsightArchetypeModels().size(); j++) {
                    listArchetypeInsight.add(insightID.get(i).getInsightArchetypeModels().get(j).getUserType());
                }
                insightTarget.put("id", insightID.get(i).getId());
                insightTarget.put("insight_statement", insightID.get(i).getInsightStatement());
                insightTarget.put("insightArchetype", listArchetypeInsight);
                list.add(insightTarget);
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

        // Untuk Update Riset
        Date dateTempResearchDate2 = targetRiset.get().getResearchDate();
        DateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
        String formated2 = date2.format(dateTempResearchDate2);

        List<UserTypeModel> list = new ArrayList<>();
        for (int i = 0; i < targetRiset.get().getListArchetypeModel().size(); i++) {
            list.add(targetRiset.get().getListArchetypeModel().get(i).getUserType());
        }

        HashMap<String,Object> insightTarget = new HashMap<>();
        List<InsightModel> insightID  = targetRiset.get().getInsightModels();

        for (int i = 0; i < insightID.size(); i++) {
            List<UserTypeModel> listArchetypeInsight = new ArrayList<>();
            if (insightID.get(i).getStatus() == true) {
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
        target.put("input_date", formatedInput);
        target.put("insight_amount", targetRiset.get().getInsight_amount());
        target.put("research_link", targetRiset.get().getResearchLink());
        target.put("research_date_update", formated2);
        target.put("id",targetRiset.get().getId());
        target.put("insightList",insightTarget);
        return target;
    }

    @Override
    public RisetModel updateRiset(RisetModel riset) {
        return risetDb.save(riset);
    }

    @Override
    public void archiveRiset(Long id) {
        RisetModel target = risetDb.findById(id).get();
        target.setStatus(false);
    }


}
