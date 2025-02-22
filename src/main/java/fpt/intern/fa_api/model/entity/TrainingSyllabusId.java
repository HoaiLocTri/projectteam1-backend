package fpt.intern.fa_api.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class TrainingSyllabusId implements Serializable {
    private Long trainingId;
    private Long syllabusId;
}
