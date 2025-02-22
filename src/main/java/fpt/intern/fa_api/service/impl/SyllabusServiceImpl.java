package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.exception.ApplicationException;
import fpt.intern.fa_api.exception.ValidationException;
import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.entity.SyllabusContent;
import fpt.intern.fa_api.model.helper.CSVHelper;
import fpt.intern.fa_api.model.mapper.SyllabusMapper;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusContentRequest;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusRequest;
import fpt.intern.fa_api.model.response.AsEntity.SyllabusResponse;
import fpt.intern.fa_api.repository.SyllabusContentRepository;
import fpt.intern.fa_api.repository.SyllabusRepository;
import fpt.intern.fa_api.repository.UserRepository;
import fpt.intern.fa_api.service.SyllabusService;
import fpt.intern.fa_api.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SyllabusServiceImpl implements SyllabusService {
    private final SyllabusRepository repository;
    private final SyllabusMapper syllabusMapper;

    private final ValidatorUtil validatorUtil;

    private final UserRepository userRepository;
    private final SyllabusContentRepository syllabusContentRepository;

    @Override
    public SyllabusResponse save(SyllabusRequest syllabusRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            // Map to Entity
            Syllabus syllabus = syllabusMapper.toEntity(syllabusRequest);

            // Save
            repository.saveAndFlush(syllabus);

            // Map to Response
            return syllabusMapper.toResponse(syllabus);
        } catch (ApplicationException ex) {
            throw ex;
        }
    }

    @Override
    public void saveDataFromCSV(MultipartFile file,String type) {
        try {
            if(type.equals("general")) {
                List<Syllabus> syllabusList = CSVHelper.csvToSyllabuses(file.getInputStream());
                for (Syllabus syllabus : syllabusList) {
                    if (userRepository.findById(syllabus.getCreatedBy().getId()).isPresent())
                        repository.save(syllabus);
                }
            } else if(type.equals("outline")){
                List<SyllabusContent> syllabusContents = CSVHelper.csvToSyllabusOutLine(file.getInputStream());
                for (SyllabusContent syllabusContent : syllabusContents) {
                    if (repository.findById(syllabusContent.getSyllabus().getId()).isPresent())
                        syllabusContentRepository.save(syllabusContent);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
