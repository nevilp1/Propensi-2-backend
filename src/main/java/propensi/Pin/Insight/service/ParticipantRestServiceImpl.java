package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.ParticipantModel;
import propensi.Pin.Insight.model.SurveyModel;
import propensi.Pin.Insight.repository.ParticipantDb;
import propensi.Pin.Insight.repository.SurveyDb;
import propensi.Pin.Insight.rest.ParticipantDetail;

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

    @Autowired
    SurveyDb surveyDb;


    @Override
    public List<ParticipantModel> retrieveListParticipant(Long surveyId) {
        List<ParticipantModel> participantList = participantDb.findAll();
        participantList.removeIf(participant -> participant.getSurvey().getId() != surveyId);
        return participantList;
    }

    @Override
    public ParticipantModel createParticipant(ParticipantDetail participant) {
        ParticipantModel temp = new ParticipantModel();
        temp.setInputDate(new Date());
        temp.setParticipantNotes(" ");
        temp.setParticipantStatus("New");
        SurveyModel s = surveyDb.findById(participant.getId()).get();
        temp.setSurvey(s);
        temp.setAge(participant.getAge());
        temp.setParticipantName(participant.getParticipantName());
        temp.setParticipantEmail(participant.getParticipantEmail());
        temp.setDomicile(participant.getDomicile());
        temp.setPhoneNumber(participant.getPhoneNumber());
        return participantDb.save(temp);
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
