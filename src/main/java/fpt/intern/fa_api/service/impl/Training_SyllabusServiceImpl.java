package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.exception.ApplicationException;
import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.mapper.SyllabusMapper;
import fpt.intern.fa_api.model.mapper.TrainingMapper;
import fpt.intern.fa_api.model.response.AsEntity.SyllabusResponse;
import fpt.intern.fa_api.model.response.AsEntity.TrainingResponse;
import fpt.intern.fa_api.repository.SyllabusRepository;
import fpt.intern.fa_api.repository.TrainingRepository;
import fpt.intern.fa_api.service.Training_SyllabusService;
import fpt.intern.fa_api.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Training_SyllabusServiceImpl implements Training_SyllabusService {

    private final TrainingRepository trainingRepository;
    private final SyllabusRepository syllabusRepository;
    private final TrainingMapper trainingMapper;
    private final SyllabusMapper syllabusMapper;

    private final ValidatorUtil validatorUtil;

    @Override
    public List<Syllabus> getListSyllabusbyTrainingID(Long trainingID) {
        try{
            List<Syllabus> syllabusList = syllabusRepository.findAllBySyllabusList(trainingID);
            return syllabusList;
        } catch (ApplicationException ex) {
            throw ex;
        }
    }
}
