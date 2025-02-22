package fpt.intern.fa_api.service;

import java.util.Optional;

import fpt.intern.fa_api.model.entity.Training;
import org.springframework.validation.BindingResult;

import fpt.intern.fa_api.model.entity.ClassEntity;
import fpt.intern.fa_api.model.filter.ClassEntityFilter;
import fpt.intern.fa_api.model.request.AsEntity.ClassRequest;
import fpt.intern.fa_api.model.response.ClassEntityPageResponse;
import fpt.intern.fa_api.model.response.AsEntity.ClassResponse;

import java.util.List;

public interface ClassService {
	String getUsernameFromUserId(Long userId);
	List<ClassResponse> findAll();
	List<ClassResponse> findClassbyTraining(Training training);
	void updateByTraining(Training training);
	void delete(int id);
	List<ClassResponse> findClassWithSorting(String field);
	List<ClassResponse> findClassWithPagination(int page, int limit);
	List<ClassResponse> findClassWithPaginationAndSorting(int page, int limit, String field);
	ClassResponse save(ClassRequest classRequest, BindingResult bindingResult);
	Optional<ClassEntity> findByID(long id);
	ClassResponse update(ClassRequest classRequest, BindingResult bindingResult);
	List<ClassResponse> findByCodeAndName(String code, String name);

    ClassEntityPageResponse<ClassResponse> searchClass(ClassEntityFilter filter, int page, int size);

    ClassEntity findById(long id);

    ClassResponse filterRole(ClassEntity classEntity);
}
