package propensi.Pin.Insight.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import propensi.Pin.Insight.model.UserTypeModel;

import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)
public class RisetaddInsight {
    @JsonProperty("insightStatement")
    private String insightStatement;

    @JsonProperty("archetype")
    private List<UserTypeModel> archetype;

    @JsonProperty("userCreate")
    private String userCreate;

    public String getInsightStatement() {
        return insightStatement;
    }

    public void setInsightStatement(String insightStatement) {
        this.insightStatement = insightStatement;
    }

    public List<UserTypeModel> getArchetype() {
        return archetype;
    }

    public void setArchetype(List<UserTypeModel> archetype) {
        this.archetype = archetype;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }
}
