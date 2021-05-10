package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.InsightArchetypeModel;
import propensi.Pin.Insight.repository.InsightArchetypeDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InsightArchetypeServiceImpl implements InsightArchetypeService {

    @Autowired
    InsightArchetypeDb insightArchetypeDb;

    @Override
    public void addListArchetype(InsightArchetypeModel listArchetype) {
        insightArchetypeDb.save(listArchetype);
    }

    @Override
    public void remove(List<InsightArchetypeModel> archetype) {
        for (int i = 0; i < archetype.size(); i++) {
            insightArchetypeDb.delete(archetype.get(i));
        }

    }
}
