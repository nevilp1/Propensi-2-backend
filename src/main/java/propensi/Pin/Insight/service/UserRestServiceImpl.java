package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.ERole;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.model.RoleModel;
import propensi.Pin.Insight.model.UserModel;
import propensi.Pin.Insight.repository.UserDb;

import javax.transaction.Transactional;

import java.util.*;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{
  
    @Autowired
    UserDb userDb;

    @Override
    public void addUser(UserModel add) {
        userDb.save(add);
    }

    @Override
    public Optional<UserModel> getUser(Long id) {
        return userDb.findById(id);
    }
  
    public List<Map<String, Object>> listUser() {
        List<Map<String, Object>> userList = new ArrayList<>();
        List<UserModel> allUser = userDb.findAll();

        for (int i = 0; i < allUser.size(); i++) {
            Map<String, Object> data = new HashMap<>();
            UserModel target = allUser.get(i);
            String idUser = "ID-" + target.getId().toString();
            data.put("id", target.getId());
            data.put("idUser", idUser);
            data.put("nama", target.getNama());
            data.put("username", target.getUsername());
            data.put("team", target.getTeam());
            data.put("role", target.getRoles());
            String userRole = target.getRoles().toString();
            Set<RoleModel> x = target.getRoles();
//            String[] y = x.toArray(new String[0]);
//            data.put("role", y);
//            System.out.println(y);
            userList.add(data);
        }
        return userList;
    }

    @Override
    public HashMap<String, Object> getUserById(Long id) {
        HashMap<String, Object> userDetail = new HashMap<>();
        Optional<UserModel> user = userDb.findById(id);
        if (user.isPresent()) {
            userDetail.put("nama", user.get().getNama());
            userDetail.put("id", user.get().getId());
            userDetail.put("team", user.get().getTeam());
            userDetail.put("username", user.get().getUsername());
            userDetail.put("email", user.get().getEmail());
            Set<RoleModel> role = user.get().getRoles();
            userDetail.put("role", role);
            return userDetail;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserModel> user = userDb.findById(id);
        if(user.isPresent()){
            UserModel userModel = user.get();
            userDb.delete(userModel);
        }else{
            throw new NoSuchElementException();
        }
    }
}
