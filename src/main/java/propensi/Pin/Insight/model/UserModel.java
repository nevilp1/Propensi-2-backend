package propensi.Pin.Insight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="userModel")
public class UserModel implements Serializable {
    public UserModel(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="team")
    private String team;

    //    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private RoleModel role;

    @NotNull
    @Column(name="username", nullable = false)
    private String username;

    @NotNull
//    @Lob
    @Column(name="password", nullable = false)
    private String password;

    @NotNull
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name="email", nullable = false)
    private String email;

    // ini blm diganti not null
    @Column(name="status", nullable = true)
    private Boolean status;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="registerDate", nullable = false)
    private Date registerDate;

    @OneToMany(mappedBy = "userKomentar", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<KomentarModel> komentarModelList;

    @OneToMany(mappedBy = "userRiset", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<RisetModel> risetModelList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleModel> roles = new HashSet<>();

    public UserModel(String username, String password, String nama, String email, String team) {
        this.username = username;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.team = team;
        this.registerDate = new Date();
    }


    public Set<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

    public List<KomentarModel> getKomentarModelList() {
        return komentarModelList;
    }

    public void setKomentarModelList(List<KomentarModel> komentarModelList) {
        this.komentarModelList = komentarModelList;
    }

    public List<RisetModel> getRisetModelList() {
        return risetModelList;
    }

    public void setRisetModelList(List<RisetModel> risetModelList) {
        this.risetModelList = risetModelList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
