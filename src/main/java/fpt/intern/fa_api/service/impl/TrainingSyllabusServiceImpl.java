package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.exception.NotFoundException;
import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.entity.Training;
import fpt.intern.fa_api.model.entity.TrainingSyllabus;
import fpt.intern.fa_api.model.entity.TrainingSyllabusId;
import fpt.intern.fa_api.model.mapper.SyllabusMapper;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusRequest;
import fpt.intern.fa_api.model.response.AsEntity.SyllabusResponse;
import fpt.intern.fa_api.repository.SyllabusRepository;
import fpt.intern.fa_api.repository.TrainingSyllabusRepository;
import fpt.intern.fa_api.service.TrainingSyllabusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingSyllabusServiceImpl implements TrainingSyllabusService {
    private final TrainingSyllabusRepository trainingSyllabusRepository;
    private final SyllabusRepository syllabusRepository;
    private final SyllabusMapper syllabusMapper;

    @Override
    public List<SyllabusResponse> findSyllabusByTraining(Training training) {
        List<TrainingSyllabus> trainingSyllabusList = training.getTrainingSyllabusList();
        if (trainingSyllabusList == null){
            throw new NotFoundException("List Syllabus Not Found");
        }
        List<Syllabus> syllabusList = new ArrayList<>();
        for (TrainingSyllabus trainingSyllabus : trainingSyllabusList) {
            syllabusList.add(trainingSyllabus.getSyllabus());
        }

        return syllabusMapper.toResponseList(syllabusList);
    }

    @Override
    public List<TrainingSyllabus> setGroup(Training training, List<SyllabusRequest> ListSyllabus) {
        List<TrainingSyllabus> trainingSyllabusList = new ArrayList<>();
        List<Syllabus> syllabusList = syllabusMapper.toEntityList(ListSyllabus);

        for (Syllabus syllabusCheck : syllabusList){
            Syllabus syllabus = syllabusRepository.findById(syllabusCheck.getId()).orElseThrow(() -> new NotFoundException("Syllabus Not Found"));

            TrainingSyllabus trainingSyllabus = new TrainingSyllabus();
            trainingSyllabus.setTraining(training);
            trainingSyllabus.setSyllabus(syllabus);

            // set trainingSyllabusId
            TrainingSyllabusId trainingSyllabusId = new TrainingSyllabusId();
            trainingSyllabusId.setSyllabusId(syllabus.getId());
            trainingSyllabusId.setTrainingId(training.getId());
            trainingSyllabus.setId(trainingSyllabusId);

            trainingSyllabusList.add(trainingSyllabus);
        }

        return trainingSyllabusList;

    }

    @Override
    public void insert(List<TrainingSyllabus> trainingSyllabusList) {
        trainingSyllabusRepository.saveAll(trainingSyllabusList);
    }

    @Override
    public void update(List<TrainingSyllabus> newList) {
        Long trainingID = 0L;
        for (TrainingSyllabus trainingSyllabus: newList){
                trainingID = trainingSyllabus.getTraining().getId();
                break;
        }

        if(trainingID != 0L){
            List<TrainingSyllabus> oldList = trainingSyllabusRepository.findByTraingID(trainingID);
            delete(oldList);
            insert(newList);
        }
    }

    @Override
    public void delete(List<TrainingSyllabus> trainingSyllabusList) {
        trainingSyllabusRepository.deleteAll(trainingSyllabusList);
    }
}
