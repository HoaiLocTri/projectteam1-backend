package fpt.intern.fa_api.service;

import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.entity.Training;
import fpt.intern.fa_api.model.entity.TrainingSyllabus;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusRequest;
import fpt.intern.fa_api.model.response.AsEntity.SyllabusResponse;

import java.util.List;

public interface TrainingSyllabusService {
    List<SyllabusResponse> findSyllabusByTraining(Training training);
    List<TrainingSyllabus> setGroup(Training training, List<SyllabusRequest> ListSyllabus);
    void insert(List<TrainingSyllabus> trainingSyllabusList);
    void update(List<TrainingSyllabus> trainingSyllabusList);
    void delete(List<TrainingSyllabus> trainingSyllabusList);

}
