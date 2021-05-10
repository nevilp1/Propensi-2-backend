package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.InsightModel;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.rest.InsightDetail;
import propensi.Pin.Insight.rest.InsightDetailCreate;

import java.util.List;
import java.util.Optional;

public interface InsightRestService {
    List<InsightDetail> getAllInsight();
    Optional<InsightModel> getInsight(Long idInsight);
    InsightModel createInsight(InsightModel insightModel);
    InsightModel archiveInsight(InsightModel insightModel);

    InsightModel updateInsight(InsightModel insightModel);
}
