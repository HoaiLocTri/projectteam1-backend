package fpt.intern.fa_api.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import fpt.intern.fa_api.model.enums.ClassStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ClassSearchCriteria {
    private String name;
    private List<String> locations;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateTo;
    @JsonFormat(pattern="HH:mm")
    private LocalTime timeFrom;
    @JsonFormat(pattern="HH:mm")
    private LocalTime timeTo;
    private ClassStatus classStatus;
    private String fsu;
    private int page;
    private int size;
}