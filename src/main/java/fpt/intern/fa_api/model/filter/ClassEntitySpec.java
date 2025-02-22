package fpt.intern.fa_api.model.filter;

import fpt.intern.fa_api.model.entity.ClassEntity;
import fpt.intern.fa_api.model.enums.ClassStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ClassEntitySpec {
    public static final String NAME = "name";
    public static final String LOCATIONS = "location";
    public static final String DATE_FROM = "startDate";
    public static final String DATE_TO = "endDate";
    public static final String CLASS_STATUS = "status";
    public static final String FSU = "fsu";

    private ClassEntitySpec() {
    }

    public static Specification<ClassEntity> filterBy(ClassEntityFilter classEntityFilter) {
        return Specification
                .where(hasLocations(classEntityFilter.locations()))
                .and(hasName(classEntityFilter.name()))
                .and(hasClassStatus(classEntityFilter.classStatus()))
                .and(hasFsu(classEntityFilter.fsu()))
                .and(hasDateGreaterThan(classEntityFilter.dateTo()))
                .and(hasDateLessThan(classEntityFilter.dateFrom()))
                ;
    }

    private static Specification<ClassEntity> hasLocations(List<String> locations) {
        return (root, query, criteriaBuilder) -> {
            if (locations == null || locations.isEmpty()) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.or(locations.stream()
                        .map(location -> criteriaBuilder.equal(root.get(LOCATIONS), location))
                        .toArray(Predicate[]::new));
            }
        };
    }

    private static Specification<ClassEntity> hasClassStatus(ClassStatus classStatus) {
        return (root, query, cb) -> classStatus == null ? cb.conjunction() : cb.equal(root.get(CLASS_STATUS), classStatus);
    }

    private static Specification<ClassEntity> hasDateGreaterThan(LocalDate dateTo) {
        return (root, query, cb) -> dateTo == null ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get(DATE_TO), dateTo);
    }

    private static Specification<ClassEntity> hasDateLessThan(LocalDate dateFrom) {
        return (root, query, cb) -> dateFrom == null ? cb.conjunction() : cb.lessThanOrEqualTo(root.get(DATE_FROM), dateFrom);
    }

    private static Specification<ClassEntity> hasFsu(String fsu) {
        return (root, query, cb) -> fsu == null || fsu.isEmpty() ? cb.conjunction() : cb.equal(root.get(FSU), fsu);
    }

    private static Specification<ClassEntity> hasName(String name) {
        return ((root, query, cb) -> name == null || name.isEmpty() ? cb.conjunction() : cb.equal(root.get(NAME), name));
    }
}
