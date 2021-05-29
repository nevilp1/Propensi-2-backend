package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.InsightModel;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface InsightDb extends JpaRepository<InsightModel, Long>{
    List<InsightModel> findAllByStatusIsTrue();
    List<InsightModel> findAllByStatusIsFalse();
}
