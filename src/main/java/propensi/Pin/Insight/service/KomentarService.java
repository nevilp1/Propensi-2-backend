package propensi.Pin.Insight.service;

import propensi.Pin.Insight.model.KomentarModel;

import java.util.List;

public interface KomentarService {

    List<KomentarModel> getAllKomentar();

    KomentarModel createKomentar(KomentarModel newKomentar);

    void deleteKomentar(Long id);
}
