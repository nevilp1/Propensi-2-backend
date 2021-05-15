package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.rest.RisetDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RisetService {
    void addRiset(RisetModel add);
    List<Map<String,Object>> listRiset();
    List<Map<String,Object>> insightRisetList();
    List<Map<String,Object>> listInsightByIDRiset(Long id);
    Optional<RisetModel> getRisetById(Long id);
    HashMap<String,Object> getRisetByIdRiset(Long id);
    RisetModel updateRiset (RisetModel riset);
    void archiveRiset(Long id);
}
