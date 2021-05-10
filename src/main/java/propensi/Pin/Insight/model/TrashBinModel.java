//package propensi.Pin.Insight.model;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.List;
//
//@Entity
//@Table(name="trashBin")
//public class TrashBinModel implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
////    @OneToMany(mappedBy = "trashbin", fetch = FetchType.LAZY)
////    @OnDelete(action = OnDeleteAction.CASCADE)
////    @JsonIgnore
////    private List<RisetModel> risetModelList;
//
//    @OneToMany(mappedBy = "trashbin", fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private List<InsightModel> insightModelList;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
////
////    public List<RisetModel> getRisetModelList() {
////        return risetModelList;
////    }
////
////    public void setRisetModelList(List<RisetModel> risetModelList) {
////        this.risetModelList = risetModelList;
////    }
//
//    public List<InsightModel> getInsightModelList() {
//        return insightModelList;
//    }
//
//    public void setInsightModelList(List<InsightModel> insightModelList) {
//        this.insightModelList = insightModelList;
//    }
//}
