package fpt.intern.fa_api.service;

import java.util.List;

import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.response.AsEntity.ClassResponse;
import fpt.intern.fa_api.model.response.AsEntity.UserResponse;
import jakarta.mail.MessagingException;

public interface UserService {
  

    User insert(User user) throws MessagingException;
    User update(User user) throws MessagingException;

	List<UserResponse> findAll();
	UserResponse FindById(long id);
}
