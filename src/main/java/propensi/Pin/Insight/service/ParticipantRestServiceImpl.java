package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.ParticipantModel;
import propensi.Pin.Insight.repository.ParticipantDb;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ParticipantRestServiceImpl implements ParticipantRestService{
    @Autowired
    ParticipantDb participantDb;


    @Override
    public List<ParticipantModel> retrieveListParticipant() {
        return null;
    }

    @Override
    public ParticipantModel createParticipant(ParticipantModel participant) {
        return participantDb.save(participant);
    }

    @Override
    public ParticipantModel getParticipantByParticipantId(Long participantId) {
        Optional<ParticipantModel> participant = participantDb.findById(participantId);
        if(participant.isPresent()){
            return participant.get();
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public ParticipantModel updateParticipant(Long participantId, ParticipantModel participantUpdated) {
        ParticipantModel participant = getParticipantByParticipantId(participantId);
        participant.setInputDate(new Date());
        participant.setParticipantStatus(participantUpdated.getParticipantStatus());
        participant.setParticipantNotes(participantUpdated.getParticipantNotes());
        return participantDb.save(participant);
    }
}
