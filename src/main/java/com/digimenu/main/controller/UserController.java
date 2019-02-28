package com.digimenu.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.digimenu.main.repository.ConfirmationTokenRepository;
import com.digimenu.main.repository.RoleRepository;
import com.digimenu.main.repository.UserRepository;
import com.digimenu.main.security.ConfirmationToken;
import com.digimenu.main.security.User;
import com.digimenu.main.service.EmailSenderService;
import com.digimenu.main.service.SecurityService;
import com.digimenu.main.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ConfirmationTokenRepository confirmTokenRepo;
	@Autowired
    private EmailSenderService emailSenderService;
	
	@PostMapping("/register")
	@ResponseBody
	ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
		
		User existingUser= userService.findByEmail(user.getEmail());
		User existingUsername=userService.findByUsername(user.getUsername());
		
		if(existingUser!=null) {
			return new ResponseEntity<>("Bu mail adresine kayıtlı kullanıcı bulunmaktadır",HttpStatus.CONFLICT);
		}
		else if(existingUsername!=null) {
			return new ResponseEntity<>("Bu kullanıcı adı kullanılmaktadır",HttpStatus.CONFLICT);
		}
		else {
			userService.save(user);
			ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmTokenRepo.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Digimenu'ye Hoşgeldiniz!");
            mailMessage.setFrom("business@digimenu.online"); //gereksiz 	
            mailMessage.setText("Üyeliğinizi doğrulamak için lütfen doğrulama linkine tıklayınız : "
            +"http://localhost:8080/user/confirm-account?token="+confirmationToken.getConfirmationToken());
            
            emailSenderService.sendEmail(mailMessage);
		}
		
		return new ResponseEntity<>("Aktivasyon epostasi adresinize gönderilmiştir",HttpStatus.CREATED);
	}
	
	@GetMapping("/confirm-account")
	@ResponseBody
		ResponseEntity<String >confirmAccount(@RequestParam("token") String confirmToken) {
		ConfirmationToken token=confirmTokenRepo.findByConfirmationToken(confirmToken);
		if(token!=null) {
			User user = userService.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userService.save(user); // parolayı yeniden hashliyor parolaya dokunmadan bi çözüm bul 
            //model.addAttribute("message", "Üyeliğiniz başarıyla onaylanmıştır.");
		}else {
			//model.addAttribute("message", "Link geçersiz ya da bozulmuş :( Lütfen bu maile cevap olarak sorunu bildiriniz.");
		}
		
		return new ResponseEntity<>("üyelik onaylanmıştır",HttpStatus.ACCEPTED);
	}
}
