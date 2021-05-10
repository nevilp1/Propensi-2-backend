package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.InsightArchetypeModel;
import propensi.Pin.Insight.model.ListArchetypeModel;

import java.util.Optional;
@Repository
public interface InsightArchetypeDb extends JpaRepository<InsightArchetypeModel, Long> {
    //    List<ListArchetypeModel> findByIdRiset(RisetModel riset);
    Optional<InsightArchetypeModel> findById(Long id);
}
