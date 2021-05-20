package propensi.Pin.Insight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.Pin.Insight.model.KomentarModel;
import propensi.Pin.Insight.repository.KomentarDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class KomentarServiceImpl implements KomentarService{

    @Autowired
    KomentarDb komentarDb;

    @Override
    public List<KomentarModel> getAllKomentar() {
        return komentarDb.findAll();
    }

    @Override
    public KomentarModel createKomentar(KomentarModel newKomentar) {
        komentarDb.save(newKomentar);
        return newKomentar;
    }

    @Override
    public void deleteKomentar(Long id) {
        komentarDb.deleteById(id);
    }

}
