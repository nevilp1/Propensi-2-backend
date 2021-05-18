package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.ERole;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.model.RoleModel;
import propensi.Pin.Insight.model.UserModel;
import propensi.Pin.Insight.repository.InsightDb;
import propensi.Pin.Insight.repository.RisetDb;
import propensi.Pin.Insight.repository.UserDb;

import javax.transaction.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class TrashBinRestServiceImpl implements TrashBinRestService {

    @Autowired
    UserDb userDb;

    @Autowired
    RisetDb risetDb;

    @Autowired
    InsightDb insightDb;

    @Override
    public List<Map<String, Object>> listRiset() {
        List<Map<String,Object>> list = new ArrayList<>();
        List<RisetModel> allRiset = risetDb.findAll();
        for (int i = 0; i < allRiset.size(); i++) {
            if(allRiset.get(i).getStatus() == true){
                continue;
            }else {
                Map<String,Object> data = new HashMap<>();
                Date temp = allRiset.get(i).getResearchDate();
                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String stringDate = date.format(temp);
                data.put("research_date",stringDate);
                data.put("title",allRiset.get(i).getResearchTitle());
                data.put("research_type",allRiset.get(i).getResearchType());
                data.put("project_name",allRiset.get(i).getProjectName());
                data.put("insight_amount",allRiset.get(i).getInsight_amount());
                data.put("id",allRiset.get(i).getId());
                data.put("status", allRiset.get(i).getStatus());
                list.add(data);
            }
        }
        return list;
    }
}
