package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.SurveyModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyDb extends JpaRepository<SurveyModel, Long> {
    List<SurveyModel> findAll();

    Optional<SurveyModel> findById(Long id);
}