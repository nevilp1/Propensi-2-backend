package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import propensi.Pin.Insight.model.UserModel;

import javax.transaction.Transactional;
import java.util.List;

import java.util.Optional;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findById(Long id);
    Optional<UserModel> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
