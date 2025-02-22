package fpt.intern.fa_api.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "group_training_syllabus")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TrainingSyllabus {
    @EmbeddedId
    private TrainingSyllabusId id;

    @ManyToOne
    @MapsId("trainingId")
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne
    @MapsId("syllabusId")
    @JoinColumn(name = "syllabus_id")
    private Syllabus syllabus;
}
