package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.ParticipantModel;
import propensi.Pin.Insight.rest.ParticipantDetail;

import java.util.List;

public interface ParticipantRestService {
    List<ParticipantModel> retrieveListParticipant(Long surveyId);

    ParticipantModel createParticipant(ParticipantDetail participant);

    ParticipantModel getParticipantByParticipantId(Long participantId);

    ParticipantModel updateParticipant(Long participantId, ParticipantModel participantUpdated);
}
