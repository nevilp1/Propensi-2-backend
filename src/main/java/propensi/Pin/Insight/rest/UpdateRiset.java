package propensi.Pin.Insight.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import propensi.Pin.Insight.model.UserTypeModel;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRiset {

    @JsonProperty("currentUser")
    private String currentUser;

    @JsonProperty("pic")
    private String pic;

    @JsonProperty("projectName")
    private String projectName;

    @JsonProperty("researchDate")
    private Date researchDate;

    @JsonProperty("researchLink")
    private String researchLink;

    @JsonProperty("researchTitle")
    private String researchTitle;

    @JsonProperty("researchType")
    private String researchType;

    @JsonProperty("team")
    private String team;

    @JsonProperty("archetype")
    private List<UserTypeModel> archetype;

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<UserTypeModel> getArchetype() {
        return archetype;
    }

    public void setArchetype(List<UserTypeModel> archetype) {
        this.archetype = archetype;
    }
}
