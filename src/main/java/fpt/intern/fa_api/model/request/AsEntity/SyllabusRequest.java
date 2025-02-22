package fpt.intern.fa_api.model.request.AsEntity;

import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.enums.SyllabusStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SyllabusRequest {
    private Long id;
    @NotBlank(message = "Please enter name")
    private String name;
    private String technicalRequirement;
    private String version;
    private Integer trainingAudience;
    private String topicOutline;
    private String trainingMaterials;
    private String trainingPrinciples;
    private String priority;
    private String status;
    private User createdBy;
}
