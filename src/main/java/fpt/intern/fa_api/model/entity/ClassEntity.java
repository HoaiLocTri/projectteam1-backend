package fpt.intern.fa_api.model.entity;

import fpt.intern.fa_api.model.enums.ClassStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "Class")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String code;

    @Enumerated(EnumType.STRING)
    private ClassStatus status;

    private String location;

    private String fsu;

    private Date start_date;

    private Date end_date;

    @ManyToOne
    @JoinColumn(name = "userCreate", referencedColumnName = "id")
    ////////////
    private User created_by;

    private Date created_date;

    @ManyToOne
    @JoinColumn(name = "userModify", referencedColumnName = "id")
    private User modified_by;

    private Date modified_date;

    @OneToMany(mappedBy = "classEntity")
    private List<UserClass> userClassList;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @OneToMany(mappedBy = "classEntity")
    private List<ClassSchedule> classScheduleList;

    @PrePersist
    public void setCreated_date() {
        this.created_date = new Date(System.currentTimeMillis());
        this.modified_date = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    public void setModified_date(){
        this.modified_date = new Date(System.currentTimeMillis());
    }

}
