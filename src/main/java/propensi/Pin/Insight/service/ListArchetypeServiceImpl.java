package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.ListArchetypeModel;
import propensi.Pin.Insight.model.RisetModel;
import propensi.Pin.Insight.repository.ListArchetypeDb;
//import propensi.Pin.Insight.repository.ListArchetypeDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ListArchetypeServiceImpl implements ListArchetypeService {

    @Autowired
    ListArchetypeDb listArchetypeDb;


    @Override
    public void addListArchetype(ListArchetypeModel listArchetype) {
        listArchetypeDb.save(listArchetype);
    }

    @Override
    public void remove(List<ListArchetypeModel> archetype) {
        for (int i = 0; i < archetype.size(); i++) {
            listArchetypeDb.delete(archetype.get(i));
        }
    }

//
//    @Override
//    public List<ListArchetypeModel> remove(ListArchetypeModel archetype) {
//        List<ListArchetypeModel> list = listArchetypeDb.findAll();
//
//
////
////        ListArchetypeModel target = listArchetypeDb.findById()
//
//        for (int i = 0; i < list.size() ; i++) {
//            if(list.get(i).getRiset().getId().equals(archetype.getId())){
//                list.remove(list.get(i));
//            }
//        }
//    }
}
