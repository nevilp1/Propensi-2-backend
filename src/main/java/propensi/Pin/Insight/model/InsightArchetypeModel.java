package propensi.Pin.Insight.model;

        import com.fasterxml.jackson.annotation.JsonIgnore;
        import org.hibernate.annotations.OnDelete;
        import org.hibernate.annotations.OnDeleteAction;

        import javax.persistence.*;
        import java.io.Serializable;

@Entity
@Table(name="insightArchetype")
public class InsightArchetypeModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_insight", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsightModel insightModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_userType", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserTypeModel userType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsightModel getInsightModel() {
        return insightModel;
    }

    public void setInsightModel(InsightModel insightModel) {
        this.insightModel = insightModel;
    }

    public UserTypeModel getUserType() {
        return userType;
    }

    public void setUserType(UserTypeModel userType) {
        this.userType = userType;
    }
}