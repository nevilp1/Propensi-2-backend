package propensi.Pin.Insight.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KomentarDetail {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("komentar")
    private String komentar;

    @JsonProperty("username")
    private String username;

    @JsonProperty("insightId")
    private Integer insightId;

    @JsonProperty("inputDate")
    private Date inputDate;

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getInsightId() {
        return insightId;
    }

    public void setInsightId(Integer insightId) {
        this.insightId = insightId;
    }
}