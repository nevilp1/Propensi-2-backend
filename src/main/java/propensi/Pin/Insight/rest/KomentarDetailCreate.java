package propensi.Pin.Insight.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KomentarDetailCreate {

    @JsonProperty("komentar")
    private String komentar;

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("insightId")
    private Integer insightId;



    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInsightId() {
        return insightId;
    }

    public void setInsightId(Integer insightId) {
        this.insightId = insightId;
    }
}