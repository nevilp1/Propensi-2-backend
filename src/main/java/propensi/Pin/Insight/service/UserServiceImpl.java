package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.UserModel;
import propensi.Pin.Insight.repository.UserDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDb userDb;

    @Override
    public List<UserModel> findAllUser() {
        return userDb.findAll();
    }

    @Override
    public UserModel getUserModelByUsername(String name) {
        return userDb.findByUsername(name).get();
    }

    @Override
    public UserModel getUserModelById(Long id) {
        return userDb.findById(id).get();
    }
}
