package fpt.intern.fa_api.model.entity;

import fpt.intern.fa_api.model.enums.SyllabusStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "syllabus")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Syllabus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String technicalRequirement;
    private String version;
    private Integer trainingAudience;
    private String topicOutline;
    private String trainingMaterials;
    private String trainingPrinciples;
    private String priority;
    @Enumerated(EnumType.STRING)
    private SyllabusStatus status;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User createdBy;

    @OneToMany(mappedBy = "syllabus")
    private List<TrainingSyllabus> trainingSyllabusList;
    @PrePersist
    public void setCreate() {

        this.createdBy = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}

