package propensi.Pin.Insight.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InsightUserType {
    @JsonProperty("userType")
    private List<String> userType;

    @JsonProperty("jumlahInsight")
    private List<Integer> jumlahInsight;

    public List<String> getUserType() {
        return userType;
    }

    public void setUserType(List<String> userType) {
        this.userType = userType;
    }

    public List<Integer> getJumlahInsight() {
        return jumlahInsight;
    }

    public void setJumlahInsight(List<Integer> jumlahInsight) {
        this.jumlahInsight = jumlahInsight;
    }
}
