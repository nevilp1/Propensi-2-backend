package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.InsightModel;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.model.UserTypeModel;
import propensi.Pin.Insight.model.InsightArchetypeModel;
import propensi.Pin.Insight.repository.InsightDb;
import propensi.Pin.Insight.repository.RisetDb;
import propensi.Pin.Insight.repository.SurveyDb;
import propensi.Pin.Insight.rest.InsightDetail;
import propensi.Pin.Insight.rest.InsightDetailCreate;

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
                insight.setNote(k.getNote());
                insight.setRiset(k.getRisetInsight().getResearchTitle());
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

}