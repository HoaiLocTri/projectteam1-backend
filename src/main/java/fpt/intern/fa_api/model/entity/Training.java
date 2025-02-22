package fpt.intern.fa_api.model.entity;

import java.sql.Date;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;

@Setter
@Getter
@Entity
@Table(name ="Training")
@Data
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name may not be blank")
    @Column(nullable = false)
    private String name;

    private String description;

    private String duration;

    private String status;

    private Date startDate;
    
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "createdBy",referencedColumnName = "id")
    private User createdBy;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "modifiedBy",referencedColumnName = "id")
    private User modifiedBy;

    private Date modifiedDate;

    @OneToMany(mappedBy = "training")
    private List<TrainingSyllabus> trainingSyllabusList;

    @PrePersist
    public void setCreate() {
        this.createdDate = new Date(System.currentTimeMillis());
        this.createdBy = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.modifiedDate = new Date(System.currentTimeMillis());
        this.modifiedBy = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PreUpdate
    public void setModified(){
        this.modifiedDate= new Date(System.currentTimeMillis());
        this.modifiedBy = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
