package fpt.intern.fa_api.model.response.AsEntity;

import fpt.intern.fa_api.model.entity.Training;
import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.enums.SyllabusStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SyllabusResponse {
    private Long id;
    private String name;
    private String technicalRequirement;
    private String version;
    private Integer trainingAudience;
    private String topicOutline;
    private String trainingMaterials;
    private String trainingPrinciples;
    private String priority;
    private String status;
    private UserResponse author;
}
