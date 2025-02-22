package fpt.intern.fa_api.service;

import fpt.intern.fa_api.model.entity.Training;
import fpt.intern.fa_api.model.request.AsEntity.TrainingRequest;
import fpt.intern.fa_api.model.response.AsEntity.TrainingResponse;
import jakarta.mail.MessagingException;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

public interface TrainingService {
    TrainingResponse insert(TrainingRequest trainingRequest)throws MessagingException;
    TrainingResponse update(TrainingRequest trainingRequest);
    void delte(Long id);
    List<TrainingResponse> findAll();
    TrainingResponse findTrainingByID(Long trainingId);



}
