package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.InsightArchetypeModel;
import propensi.Pin.Insight.model.ListArchetypeModel;
import propensi.Pin.Insight.model.UserTypeModel;

import java.util.List;
import java.util.Optional;
@Repository
public interface InsightArchetypeDb extends JpaRepository<InsightArchetypeModel, Long> {
    //    List<ListArchetypeModel> findByIdRiset(RisetModel riset);
    Optional<InsightArchetypeModel> findById(Long id);
    List<InsightArchetypeModel> findAllByUserType(UserTypeModel userType);
}
