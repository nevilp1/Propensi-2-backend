package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.UserTypeModel;
import propensi.Pin.Insight.repository.ArchetypeDb;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArchetypeServiceImpl implements ArchetypeService{

    @Autowired
    ArchetypeDb archetypeDb;


    @Override
    public List<UserTypeModel> findAllArchetype() {
        return archetypeDb.findAll();
    }

    @Override
    public Optional<UserTypeModel> findById(Integer id) {
        return archetypeDb.findById(id);
    }

    @Override
    public void addArchetype(UserTypeModel archetype) {
        archetypeDb.save(archetype);
    }


}
