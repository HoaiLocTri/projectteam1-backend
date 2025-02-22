package fpt.intern.fa_api.repository;

import fpt.intern.fa_api.model.entity.SyllabusContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusContentRepository extends JpaRepository<SyllabusContent, Long> {
    @Query("select s from SyllabusContent s where s.day=?1 and s.unit=?2 and s.syllabus.id=?3")
    SyllabusContent checkExistUnique(Long day,Long unit,Long syllabusId);

    List<SyllabusContent> findBySyllabus_Id(Long id);
}
