package fpt.intern.fa_api.repository;

import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.entity.TrainingSyllabus;
import fpt.intern.fa_api.model.entity.TrainingSyllabusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingSyllabusRepository extends JpaRepository<TrainingSyllabus, TrainingSyllabusId> {

    @Query(value = "SELECT * FROM group_training_syllabus WHERE training_id = :id", nativeQuery = true)
    List<TrainingSyllabus> findByTraingID(Long id);
}
