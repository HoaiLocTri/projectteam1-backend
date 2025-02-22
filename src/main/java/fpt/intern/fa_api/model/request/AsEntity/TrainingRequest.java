package fpt.intern.fa_api.model.request.AsEntity;

import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class TrainingRequest {
    private Long id;
    @NotBlank(message = "")

    private String name;

    private String description;

    private String duration;

    private String status;

    private Date startDate;

    private Date endDate;

    private List<SyllabusRequest> syllabusList;
}
