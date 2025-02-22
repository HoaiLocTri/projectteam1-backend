package fpt.intern.fa_api.repository;

import fpt.intern.fa_api.model.entity.ClassEntity;
import fpt.intern.fa_api.model.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long>, JpaSpecificationExecutor<ClassEntity> {
    @Query(value = "Select * from class where class_id='1'", nativeQuery = true)
    public ClassEntity checkliked();
    List<ClassEntity> findAllByTraining(Training training);
    List<ClassEntity> findByCodeAndName(String code, String name);
	List<ClassEntity> searchAllByIdAndName(Integer id, String name);
	@Query(value = "SELECT count(code) FROM Class WHERE code LIKE CONCAT('%', :year, '%')", nativeQuery = true)
	public int findLastClassCodeByYear(@Param("year")String year);
    @Query(value = "SELECT u.username FROM User u WHERE u.id = :userId", nativeQuery = true)
    public String findUsernameById(@Param("userId") Long userId);

    @Query(value = "select * from Class WHERE (start_date <= :start and :start <= end_date) OR (start_date <= :end and :end <= end_date)", nativeQuery = true)
    public List<ClassEntity> findAllByStart_dateAndeAndEnd_date(LocalDate start, LocalDate end);

}
