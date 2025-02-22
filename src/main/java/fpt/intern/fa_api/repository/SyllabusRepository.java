package fpt.intern.fa_api.repository;

import fpt.intern.fa_api.model.entity.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SyllabusRepository extends JpaRepository<Syllabus,Long> {
    @Query(value = "Select * from syllabus where id in (Select syllabus_id from project.training_syllabus where training_id= :training_id)", nativeQuery = true)
    List<Syllabus> findAllBySyllabusList(Long training_id);
}
