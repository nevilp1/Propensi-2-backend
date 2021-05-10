package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.ListArchetypeModel;
import propensi.Pin.Insight.model.RisetModel;

import java.util.List;

public interface ListArchetypeService {
    void addListArchetype (ListArchetypeModel listArchetype);
    void remove (List<ListArchetypeModel> archetype);

}
