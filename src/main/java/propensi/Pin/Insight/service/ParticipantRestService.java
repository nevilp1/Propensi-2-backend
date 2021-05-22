package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.ParticipantModel;

import java.util.List;

public interface ParticipantRestService {
    List<ParticipantModel> retrieveListParticipant();

    ParticipantModel createParticipant(ParticipantModel participant);

    ParticipantModel getParticipantByParticipantId(Long participantId);

    ParticipantModel updateParticipant(Long participantId, ParticipantModel participantUpdated);
}
