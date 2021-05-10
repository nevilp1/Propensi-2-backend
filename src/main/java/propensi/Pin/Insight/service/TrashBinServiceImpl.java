//package propensi.Pin.Insight.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import propensi.Pin.Insight.model.TrashBinModel;
//import propensi.Pin.Insight.repository.TrashBinDb;
//
//import javax.transaction.Transactional;
//
//@Service
//@Transactional
//public class TrashBinServiceImpl implements TrashBinService {
//
//    @Autowired
//    TrashBinDb trashBinDb;
//
//    @Override
//    public TrashBinModel createTrashBin(TrashBinModel trashBinModel) {
//        trashBinDb.save(trashBinModel);
//        return trashBinModel;
//    }
//
//    @Override
//    public TrashBinModel getRecentTrashbin() {
//        return trashBinDb.findTopByOrderByIdDesc();
//    }
//
//
//}
