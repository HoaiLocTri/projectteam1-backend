package fpt.intern.fa_api.service;

import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.entity.Training;
import fpt.intern.fa_api.model.request.AsEntity.TrainingRequest;
import fpt.intern.fa_api.model.response.AsEntity.SyllabusResponse;
import fpt.intern.fa_api.model.response.AsEntity.TrainingResponse;

import java.util.List;

public interface Training_SyllabusService {
    List<Syllabus> getListSyllabusbyTrainingID(Long trainingID);

}
