package fpt.intern.fa_api.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.response.StatusEnum;
import fpt.intern.fa_api.repository.UserRepository;
import fpt.intern.fa_api.util.EmailService;
import fpt.intern.fa_api.util.TemporaryCodeService;

import java.util.Optional;
import java.util.Random;
import java.text.DecimalFormat;

import jakarta.mail.MessagingException;

@Tag(name = "Email", description = "Email APIs")
@RestController
@RequestMapping("fa/api/auth")
public class EmailController {
	@Autowired
    private EmailService emailService;
	@Autowired
    private UserRepository userRepository;
    @Autowired
    private TemporaryCodeService temporaryCodeService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String Email) throws MessagingException {
   
    		 Random random = new Random();

             int randomNumber = random.nextInt(900000) + 100000;
             DecimalFormat decimalFormat = new DecimalFormat("000000");
             String formattedNumber = decimalFormat.format(randomNumber);
            emailService.sendConfirmationEmail(Email, formattedNumber);
            return formattedNumber;
        
       
    }
  
}
