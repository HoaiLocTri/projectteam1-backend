package fpt.intern.fa_api.controller;

import fpt.intern.fa_api.exception.ApplicationException;
import fpt.intern.fa_api.exception.NotFoundException;
import fpt.intern.fa_api.exception.ValidationException;
import fpt.intern.fa_api.model.entity.ClassEntity;
import fpt.intern.fa_api.model.entity.ClassSchedule;
import fpt.intern.fa_api.model.enums.ClassStatus;
import fpt.intern.fa_api.model.mapper.UserMapper;
import fpt.intern.fa_api.model.request.AsEntity.ClassRequest;
import fpt.intern.fa_api.model.filter.ClassEntityFilter;
import fpt.intern.fa_api.model.request.ClassSearchCriteria;
import fpt.intern.fa_api.model.response.ApiResponse;
import fpt.intern.fa_api.model.response.AsEntity.ClassResponse;
import fpt.intern.fa_api.model.response.AsEntity.ClassScheduleResponse;
import fpt.intern.fa_api.service.ClassScheduleService;
import fpt.intern.fa_api.service.ClassService;
import fpt.intern.fa_api.util.ClassServiceUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Tag(name = "Class", description = "Class management APIs")
@RestController
@RequestMapping("/fa/api")
public class ClassController {

	@Autowired
	ClassService classRepository;
    @Autowired
    ClassScheduleService classScheduleService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ClassServiceUtil classServiceUtil;

	@PostMapping(value = { "/create/step1" })
    @PreAuthorize("hasAuthority('Class.Create')" )
	public ResponseEntity<ApiResponse> getname(@Valid @RequestBody ClassRequest classRequest,
			BindingResult bindingResult) {
		try {

			String code = classServiceUtil.saveClass();
			classRequest.setCode(code);
			classRequest.setStatus("PLANNING");
			// Save
			ClassResponse classResponse = classRepository.save(classRequest, bindingResult);

			// Response
			ApiResponse apiResponse = new ApiResponse();
			apiResponse.ok(classResponse);
			return ResponseEntity.ok(apiResponse);
		} catch (NotFoundException ex) {
			throw ex; // Rethrow NotFoundException
		} catch (ValidationException ex) {
			throw ex; // Rethrow ValidationException
		} catch (Exception ex) {
			throw new ApplicationException(ex.getMessage()); // Handle other exceptions
		}
	}
    @GetMapping("/getUsername")
    public String getUsernameFromUserId(@RequestParam Long id) {
        return classRepository.getUsernameFromUserId(id);
    }
	@DeleteMapping(value = {"/{id}"})
    public ResponseEntity<ApiResponse> delete(@PathVariable int id) {
        try {
            // Delete

            List<ClassScheduleResponse> listSchedule=classScheduleService.getScheduleByClassID((long) id);
            if(listSchedule!=null) {
                for (ClassScheduleResponse item : listSchedule) {
                    classScheduleService.delete(item.getId());
                }
            }
            classRepository.delete(id);
            // Response
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok();
            return ResponseEntity.ok(apiResponse);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(); // Handle other exceptions
        }
    }


	@PutMapping(value = { "/create/step2" })
    @PreAuthorize("hasAuthority('Class.FullAccess')")
	public ResponseEntity<ApiResponse> save(@Valid @RequestBody ClassRequest classRequest,
			BindingResult bindingResult) {
		try {
            System.out.println(classRequest);
			// Save
			ClassResponse classResponse = classRepository.update(classRequest, bindingResult);

			// Response
			ApiResponse apiResponse = new ApiResponse();
			apiResponse.ok(classResponse);
			return ResponseEntity.ok(apiResponse);
		} catch (NotFoundException ex) {
			throw ex; // Rethrow NotFoundException
		} catch (ValidationException ex) {
			throw ex; // Rethrow ValidationException
		} catch (Exception ex) {
			throw new ApplicationException(); // Handle other exceptions
		}
	}

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ClassResponse>>> getAllClassWithPagination(@RequestParam Map<String, String> customQuery) {
        try {
			List<ClassResponse> ListclassResponse = classRepository.findAll();

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(ListclassResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @GetMapping("/list/{field}")
    public ResponseEntity<ApiResponse<List<ClassResponse>>> getClassWithPaginationAndSort(@RequestParam Map<String, String> customQuery, @PathVariable String field) {
        try {
            boolean checkPage = customQuery.containsKey("page");      //check trường offset
            boolean checkLimit = customQuery.containsKey("limit");    //check trường limit

            int page = checkPage? Integer.parseInt(customQuery.get("page")):0;      //Nếu không có page thì mặc định page ở trang 0
            int limit = checkLimit? Integer.parseInt(customQuery.get("limit")):5;   //Nếu không có limit thì mặc định limit = 5

            List<ClassResponse> ListclassResponse = classRepository.findClassWithPaginationAndSorting(page, limit, field);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(ListclassResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @PostMapping(value = {"/search-with-code-and-name"})
    public ResponseEntity<ApiResponse> findByCodeAndName(@RequestParam String code, @RequestParam String name) {
        try {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(classRepository.findByCodeAndName(code, name));
            return ResponseEntity.ok(apiResponse);
        } catch (NotFoundException ex) {
            throw ex;
        }
    }

    @PostMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse> getFilter(@RequestBody ClassSearchCriteria criteria) {
        ClassEntityFilter classEntityFilter = new ClassEntityFilter(criteria.getName(), criteria.getLocations(),criteria.getDateFrom(), criteria.getDateTo(), criteria.getTimeFrom(), criteria.getTimeTo(), criteria.getClassStatus(), criteria.getFsu());
        ApiResponse response = new ApiResponse();
        response.setPayload(classRepository.searchClass(classEntityFilter, criteria.getPage(), criteria.getSize()));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/classes/{classId}")
    ResponseEntity<ApiResponse<ClassResponse>> getClassById(@PathVariable long classId){
        try {
            ApiResponse apiResponse = new ApiResponse();

            ClassEntity classEntity = classRepository.findById(classId);
            if (classEntity == null) {
                throw new NotFoundException("Class Not Found");
            }

            apiResponse.ok(classRepository.filterRole(classEntity));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        }
    }
}
