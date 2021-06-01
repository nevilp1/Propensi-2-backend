package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.SurveyModel;
import propensi.Pin.Insight.model.ParticipantModel;
import propensi.Pin.Insight.repository.ParticipantDb;
import propensi.Pin.Insight.repository.SurveyDb;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class SurveyRestServiceImpl implements SurveyRestService{
    @Autowired
    SurveyDb surveyDb;

    @Autowired
    ParticipantDb participantDb;

    @Override
    public List<SurveyModel> retrieveListSurvey() {
        return surveyDb.findAll();
    }

    @Override
    public SurveyModel createSurvey(SurveyModel survey){
//        survey.setStatus(true);
//        survey.setInputDate(new Date());
        return surveyDb.save(survey);
    }

    @Override
    public SurveyModel getSurveyBySurveyId(Long surveyId){
        Optional<SurveyModel> survey = surveyDb.findById(surveyId);
        if(survey.isPresent()){
            return survey.get();
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public SurveyModel updateSurvey(Long surveyId, SurveyModel surveyUpdated) {
        SurveyModel survey = getSurveyBySurveyId(surveyId);
        try {
            survey.setInputDate(new Date());
            survey.setResearchTitle(surveyUpdated.getResearchTitle());
            survey.setResearchType(surveyUpdated.getResearchType());
            survey.setPicName(surveyUpdated.getPicName());
            survey.setTeam(surveyUpdated.getTeam());
//            survey.setLinkToForm(surveyUpdated.getLinkToForm());
//            survey.setLinkToParticipant(surveyUpdated.getLinkToParticipant());
            survey.setContent(surveyUpdated.getContent());
            survey.setCriteria(surveyUpdated.getCriteria());
            surveyDb.save(survey);
            return survey;
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public void deleteSurvey(Long surveyId) {
        SurveyModel survey = getSurveyBySurveyId(surveyId);
        List<ParticipantModel> participantList = participantDb.findAll();
        for (ParticipantModel participant : participantList){
            if (participant.getSurvey().getId() == surveyId){
                participantDb.delete(participant);
            }
        }
        surveyDb.delete(survey);
    }


}