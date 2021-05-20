package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.model.UserModel;

import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TrashBinRestService {
    void addRiset(RisetModel add);
    List<Map<String, Object>> listRiset();
    Optional<RisetModel> getRisetById(Long id);
    HashMap<String,Object> getRisetByIdRiset(Long id);


}
