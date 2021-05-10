package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.Pin.Insight.model.ERole;
import propensi.Pin.Insight.model.RoleModel;

import java.util.Optional;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByNamaRole(ERole name);
}
