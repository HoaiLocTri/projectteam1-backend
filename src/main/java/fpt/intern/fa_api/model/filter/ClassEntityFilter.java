package fpt.intern.fa_api.model.filter;

import fpt.intern.fa_api.model.enums.ClassStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record ClassEntityFilter(String name, List<String> locations, LocalDate dateFrom, LocalDate dateTo, LocalTime timeFrom,
                                LocalTime timeTo, ClassStatus classStatus, String fsu) {
}
