package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.InsightModel;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.model.UserModel;

import java.util.*;

public interface UserRestService {
    List<Map<String, Object>> listUser();
    Optional<UserModel> getUser(Long id);
//    void addUser(UserModel add);
    UserModel archiveUser(UserModel userModel);
    HashMap<String, Object> getUserById(Long id);
    Set<String> listTeam();
    Set<String> listPIC();

    void deleteUser(Long id);

}
