package propensi.Pin.Insight.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.RisetModel;


import java.util.List;
import java.util.Optional;

@Repository
public interface RisetDb extends JpaRepository<RisetModel, Long> {
    Optional<RisetModel> findById(Long id);
    List<RisetModel> findAll();
    @Query(value = "SELECT team, count(*) "+  "FROM riset group by id, team", nativeQuery = true)
    List<RisetTeam> findTeam();
}
