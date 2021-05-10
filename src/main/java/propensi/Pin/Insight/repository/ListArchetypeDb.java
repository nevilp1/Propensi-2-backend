package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.ListArchetypeModel;

import java.util.Optional;

@Repository
public interface ListArchetypeDb extends JpaRepository<ListArchetypeModel, Long> {
//    List<ListArchetypeModel> findByIdRiset(RisetModel riset);
    Optional<ListArchetypeModel> findById(Long id);

}
