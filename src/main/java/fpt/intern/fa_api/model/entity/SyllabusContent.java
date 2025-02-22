package fpt.intern.fa_api.model.entity;

import fpt.intern.fa_api.model.enums.DeliveryType;
import fpt.intern.fa_api.model.enums.Method;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"id", "unit", "syllabusId"}))
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SyllabusContent implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Long day;
    private Long unit;
    private String unitName;
    private String lessonName;
    private String trainingMaterial;
    private Long minute;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    @Enumerated(EnumType.STRING)
    private Method method;
    @ManyToOne
    @JoinColumn(name = "syllabusId")
    private Syllabus syllabus;
}
