package propensi.Pin.Insight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="survey")
public class SurveyModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="researchTitle", nullable = false)
    private String researchTitle;

    @NotNull
    @Column(name="researchType", nullable = false)
    private String researchType;

//    @NotNull
//    @Column(name="linkToForm", nullable = false)
//    private String linkToForm;
//
//    @NotNull
//    @Column(name="linkToParticipant", nullable = false)
//    private String linkToParticipant;

    @NotNull
    @Column(name="picName", nullable = false)
    private String picName;

    @NotNull
    @Column(name="team", nullable = false)
    private String team;

    @NotNull
    @Column(name="content", nullable = false)
    private String content;

    @NotNull
    @Column(name="criteria", nullable = false)
    private String criteria;

    @NotNull
    @Column(name="status", nullable = false)
    private Boolean status;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="inputDate", nullable = false)
    private Date inputDate;

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ParticipantModel> listParticipant;

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

    public String getResearchType() {
        return researchType;
    }

    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }

//    public String getLinkToForm() {
//        return linkToForm;
//    }
//
//    public void setLinkToForm(String linkToForm) {
//        this.linkToForm = linkToForm;
//    }
//
//    public String getLinkToParticipant() {
//        return linkToParticipant;
//    }
//
//    public void setLinkToParticipant(String linkToParticipant) {
//        this.linkToParticipant = linkToParticipant;
//    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public List<ParticipantModel> getListParticipant() {
        return listParticipant;
    }

    public void setListParticipant(List<ParticipantModel> listParticipant) {
        this.listParticipant = listParticipant;
    }
}
