package propensi.Pin.Insight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="role")
public class RoleModel implements Serializable {

    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ERole namaRole;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return namaRole;
    }

    public void setName(ERole name) {
        this.namaRole = name;
    }
}
