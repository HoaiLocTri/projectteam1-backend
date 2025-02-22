package fpt.intern.fa_api.service.impl;

import fpt.intern.fa_api.exception.ApplicationException;
import fpt.intern.fa_api.exception.UserExistException;
import fpt.intern.fa_api.model.entity.Roles;
import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.mapper.UserMapper;
import fpt.intern.fa_api.model.response.AsEntity.UserResponse;
import fpt.intern.fa_api.repository.RolesRepository;
import fpt.intern.fa_api.repository.UserRepository;
import fpt.intern.fa_api.service.UserService;
import fpt.intern.fa_api.util.EmailService;
import fpt.intern.fa_api.util.ValidatorUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

	private final UserMapper userMapper;

    private final ValidatorUtil validatorUtil;
	private final RolesRepository rolesRepository;
	private final EmailService emailService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public List<UserResponse> findAll() {
		try {
			List<User> list = repository.findAll();
			List<UserResponse> ListclassResponse = userMapper.toResponseList(list);
			System.out.print(list);
			System.out.print(ListclassResponse);
			return ListclassResponse;
		} catch (ApplicationException ex) {
			throw ex;
		}
	}


	@Override
	public User insert(User user) throws MessagingException {
		if(repository.findByUsername(user.getUsername()).isPresent())
			throw new UserExistException("Username đã tồn tại");
		if(repository.findByEmail(user.getEmail()).isPresent())
			throw  new UserExistException("Email đã tồn tại");
		Roles role = rolesRepository.findByName(user.getRoles().getName()).orElseThrow(RuntimeException::new);
		String password = RandomStringUtils.random(8, 65, 122, true, true);
		user.setRoles(role);
		user.setPassword(passwordEncoder.encode(password));
		User result = repository.saveAndFlush(user);
		emailService.sendAccountByByMail(user.getUsername(), user.getEmail(), password);
		return result;
	}


	@Override
	public User update(User user) throws MessagingException {
		User exuser =repository.findByEmail(user.getEmail()).orElse(null);
		if(exuser!=null)
		{
			exuser.setEmail(user.getEmail());
			exuser.setUsername(user.getUsername());
			exuser.setPhone(user.getPhone());
			exuser.setGender(user.getGender());
			exuser.setDateOfBirth(user.getDateOfBirth());
			Roles role = rolesRepository.findByName(user.getRoles().getName()).orElseThrow(RuntimeException::new);
			exuser.setRoles(role);
			User result = repository.saveAndFlush(exuser);

		return result;
		}
		else 
		{
			throw  new UserExistException("Người dùng không tồn tại");
		}
	}


	@Override
	public UserResponse FindById(long id) {
		try {
			User user = repository.findById(id).orElseThrow(RuntimeException::new);
			UserResponse userResponse = userMapper.toResponse(user);

			return userResponse;
		} catch (ApplicationException ex) {
			throw ex;
		}
	}
}



