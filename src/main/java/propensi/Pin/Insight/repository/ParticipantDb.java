package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.ParticipantModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantDb extends JpaRepository<ParticipantModel, Long>{
    List<ParticipantModel> findAll();

    Optional<ParticipantModel> findById(Long id);
}
