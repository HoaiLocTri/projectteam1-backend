package fpt.intern.fa_api.model.response.AsEntity;

import fpt.intern.fa_api.model.entity.Syllabus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class TrainingResponse {
    private Long id;
    private String name;
    private String description;
    private String duration;
    private String status;
    private Date startDate;
    private Date endDate;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private List<SyllabusResponse> syllabusList;
    private List<ClassResponse> classList;
}

