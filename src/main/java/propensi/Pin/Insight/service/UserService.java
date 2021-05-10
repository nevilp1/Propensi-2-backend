package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> findAllUser();
    UserModel getUserModelByUsername(String name);
    UserModel getUserModelById(Long id);
}
