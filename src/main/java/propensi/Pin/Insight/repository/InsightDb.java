package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.InsightModel;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface InsightDb extends JpaRepository<InsightModel, Long>{
    List<InsightModel> findAllByStatusIsTrue();
    List<InsightModel> findAllByStatusIsFalse();

    @Query(value = "SELECT * FROM insight where extract(YEAR FROM input_date) = :current_year", nativeQuery = true)
    List<InsightModel> findInsightByYear(Long current_year);
}
