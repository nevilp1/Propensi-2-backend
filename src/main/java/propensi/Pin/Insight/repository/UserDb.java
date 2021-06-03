package propensi.Pin.Insight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import propensi.Pin.Insight.model.UserModel;
import java.util.List;

import java.util.Optional;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findById(Long id);
//    UserModel findByUsername(String name);
    Optional<UserModel> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<UserModel> findAll();

//    @Query(value = "SELECT u.username FROM user_model u JOIN user_roles r on r.user_id = u.id JOIN role ro on ro.id = r.role_id where role_id = '2' or role_id = '3';", nativeQuery = true)
//    List<PicAutocomplete> findPIC();


}
