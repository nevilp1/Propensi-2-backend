package propensi.Pin.Insight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="participants")
public class ParticipantModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="participantName", nullable = false)
    private String participantName;

    @NotNull
    @Column(name="participantEmail", nullable = false)
    private String participantEmail;

    @NotNull
    @Column(name="phoneNumber", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name="age", nullable = false)
    private Integer age;

    @NotNull
    @Column(name="domicile", nullable = false)
    private String domicile;

    @NotNull
    @Column(name="participantStatus", nullable = false)
    private String participantStatus;

//    @NotNull
    @Column(name="participantNotes")
    private String participantNotes;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="inputDate", nullable = false)
    private Date inputDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "surveyId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SurveyModel survey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getParticipantStatus() {
        return participantStatus;
    }

    public void setParticipantStatus(String participantStatus) {
        this.participantStatus = participantStatus;
    }

    public String getParticipantNotes() {
        return participantNotes;
    }

    public void setParticipantNotes(String participantNotes) {
        this.participantNotes = participantNotes;
    }

    public SurveyModel getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyModel survey) {
        this.survey = survey;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }
}
