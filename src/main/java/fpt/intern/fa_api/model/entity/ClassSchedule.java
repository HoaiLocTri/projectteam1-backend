package fpt.intern.fa_api.model.entity;

import fpt.intern.fa_api.model.enums.DayOfWeek;
import fpt.intern.fa_api.model.enums.TimeOfDay;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "class_schedule", uniqueConstraints = { @UniqueConstraint(columnNames = { "class_id", "timeOfDay", "dayOfWeek"}) })
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    @Enumerated(EnumType.STRING)
    private TimeOfDay timeOfDay;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
}
