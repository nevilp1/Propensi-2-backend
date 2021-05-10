package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import propensi.Pin.Insight.model.UserTypeModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchetypeDb extends JpaRepository<UserTypeModel,Integer> {
    Optional<UserTypeModel> findById(Integer id);
    List<UserTypeModel> findAll();
}
