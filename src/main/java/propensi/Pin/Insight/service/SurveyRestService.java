package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.SurveyModel;

import java.util.List;

public interface SurveyRestService {
    List<SurveyModel> retrieveListSurvey();

    SurveyModel createSurvey(SurveyModel survey);

    SurveyModel getSurveyBySurveyId(Long surveyId);

    SurveyModel updateSurvey(Long surveyId, SurveyModel surveyUpdated);

    void deleteSurvey(Long surveyId);

}