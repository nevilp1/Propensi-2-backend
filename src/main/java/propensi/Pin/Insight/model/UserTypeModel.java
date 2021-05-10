package propensi.Pin.Insight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="userType")
public class UserTypeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name="typeName", nullable = false)
    private String typeName;

    @OneToMany(mappedBy = "userType", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<ListArchetypeModel> listArchetypeModel;

    @OneToMany(mappedBy = "userType", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<InsightArchetypeModel> insightArchetypeModels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<ListArchetypeModel> getListArchetypeModel() {
        return listArchetypeModel;
    }

    public void setListArchetypeModel(List<ListArchetypeModel> listArchetypeModel) {
        this.listArchetypeModel = listArchetypeModel;
    }

    public List<InsightArchetypeModel> getInsightArchetypeModels() {
        return insightArchetypeModels;
    }

    public void setInsightArchetypeModels(List<InsightArchetypeModel> insightArchetypeModels) {
        this.insightArchetypeModels = insightArchetypeModels;
    }
}