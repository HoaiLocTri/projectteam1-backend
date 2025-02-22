package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.exception.ApplicationException;
import fpt.intern.fa_api.exception.NotFoundException;
import fpt.intern.fa_api.model.entity.Training;
import fpt.intern.fa_api.model.mapper.TrainingMapper;
import fpt.intern.fa_api.model.request.AsEntity.TrainingRequest;
import fpt.intern.fa_api.model.response.AsEntity.TrainingResponse;
import fpt.intern.fa_api.repository.TrainingRepository;
import fpt.intern.fa_api.service.ClassService;
import fpt.intern.fa_api.service.TrainingService;
import fpt.intern.fa_api.service.TrainingSyllabusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository repository;
    private final TrainingMapper trainingMapper;
    private final ClassService classService;
    private final TrainingSyllabusService trainingSyllabusService;

    @Override
    public TrainingResponse insert(TrainingRequest trainingRequest) {
        try {
            if(trainingRequest.getName().isEmpty())         throw new NotFoundException("Chưa có tên chương trình đào tạo");
            if(trainingRequest.getDescription().isEmpty())  throw new NotFoundException("Chưa có mô tả chương trình đào tạo");
            if(trainingRequest.getDuration().isEmpty())     throw new NotFoundException("Chưa có khoảng thời gian chương trình đào tạo");
            if(trainingRequest.getSyllabusList().isEmpty()) throw new NotFoundException("Chưa có danh sách tài liệu trong chương trình đào tạo");

            //Request to Entity
            Training training = trainingMapper.toEnity(trainingRequest);

            training.setStatus("INACTIVE");

            //save training
            repository.save(training);

            // set group_traing_syllabus
            training.setTrainingSyllabusList(trainingSyllabusService.setGroup(training, trainingRequest.getSyllabusList()));
            // save group_traing_syllabus
            trainingSyllabusService.insert(training.getTrainingSyllabusList());

            return setClassandSyllabus(training);
        } catch (ApplicationException ex) {
            throw ex;
        }
    }

    @Override
    public TrainingResponse update(TrainingRequest trainingRequest){
        try {
            // check điều kiện cần
            Training oldTraining = repository.findById(trainingRequest.getId()).orElseThrow(()->new NotFoundException("Không tìm thấy chương trình đào tạo này"));
            if(trainingRequest.getName().isEmpty())         throw new NotFoundException("Chưa có tên chương trình đào tạo");
            if(trainingRequest.getDescription().isEmpty())  throw new NotFoundException("Chưa có mô tả chương trình đào tạo");
            if(trainingRequest.getDuration().isEmpty())     throw new NotFoundException("Chưa có khoảng thời gian chương trình đào tạo");
            if(trainingRequest.getSyllabusList().isEmpty()) throw new NotFoundException("Chưa có danh sách tài liệu trong chương trình đào tạo");

            //Request to Entity
            Training newTraining = trainingMapper.toEnity(trainingRequest);

            newTraining.setCreatedBy(oldTraining.getCreatedBy());
            newTraining.setCreatedDate(oldTraining.getCreatedDate());
            //save training
            newTraining = repository.saveAndFlush(newTraining);


            // set group_traing_syllabus
            newTraining.setTrainingSyllabusList(trainingSyllabusService.setGroup(newTraining, trainingRequest.getSyllabusList()));
            // save group_traing_syllabus
            trainingSyllabusService.update(newTraining.getTrainingSyllabusList());

            return setClassandSyllabus(newTraining);
        } catch (ApplicationException ex) {
            throw ex;
        }
    }

    @Override
    public void delte(Long id) {
        try {
            Training training = repository.findById(id).orElseThrow(() -> new NotFoundException("Training Program Not Found"));
            classService.updateByTraining(training);

            trainingSyllabusService.delete(training.getTrainingSyllabusList());

            repository.delete(training);

        } catch (ApplicationException ex) {
            throw ex;
        }
    }

    @Override
    public List<TrainingResponse> findAll(){
        try {
            List<Training> listTraining = repository.findAll();

            return setClassandSyllabusList(listTraining);
        } catch (ApplicationException ex) {
            throw ex;
        }
    }

    @Override
    public TrainingResponse findTrainingByID(Long trainingId) {
        try{
            Training training = repository.findById(trainingId).orElseThrow(() -> new NotFoundException("Training Program Not Found"));
            return setClassandSyllabus(training);

        } catch (ApplicationException ex) {
            throw ex;
        }
    }

    private List<TrainingResponse> setClassandSyllabusList(List<Training> listTraining){
        List<TrainingResponse> trainingResponseList = new ArrayList<>();
        for(Training training : listTraining) {
            trainingResponseList.add(setClassandSyllabus(training));
        }
        return trainingResponseList;
    }
    private TrainingResponse setClassandSyllabus(Training training){
        TrainingResponse trainingResponse = trainingMapper.toResponse(training);
        trainingResponse.setSyllabusList(trainingSyllabusService.findSyllabusByTraining(training));
        trainingResponse.setClassList(classService.findClassbyTraining(training));

        return trainingResponse;
    }


}
