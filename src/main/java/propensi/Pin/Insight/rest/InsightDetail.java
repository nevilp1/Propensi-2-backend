package propensi.Pin.Insight.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import propensi.Pin.Insight.model.InsightArchetypeModel;
import propensi.Pin.Insight.model.KomentarModel;
import propensi.Pin.Insight.model.RoleModel;
import propensi.Pin.Insight.model.UserTypeModel;

import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InsightDetail {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("inputDate")
    private Date inputDate;

    @JsonProperty("insightPicName")
    private String insightPicName;

    @JsonProperty("insightTeamName")
    private String insightTeamName;

    @JsonProperty("note")
    private String note;

    @JsonProperty("insightStatement")
    private String insightStatement;

    @JsonProperty("riset")
    private String riset;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("archetype")
    private List<UserTypeModel> listArchetype;

    @JsonProperty("listKomentar")
    private List<KomentarDetail> listKomentar;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInsightStatement() {
        return insightStatement;
    }

    public void setInsightStatement(String insightStatement) {
        this.insightStatement = insightStatement;
    }

    public String getRiset() {
        return riset;
    }

    public void setRiset(String riset) {
        this.riset = riset;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<UserTypeModel> getListArchetype() {
        return listArchetype;
    }

    public void setListArchetype(List<UserTypeModel> listArchetype) {
        this.listArchetype = listArchetype;
    }

    public List<KomentarDetail> getListKomentar() {
        return listKomentar;
    }

    public void setListKomentar(List<KomentarDetail> listKomentar) {
        this.listKomentar = listKomentar;
    }

    //
//    @Override
//    public String toString() {
//        return "InsightDetail{" +
//                "insightPicName='" + insightPicName + '\'' +
//                ", insightTeamName='" + insightTeamName + '\'' +
//                ", note='" + note + '\'' +
//                ", insightStatement='" + insightStatement + '\'' +
//                ", riset=" + riset +
//                ", status=" + status +
//                ", archetype=" + archetype +
//                '}';
//    }
}
