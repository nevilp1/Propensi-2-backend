package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.InsightArchetypeModel;

import java.util.List;

public interface InsightArchetypeService {
    void addListArchetype (InsightArchetypeModel listArchetype);
    void remove (List<InsightArchetypeModel> archetype);
}
