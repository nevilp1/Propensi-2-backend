package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.RisetModel;


import java.util.List;
import java.util.Optional;

@Repository
public interface RisetDb extends JpaRepository<RisetModel, Long> {
    Optional<RisetModel> findById(Long id);
    List<RisetModel> findAll();
    List<RisetModel> findAllByTeam(String team);
}
