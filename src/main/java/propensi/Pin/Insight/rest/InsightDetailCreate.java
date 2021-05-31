package propensi.Pin.Insight.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.usertype.UserType;
import propensi.Pin.Insight.model.InsightArchetypeModel;
import propensi.Pin.Insight.model.UserTypeModel;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InsightDetailCreate {

    @JsonProperty("insightPicName")
    private String insightPicName;

    @JsonProperty("insightTeamName")
    private String insightTeamName;

    @JsonProperty("note")
    private String note;

    @JsonProperty("insightStatement")
    private String insightStatement;

    @JsonProperty("riset")
    private Integer idRiset;

    @JsonProperty("user")
    private Integer idUser;

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("archetype")
    private List<Integer> archetype;

    @JsonProperty("userTypeList")
    private List<Integer> userTypeModels;

    @JsonProperty("archetype")
    public List<Integer> getArchetype() {
        return archetype;
    }

    @JsonProperty("insightList")
    private List<String> insightList;

    public void setArchetype(List<Integer> archetype) {
        this.archetype = archetype;
    }

    public List<String> getInsightList() {
        return insightList;
    }

    public void setInsightList(List<String> insightList) {
        this.insightList = insightList;
    }

    public List<Integer> getUserTypeModels() {
        return userTypeModels;
    }

    public void setUserTypeModels(List<Integer> userTypeModels) {
        this.userTypeModels = userTypeModels;
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

    public Integer getIdRiset() {
        return idRiset;
    }

    public void setIdRiset(Integer idRiset) {
        this.idRiset = idRiset;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InsightDetailCreate{" +
                "insightPicName='" + insightPicName + '\'' +
                ", insightTeamName='" + insightTeamName + '\'' +
                ", note='" + note + '\'' +
                ", insightStatement='" + insightStatement + '\'' +
                ", idRiset=" + idRiset +
                ", idUser=" + idUser +
                ", status=" + status +
                ", archetype=" + archetype +
                ", userTypeModels=" + userTypeModels +
                ", insightList=" + insightList +
                '}';
    }
}
