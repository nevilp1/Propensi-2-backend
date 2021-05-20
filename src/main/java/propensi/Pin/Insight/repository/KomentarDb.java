package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.InsightModel;
import propensi.Pin.Insight.model.KomentarModel;

@Repository
public interface KomentarDb extends JpaRepository<KomentarModel, Long> {
}
