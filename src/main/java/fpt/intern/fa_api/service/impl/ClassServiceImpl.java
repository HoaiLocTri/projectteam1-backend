package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.exception.ApplicationException;
import fpt.intern.fa_api.exception.NotFoundException;
import fpt.intern.fa_api.exception.ValidationException;
import fpt.intern.fa_api.model.entity.ClassEntity;
import fpt.intern.fa_api.model.entity.Training;
import fpt.intern.fa_api.model.enums.RoleStatus;
import fpt.intern.fa_api.model.filter.ClassEntityFilter;
import fpt.intern.fa_api.model.filter.ClassEntitySpec;
import fpt.intern.fa_api.model.mapper.ClassMapper;
import fpt.intern.fa_api.model.mapper.UserMapper;
import fpt.intern.fa_api.model.request.AsEntity.ClassRequest;
import fpt.intern.fa_api.model.response.AsEntity.UserResponse;
import fpt.intern.fa_api.model.response.ClassEntityPageResponse;
import fpt.intern.fa_api.model.response.AsEntity.ClassResponse;
import fpt.intern.fa_api.repository.ClassRepository;
import fpt.intern.fa_api.service.ClassService;
import fpt.intern.fa_api.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Sort;



@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassRepository repository;
    private final ClassMapper classMapper;
	private final UserMapper userMapper;

    private final ValidatorUtil validatorUtil;

	@Override
	public String getUsernameFromUserId(Long userId) {
		return repository.findUsernameById(userId);
	}
	@Override
	public List<ClassResponse> findAll() {
		try {


			List<ClassEntity> list = repository.findAll();

			List<ClassResponse> ListclassResponse = classMapper.toResponseList(list);

			return ListclassResponse;
		} catch (ApplicationException ex) {
			throw ex;
		}
	}

	@Override
	public List<ClassResponse> findClassbyTraining(Training training) {
		try {
			List<ClassEntity> list = repository.findAllByTraining(training);
			List<ClassResponse> ListclassResponse = classMapper.toResponseList(list);

			return ListclassResponse;
		} catch (ApplicationException ex) {
			throw ex;
		}
	}

    @Override
    public void updateByTraining(Training training) {
		List<ClassEntity> list = repository.findAllByTraining(training);
		if(!list.isEmpty()){
			for(ClassEntity classEntity: list){
				classEntity.setTraining(null);
			}

			repository.saveAllAndFlush(list);
		}
    }

    @Override
	public List<ClassResponse> findClassWithSorting(String field){
		try {

			List<ClassEntity> list = repository.findAll(Sort.by(Sort.Direction.DESC, "modifiedDate"));

			List<ClassResponse> ListclassResponse = classMapper.toResponseList(list);

			return ListclassResponse;
		} catch (ApplicationException ex) {
			throw ex;
		}
	}

	@Override
	public List<ClassResponse> findClassWithPagination(int page, int pagesize){
		try {
			Page<ClassEntity> classenitylist = repository.findAll(PageRequest.of(page, pagesize));
			List<ClassEntity> list = new ArrayList<ClassEntity>(classenitylist.getSize());
			for ( ClassEntity classEnity : classenitylist ) {
				list.add(classEnity);
			}
			List<ClassResponse> ListclassResponse = classMapper.toResponseList(list);
			return ListclassResponse;
		} catch (ApplicationException ex) {
			throw ex;
		}
	}


	@Override
	public List<ClassResponse> findClassWithPaginationAndSorting(int page, int pagesize, String field){
		try {
			Page<ClassEntity> classenitylist = repository.findAll(PageRequest.of(page, pagesize).withSort(Sort.by(field)));
			List<ClassEntity> list = new ArrayList<ClassEntity>(classenitylist.getSize());
			for ( ClassEntity classEnity : classenitylist ) {
				list.add(classEnity);
			}
			List<ClassResponse> ListclassResponse = classMapper.toResponseList(list);
			return ListclassResponse;
		} catch (ApplicationException ex) {
			throw ex;
		}
	}




	@Override
	public ClassResponse save(ClassRequest classRequest, BindingResult bindingResult) {
		try {


			if (bindingResult.hasErrors()) {
				Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
				throw new ValidationException(validationErrors);
			}

			// Map to Entity
			ClassEntity classEntity = classMapper.toEntity(classRequest);

			// Save
			repository.saveAndFlush(classEntity);

            // Map to Response
            return classMapper.toResponse(classEntity);
        } catch (ApplicationException ex) {
            throw ex;
        }
    }


		@Override
		public ClassResponse update(ClassRequest classRequest, BindingResult bindingResult) {
			 try {
		            Optional<ClassEntity> existingClass = findByID(classRequest.getId());
		            if (existingClass == null) {
		                throw new NotFoundException("Product Not Found");
		            }


		            if (bindingResult.hasErrors()) {
		                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
		                throw new ValidationException(validationErrors);
		            }

		            // Map to Entity
		            ClassEntity classEntity = classMapper.toEntity(classRequest);


		            // Update
		            repository.saveAndFlush(classEntity);

		            // Map to Response
		            return classMapper.toResponse(classEntity);
		        } catch (ApplicationException ex) {
		            throw ex;
		        }
		}


		@Override
		public Optional<ClassEntity> findByID(long id) {

			return repository.findById(id);
		}

    @Override
    public List<ClassResponse> findByCodeAndName(String code, String name) {
        List<ClassResponse> responses = new ArrayList<>();
        repository.findByCodeAndName(code, name).forEach(x -> {
            responses.add(classMapper.toResponse(x));
        });
        if (responses.isEmpty()) throw new NotFoundException("Không bản ghi nào được tìm thấy");
        return responses;
//            return repository.findByCodeAndName(code,name);
    }

    @Override
    public ClassEntityPageResponse<ClassResponse> searchClass(ClassEntityFilter filter, int page, int size) {
        Specification<ClassEntity> spec = ClassEntitySpec.filterBy(filter);
        Page<ClassEntity> pageResult = repository.findAll(spec, PageRequest.of(page, size));
        return classMapper.toClassEntityPageResponse(pageResult);
    }


	@Override
	public ClassEntity findById(long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public ClassResponse filterRole(ClassEntity classEntity){
		ClassResponse result = classMapper.toResponse(classEntity);
		List<UserResponse> trainer = new ArrayList<>();
		List<UserResponse> admin = new ArrayList<>();

//		for(int i=0;i<classEntity.getUserList().size();i++){
//			UserResponse user =  userMapper.toResponse(classEntity.getUserList().get(i));
//			if(user.getRoles().equals(RoleStatus.TRAINER)){
//				trainer.add(user);
//			}
//			else admin.add(user);
//		}
//		result.setTrainer(trainer);
//		result.setAdmin(admin);
		return result;
	}
	 @Override
	    public void delete(int id) {
	        try {
	            ClassEntity product = findById(id);
	            if (product == null) {
	                throw new NotFoundException("Product Not Found");
	            }

	            
	            repository.delete(product);
	        } catch (ApplicationException ex) {
	            throw ex;
	        }
	    }
}



