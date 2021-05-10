package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.UserTypeModel;

import java.util.List;
import java.util.Optional;

public interface ArchetypeService {
    List<UserTypeModel> findAllArchetype();
    Optional<UserTypeModel> findById(Integer id);
    void addArchetype(UserTypeModel archetype);
}
