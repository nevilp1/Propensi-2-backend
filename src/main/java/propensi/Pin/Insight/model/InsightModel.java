package propensi.Pin.Insight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.catalina.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "insight")
public class InsightModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "insightStatement", nullable = false)
    private String insightStatement;

    @NotNull
    @Column(name = "insightPicName", nullable = false)
    private String insightPicName;

    @NotNull
    @Column(name = "insightTeamName", nullable = false)
    private String insightTeamName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "inputDate", nullable = false)
    private Date inputDate;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status;

    @NotNull
    @Column(name = "note", nullable = false)
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_riset", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RisetModel risetInsight;


    @OneToMany(mappedBy = "insightModel", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<InsightArchetypeModel> insightArchetypeModels;

    @OneToMany(mappedBy = "insightModel", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<KomentarModel> insightCommentModels;


    @Override
    public String toString() {
        return "InsightModel{" +
                "id=" + id +
                ", insightStatement='" + insightStatement + '\'' +
                ", insightPicName='" + insightPicName + '\'' +
                ", insightTeamName='" + insightTeamName + '\'' +
                ", inputDate=" + inputDate +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", user=" + user +
                ", risetInsight=" + risetInsight +
                ", insightArchetypeModels=" + insightArchetypeModels +
                '}';
    }

    public List<KomentarModel> getInsightCommentModels() {
        return insightCommentModels;
    }

    public void setInsightCommentModels(List<KomentarModel> insightCommentModels) {
        this.insightCommentModels = insightCommentModels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsightStatement() {
        return insightStatement;
    }

    public void setInsightStatement(String insightStatement) {
        this.insightStatement = insightStatement;
    }

    public String getInsightPicName() {
        return insightPicName;
    }

    public void setInsightPicName(String insightPicName) {
        this.insightPicName = insightPicName;
    }

    public String getInsightTeamName() {
        return insightTeamName;
    }

    public void setInsightTeamName(String insightTeamName) {
        this.insightTeamName = insightTeamName;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public RisetModel getRisetInsight() {
        return risetInsight;
    }

    public void setRisetInsight(RisetModel risetInsight) {
        this.risetInsight = risetInsight;
    }

    public List<InsightArchetypeModel> getInsightArchetypeModels() {
        return insightArchetypeModels;
    }

    public void setInsightArchetypeModels(List<InsightArchetypeModel> insightArchetypeModels) {
        this.insightArchetypeModels = insightArchetypeModels;
    }

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_trashbin", referencedColumnName = "id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private TrashBinModel trashbin;

//    @OneToMany(mappedBy = "insight", fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private List<KomentarModel> komentarModelList;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getInsightStatement() {
//        return insightStatement;
//    }
//
//    public void setInsightStatement(String insightStatement) {
//        this.insightStatement = insightStatement;
//    }
//
//    public RisetModel getRiset() {
//        return risetInsight;
//    }
//
//    public void setRiset(RisetModel riset) {
//        this.risetInsight = riset;
//    }
//
//    public String getInsightPicName() {
//        return insightPicName;
//    }
//
//    public void setInsightPicName(String insightPicName) {
//        this.insightPicName = insightPicName;
//    }
//
//    public String getInsightTeamName() {
//        return insightTeamName;
//    }
//
//    public void setInsightTeamName(String insightTeamName) {
//        this.insightTeamName = insightTeamName;
//    }
//
//    public Date getInputDate() {
//        return inputDate;
//    }
//
//    public void setInputDate(Date inputDate) {
//        this.inputDate = inputDate;
//    }
//
//    public Boolean getStatus() {
//        return status;
//    }
//
//    public void setStatus(Boolean status) {
//        this.status = status;
//    }
//
//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(String note) {
//        this.note = note;
//    }
//
//    public UserModel getUser() {
//        return user;
//    }
//
//    public void setUser(UserModel user) {
//        this.user = user;
//    }
//
////    public List<KomentarModel> getKomentarModelList() {
////        return komentarModelList;
////    }
////
////    public void setKomentarModelList(List<KomentarModel> komentarModelList) {
////        this.komentarModelList = komentarModelList;
////    }
//
////
////    public ArchetypeModel getTypeInsight() {
////        return typeInsight;
////    }
////
////    public void setTypeInsight(ArchetypeModel typeInsight) {
////        this.typeInsight = typeInsight;
////    }
//
//    public TrashBinModel getTrashbin() {
//        return trashbin;
//    }
//
//    public void setTrashbin(TrashBinModel trashbin) {
//        this.trashbin = trashbin;
//    }
//
////    public void setAll(Date date, String pic, String team, String note, String insight, RisetModel riset, boolean status, UserModel user, TrashBinModel trashbin, ArchetypeModel archtype) {
////        this.inputDate = date;
////        this.insightPicName = pic;
////        this.insightTeamName = team;
////        this.note = note;
////        this.insightStatement = insight;
////        this.risetInsight = riset;
////        this.status = status;
////        this.user = user;
////        this.trashbin = trashbin;
////        this.typeInsight = archtype;
////    }
}
