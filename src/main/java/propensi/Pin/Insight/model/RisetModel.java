package propensi.Pin.Insight.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="riset")
public class RisetModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="researchTitle", nullable = false)
    private String researchTitle;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name="researchDate", nullable = false)
    private Date researchDate;

    @NotNull
    @Column(name="researchLink", nullable = false)
    private String researchLink;

    @NotNull
    @Column(name="researchType", nullable = false)
    private String researchType;

    @NotNull
    @Column(name="projectName", nullable = false)
    private String projectName;

    @NotNull
    @Column(name="insightAmount", nullable = false)
    private Integer insight_amount;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name="inputDate", nullable = false)
    private Date inputDate;

    @NotNull
    @Column(name="status", nullable = false)
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel userRiset;

    @NotNull
    @Column(name="team", nullable = false)
    private String team;

    @NotNull
    @Column(name="pic", nullable = false)
    private String pic;

    @OneToMany(mappedBy = "riset", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<ListArchetypeModel> listArchetypeModel;

    @OneToMany(mappedBy = "risetInsight", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<InsightModel> insightModels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResearchTitle() {
        return researchTitle;
    }

    public void setResearchTitle(String researchTitle) {
        this.researchTitle = researchTitle;
    }

    public Date getResearchDate() {
        return researchDate;
    }

    public void setResearchDate(Date researchDate) {
        this.researchDate = researchDate;
    }

    public String getResearchLink() {
        return researchLink;
    }

    public void setResearchLink(String researchLink) {
        this.researchLink = researchLink;
    }

    public String getResearchType() {
        return researchType;
    }

    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getInsight_amount() {
        return insight_amount;
    }

    public void setInsight_amount(Integer insight_amount) {
        this.insight_amount = insight_amount;
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

    public UserModel getUserRiset() {
        return userRiset;
    }

    public void setUserRiset(UserModel userRiset) {
        this.userRiset = userRiset;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<ListArchetypeModel> getListArchetypeModel() {
        return listArchetypeModel;
    }

    public void setListArchetypeModel(List<ListArchetypeModel> listArchetypeModel) {
        this.listArchetypeModel = listArchetypeModel;
    }

    public List<InsightModel> getInsightModels() {
        return insightModels;
    }

    public void setInsightModels(List<InsightModel> insightModels) {
        this.insightModels = insightModels;
    }
}