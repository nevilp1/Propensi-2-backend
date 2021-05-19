package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.InsightModel;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.model.UserModel;

import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserRestService {
    List<Map<String, Object>> listUser();
    Optional<UserModel> getUser(Long id);
//    void addUser(UserModel add);
    UserModel archiveUser(UserModel userModel);
    HashMap<String, Object> getUserById(Long id);

    void deleteUser(Long id);

}
