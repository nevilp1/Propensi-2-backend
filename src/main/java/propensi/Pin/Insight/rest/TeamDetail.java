package propensi.Pin.Insight.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDetail {
    @JsonProperty("namaTim")
    private List<String> namaTim;

    @JsonProperty("jumlahRiset")
    private List<Long> jumlahRiset;


    public List<String> getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(List<String> namaTim) {
        this.namaTim = namaTim;
    }

    public List<Long> getJumlahRiset() {
        return jumlahRiset;
    }

    public void setJumlahRiset(List<Long> jumlahRiset) {
        this.jumlahRiset = jumlahRiset;
    }
}
