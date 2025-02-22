package fpt.intern.fa_api.repository;

import fpt.intern.fa_api.model.entity.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Long> {

    @Query(value = "SELECT * FROM class_schedule WHERE class_id LIKE CONCAT('%', :id, '%')", nativeQuery = true)
    List<ClassSchedule> findByClassID(Long id);
}